package config.cfg;

import base.Constants;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.etcd.jetcd.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author atractylodis
 */
public enum DatabaseConfig {
    DEFAULT_CONFIG(Constants.INFLUXDB_URL,
            Constants.INFLUXDB_TOKEN,
            Constants.INFLUXDB_ORG,
            Constants.INFLUXDB_BUCKET,
            Constants.BATCH_SIZE,
            Constants.CLIENT_POOL_SIZE,
            Constants.FLUSH_INTERVAL,
            Constants.JITTER_INTERVAL,
            Constants.RETRY_INTERVAL,
            Constants.MAX_RETRY_TIME,
            Constants.MAX_RETRIES,
            Constants.MAX_RETRY_DELAY);

    private String url;
    private String token;
    private String org;
    private String bucket;
    private int batchSize;
    private int clientPoolSize;
    private int flushInterval;
    private int jitterInterval;
    private int retryInterval;
    private int maxRetryTime;
    private int maxRetries;
    private int maxRetryDelay;
    private Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    DatabaseConfig(String url, String token, String org, String bucket) {
        this.url = url;
        this.token = token;
        this.org = org;
        this.bucket = bucket;
    }

    DatabaseConfig(String url, String token, String org, String bucket, int batchSize, int clientPoolSize, int flushInterval, int jitterInterval, int retryInterval, int maxRetryTime, int maxRetries, int maxRetryDelay) {
        this.url = url;
        this.token = token;
        this.org = org;
        this.bucket = bucket;
        this.batchSize = batchSize;
        this.clientPoolSize = clientPoolSize;
        this.flushInterval = flushInterval;
        this.jitterInterval = jitterInterval;
        this.retryInterval = retryInterval;
        this.maxRetryTime = maxRetryTime;
        this.maxRetries = maxRetries;
        this.maxRetryDelay = maxRetryDelay;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public String getOrg() {
        return org;
    }

    public String getBucket() {
        return bucket;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getClientPoolSize() {
        return clientPoolSize;
    }

    public void setClientPoolSize(int clientPoolSize) {
        this.clientPoolSize = clientPoolSize;
    }

    public int getFlushInterval() {
        return flushInterval;
    }

    public void setFlushInterval(int flushInterval) {
        this.flushInterval = flushInterval;
    }

    public int getJitterInterval() {
        return jitterInterval;
    }

    public void setJitterInterval(int jitterInterval) {
        this.jitterInterval = jitterInterval;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }

    public int getMaxRetryTime() {
        return maxRetryTime;
    }

    public void setMaxRetryTime(int maxRetryTime) {
        this.maxRetryTime = maxRetryTime;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getMaxRetryDelay() {
        return maxRetryDelay;
    }

    public void setMaxRetryDelay(int maxRetryDelay) {
        this.maxRetryDelay = maxRetryDelay;
    }

    public static DatabaseConfig fromMap(Map<String, Object> config) {
        config.forEach((key, value) -> {
            String v = (String) value;
            selectAndModify(key, v);
        });
        return DEFAULT_CONFIG;
    }

    public static DatabaseConfig fromJson(String config) {
        Gson gson = new Gson();
        Map<String,Object> map = gson.fromJson(config, new TypeToken<HashMap<String,Object>>(){}.getType());
        return fromMap(map);
    }

    public static DatabaseConfig fromList(List<KeyValue> config) {
        config.forEach(e -> {
            String key = e.getKey().toString();
            String value = e.getValue().toString();
            selectAndModify(key, value);
        });
        return DEFAULT_CONFIG;
    }

    public static DatabaseConfig fromConfig(DatabaseConfig config) {
        DEFAULT_CONFIG.setUrl(config.getUrl());
        DEFAULT_CONFIG.setOrg(config.getOrg());
        DEFAULT_CONFIG.setToken(config.getToken());
        DEFAULT_CONFIG.setBucket(config.getBucket());
        DEFAULT_CONFIG.setBatchSize(config.getBatchSize());
        DEFAULT_CONFIG.setClientPoolSize(config.getClientPoolSize());
        DEFAULT_CONFIG.setJitterInterval(config.getJitterInterval());
        DEFAULT_CONFIG.setFlushInterval(config.getFlushInterval());
        DEFAULT_CONFIG.setRetryInterval(config.getRetryInterval());
        DEFAULT_CONFIG.setMaxRetryTime(config.getMaxRetryTime());
        DEFAULT_CONFIG.setMaxRetryDelay(config.getMaxRetryDelay());
        DEFAULT_CONFIG.setMaxRetries(config.getMaxRetries());
        return DEFAULT_CONFIG;
    }

    private static void selectAndModify(String key, String value) {
        switch (key){
            case Constants.KEY_INFLUXDB_URL -> DEFAULT_CONFIG.setUrl(value);
            case Constants.KEY_INFLUXDB_TOKEN -> DEFAULT_CONFIG.setToken(value);
            case Constants.KEY_INFLUXDB_ORG -> DEFAULT_CONFIG.setOrg(value);
            case Constants.KEY_INFLUXDB_BUCKET -> DEFAULT_CONFIG.setBucket(value);
            case Constants.KEY_INFLUXDB_CLIENT_POOL_SIZE -> DEFAULT_CONFIG.setClientPoolSize(Integer.parseInt(value));
            case Constants.KEY_INFLUXDB_BATCH_SIZE -> DEFAULT_CONFIG.setBatchSize(Integer.parseInt(value));
            case Constants.KEY_INFLUXDB_FLUSH_INTERVAL -> DEFAULT_CONFIG.setFlushInterval(Integer.parseInt(value));
            case Constants.KEY_INFLUXDB_JITTER_INTERVAL -> DEFAULT_CONFIG.setJitterInterval(Integer.parseInt(value));
            case Constants.KEY_INFLUXDB_RETRY_INTERVAL -> DEFAULT_CONFIG.setRetryInterval(Integer.parseInt(value));
            case Constants.KEY_INFLUXDB_MAX_RETRIES -> DEFAULT_CONFIG.setMaxRetries(Integer.parseInt(value));
            case Constants.KEY_INFLUXDB_MAX_RETRY_DELAY -> DEFAULT_CONFIG.setMaxRetryDelay(Integer.parseInt(value));
            case Constants.KEY_INFLUXDB_MAX_RETRY_TIME -> DEFAULT_CONFIG.setMaxRetryTime(Integer.parseInt(value));
            default -> {

            }
        }
    }
}
