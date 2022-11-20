package store.impl;

import base.Constants;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.lmax.disruptor.WorkHandler;
import config.ConfigHolder;
import config.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.MeasurementData;
import pojo.PingData;
import pojo.TraceData;
import store.Writeable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author atractylodis
 */
public class InfluxdbStore implements WorkHandler<MeasurementData>, Writeable {
    private DatabaseConfig config = ConfigHolder.DEFAULT_CONFIG_HOLDER.getDatabaseConfig();
    private WriteApiBlocking writer;
    private final InfluxDBClient client;
    private List<MeasurementData> dataset;
    private AtomicInteger pingTurn;
    private AtomicInteger traceTurn;
    private ConcurrentMap<String, Object> rawData;
    private Logger logger = LoggerFactory.getLogger(InfluxdbStore.class);

    public InfluxdbStore(AtomicInteger pingTurn, AtomicInteger traceTurn, ConcurrentMap<String, Object> rawData) {
        this.client = connect();
        this.writer = createWriter();
        this.dataset = new ArrayList<>(config.getBatchSize());
        this.pingTurn = pingTurn;
        this.traceTurn = traceTurn;
        this.rawData = rawData;
    }

    private InfluxDBClient connect() {
        return InfluxDBClientFactory.create(this.config.getUrl(), this.config.getToken().toCharArray(), this.config.getOrg(), this.config.getBucket());
    }

    private WriteApiBlocking createWriter() {
        return this.client.getWriteApiBlocking();
    }

    @Override
    public void onEvent(MeasurementData event) {
        logger.info("get data: "+event);
        writeDataByPojo(event);
        int currentTurn;
        if (event.getType().equals(Constants.PING_DATA)) {
            PingData pingData = (PingData) event;
            currentTurn = this.pingTurn.get();
            if (pingData.getRound() > currentTurn) {
                // todo compute
            }
        } else {
            logger.error("");
        }
    }

    @Override
    public void writeDatasetByPojo(List<MeasurementData> dataset) {
        if (this.dataset.size() > 5000){
            this.writer.writeMeasurements(WritePrecision.NS, this.dataset);
            this.dataset.clear();
        }
    }

    @Override
    public void writeDatasetByPoint() {

    }

    @Override
    public void writeDataByPojo(MeasurementData data) {
        this.writer.writeMeasurement(WritePrecision.NS, data);
    }

    @Override
    public void writeDataByPoint() {

    }
}
