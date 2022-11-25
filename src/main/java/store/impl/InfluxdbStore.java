package store.impl;

import base.Constants;
import base.JsonUtil;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.lmax.disruptor.WorkHandler;
import config.holder.DatabaseConfigHolder;
import config.cfg.DatabaseConfig;
import data.DataCollector;
import data.MeasurementDataComputer;
import data.impl.DefaultDataComputer;
import data.impl.ThreeLevelDataCollector;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.MeasurementData;
import pojo.PingData;
import pojo.compute.City;
import pojo.compute.Country;
import pojo.compute.NetSegment;
import pojo.compute.Region;
import store.Writeable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * @author atractylodis
 */
public class InfluxdbStore implements WorkHandler<MeasurementData>, Writeable {
    private DatabaseConfig config = DatabaseConfigHolder.DEFAULT_CONFIG_HOLDER.getDatabaseConfig();
    private WriteApiBlocking writer;
    private final InfluxDBClient client;
    private List<MeasurementData> dataset;
    private volatile AtomicInteger pingTurn;
    private volatile AtomicInteger traceTurn;
    private volatile Map<String, Object> rawData;
    private DataCollector dataCollector;
    private MeasurementDataComputer computer;
    private Lock lock;
    private Logger logger = LoggerFactory.getLogger(InfluxdbStore.class);

    public InfluxdbStore(Lock lock) {
        this.client = connect();
        this.writer = createWriter();
        this.dataset = new ArrayList<>(config.getBatchSize());
        this.pingTurn = new AtomicInteger(0);
        this.traceTurn = new AtomicInteger(0);
        this.rawData = new ConcurrentHashMap<>();
        this.dataCollector = new ThreeLevelDataCollector();
        this.computer = new DefaultDataComputer();
        this.lock = lock;
    }

    public InfluxdbStore(AtomicInteger pingTurn, AtomicInteger traceTurn, Lock lock, Map<String, Object> rawData) {
        this.client = connect();
        this.writer = createWriter();
        this.dataset = new ArrayList<>(config.getBatchSize());
        this.pingTurn = pingTurn;
        this.traceTurn = traceTurn;
        this.rawData = rawData;
        this.dataCollector = new ThreeLevelDataCollector();
        this.computer = new DefaultDataComputer();
        this.lock = lock;
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
                Map<String, Object> dataMap;
                try {
                    lock.lock();
                    dataMap = JsonUtil.mapDeepcopy(this.rawData);
                    this.rawData.clear();
                    this.pingTurn.set(pingData.getRound());
                } finally {
                    lock.unlock();
                }
                this.computer.compute(dataMap);
            }
            try {
                lock.lock();
                String country = pingData.getCountry();
                String region = pingData.getRegion();
                String city = pingData.getCity();
                String ip = pingData.getHost();
                Float rtt = pingData.getRtt();
                String[] split = ip.split("\\.");
                String seg = split[0] + "." + split[1] + "." + split[2] + ".0";
                Object countryCollector = this.rawData.getOrDefault(country, new Country(country, new SynchronizedDescriptiveStatistics()));
                Object regionCollector = this.rawData.getOrDefault(region, new Region(country, region, new SynchronizedDescriptiveStatistics()));
                Object cityCollector = this.rawData.getOrDefault(city, new City(country, region, city, new SynchronizedDescriptiveStatistics()));
                Object segCollector = this.rawData.getOrDefault(seg, new NetSegment(city, region, city, seg, new SynchronizedDescriptiveStatistics()));
                this.dataCollector.collect(rtt,countryCollector, regionCollector, cityCollector, segCollector);
            } finally {
                lock.unlock();
            }
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
