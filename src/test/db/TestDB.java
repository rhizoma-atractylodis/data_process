package db;

import org.junit.Test;
import pojo.PingData;
import store.impl.InfluxdbStore;
import store.impl.RedisStore;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class TestDB {
    @Test
    public void testDB() {
        InfluxdbStore influxdbStore = new InfluxdbStore(new ReentrantLock());
        PingData pingData = new PingData();
        pingData.setHost("123.123.123.123");
        pingData.setDestA("123");
        pingData.setDestB("123");
        pingData.setDestC("123");
        pingData.setDestD("123");
        pingData.setProtocol("TCP");
        pingData.setRound(1);
        pingData.setMeasurementPrefix("prefix");
        pingData.setRtt(123.123f);
        pingData.setTime(Instant.now());
        influxdbStore.writeDataByPojo(pingData);
    }

    @Test
    public void testRedisSet() {
        RedisStore redis = new RedisStore();
        Map<String, String> map = new HashMap<>();
        map.put("{host}", "127.0.0.1");
        map.put("{port}", "8086");
        redis.set("123.123.123.123", map);
    }

    @Test
    public void testRedisGet() {
        RedisStore redis = new RedisStore();
        Object o = redis.get("123.123.123.123");
        Map<String, Object> m = (Map<String, Object>) o;
        System.out.println(m);
    }

    @Test
    public void testRedisCannotGet() {
        RedisStore redis = new RedisStore();
        Object o = redis.get("123.123.124.0");
        Map<String, Object> m = (Map<String, Object>) o;
        System.out.println(m);
    }
}
