package store.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import config.cfg.DatabaseConfig;
import config.holder.DatabaseConfigHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.MeasurementData;
import store.Writeable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author atractylodis
 */
public class InfluxdbStore implements Writeable {
    private DatabaseConfig config = DatabaseConfigHolder.DEFAULT_CONFIG_HOLDER.getDatabaseConfig();
    private WriteApiBlocking writer;
    private final InfluxDBClient client;
    private List<MeasurementData> dataset;
    private Logger logger = LoggerFactory.getLogger(InfluxdbStore.class);

    public InfluxdbStore() {
        this.client = connect();
        this.writer = createWriter();
        this.dataset = new ArrayList<>(config.getBatchSize());
    }

    public InfluxdbStore(AtomicInteger pingTurn, AtomicInteger traceTurn, Map<String, Object> rawData) {
        this.client = connect();
        this.writer = createWriter();
        this.dataset = new ArrayList<>(config.getBatchSize());
    }

    private InfluxDBClient connect() {
        return InfluxDBClientFactory.create(this.config.getUrl(), this.config.getToken().toCharArray(), this.config.getOrg(), this.config.getBucket());
    }

    private WriteApiBlocking createWriter() {
        return this.client.getWriteApiBlocking();
    }

    @Override
    public void writeDatasetByPojo(List<MeasurementData> dataset) {
        if (this.dataset.size() > 5000){
            this.writer.writeMeasurements(WritePrecision.NS, this.dataset);
            this.dataset.clear();
        }
    }

    @Override
    public void writeDatasetByPoint(List<Point> dataset) {

    }

    @Override
    public void writeDataByPojo(MeasurementData data) {
        this.writer.writeMeasurement(WritePrecision.NS, data);
    }

    @Override
    public void writeDataByPoint(Point data) {
//        this.writer.writePoint();
    }

    public WriteApiBlocking getWriter() {
        return writer;
    }
}
