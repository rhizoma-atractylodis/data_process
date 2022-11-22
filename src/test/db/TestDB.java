package db;

import com.influxdb.client.domain.WritePrecision;
import org.junit.Test;
import pojo.MeasurementData;
import pojo.PingData;
import store.impl.InfluxdbStore;

import java.time.Instant;

public class TestDB {
    @Test
    public void testDB() {
        InfluxdbStore influxdbStore = new InfluxdbStore();
        PingData pingData = new PingData();
        pingData.setHost("123.123.123.123");
        pingData.setDestA("123");
        pingData.setDestB("123");
        pingData.setDestC("123");
        pingData.setDestD("123");
        pingData.setProtocol("TCP");
        pingData.setRound(1);
        pingData.setMeasurementPrefix("prefix");
        pingData.setRtt(123.123);
        pingData.setTime(Instant.now());
        influxdbStore.writeDataByPojo(pingData);
    }
}
