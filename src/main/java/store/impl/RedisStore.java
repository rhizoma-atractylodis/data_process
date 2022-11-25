package store.impl;

import base.JsonUtil;
import com.influxdb.client.write.Point;
import config.cfg.RedisConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.MeasurementData;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import store.Writeable;

import java.util.List;

public class RedisStore implements Writeable {
    private JedisPool redisPool;
    private RedisConfig config = RedisConfig.DEFAULT_REDIS_CONFIG;
    private GenericObjectPoolConfig<Jedis> poolConfig;
    private Logger logger = LoggerFactory.getLogger(RedisStore.class);

    public RedisStore() {
        this.poolConfig = new GenericObjectPoolConfig<>();
        // todo config
        this.poolConfig.setMaxIdle(config.getMaxIdle());
        this.poolConfig.setMaxTotal(config.getMaxTotal());
        this.poolConfig.setMinIdle(config.getMinIdle());
        this.poolConfig.setTestWhileIdle(config.isTestWhileIdle());
        this.poolConfig.setBlockWhenExhausted(config.isBlockWhenExhausted());
        this.poolConfig.setJmxEnabled(config.isJmxEnable());
        if (config.getPassword().equals("")) {
            this.redisPool = new JedisPool(this.poolConfig, config.getHost(), config.getPort(), config.getTimeout());
        } else {
            this.redisPool = new JedisPool(this.poolConfig, config.getHost(), config.getPort(), config.getTimeout(),
                    config.getPassword());
        }
    }

    public Jedis getRedisConnection() {
        return this.redisPool.getResource();
    }

    public Object get(String key) {
        Object result = null;
        try (Jedis client = this.getRedisConnection()) {
            String json = client.get(key);
            result = JsonUtil.fromJson(json);
        } catch (Exception e) {
            logger.error("get data from redis error: " + e.getMessage());
        }
        return result;
    }

    public void set(String key, Object value) {
        try (Jedis client = this.getRedisConnection()) {
            client.set(key, JsonUtil.toJson(value));
        } catch (Exception e) {
            logger.error("store " + value + " to redis error: " + e.getMessage());
        }
    }

    @Override
    public void writeDatasetByPojo(List<MeasurementData> dataset) {

    }

    @Override
    public void writeDatasetByPoint(List<Point> dataset) {

    }

    @Override
    public void writeDataByPojo(MeasurementData data) {

    }

    @Override
    public void writeDataByPoint(Point data) {

    }
}
