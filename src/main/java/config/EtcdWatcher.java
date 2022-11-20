package config;

import base.Constants;
import io.etcd.jetcd.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public enum EtcdWatcher {
    DATABASE_CONFIG_WATCHER(Constants.ETCD_KEY_DATABASE, Constants.ETCD_HOST);

    private String etcdKey;
    private String hosts;
    private Client etcdClient;
    private Watch databaseConfigWatcher;
    private KV databaseConfig;
    private Logger logger = LoggerFactory.getLogger(EtcdWatcher.class);

    EtcdWatcher(String etcdKey, String hosts) {
        this.etcdKey = etcdKey;
        this.hosts = hosts;
        this.etcdClient = Client.builder().endpoints(this.hosts.split(",")).build();
        this.databaseConfigWatcher = this.etcdClient.getWatchClient();
        this.databaseConfig = this.etcdClient.getKVClient();
    }

    public DatabaseConfig getDatabaseConfig() {
        List<KeyValue> config = null;
        while (true) {
            try {
                config = this.databaseConfig.get(ByteSequence.from(this.etcdKey.getBytes())).get().getKvs();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            if (config != null) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("error: " + e.getMessage());
                return null;
            }
        }
        logger.info("get config success");
        return DatabaseConfig.fromList(config);
    }

    public DatabaseConfig watchDatabaseConfig(Function<KeyValue, DatabaseConfig> callback) {
        AtomicReference<DatabaseConfig> cfg = new AtomicReference<>(DatabaseConfig.DEFAULT_CONFIG);
        databaseConfigWatcher.watch(ByteSequence.from(this.etcdKey.getBytes()), listener -> listener.getEvents().forEach(event -> {
            switch (event.getEventType()) {
                case DELETE -> {
                    cfg.set(callback.apply(event.getKeyValue()));
                    logger.info(this.etcdKey + " deleted");
                }
                case PUT -> {
                    cfg.set(callback.apply(event.getKeyValue()));
                    logger.info(this.etcdKey + " has been changed");
                }
                default -> {
                }
            }
        }));
        return cfg.get();
    }
}
