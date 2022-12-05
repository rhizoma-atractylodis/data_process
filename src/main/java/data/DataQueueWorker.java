package data;

import base.Constants;
import base.JsonUtil;
import com.lmax.disruptor.WorkHandler;
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
import store.impl.InfluxdbStore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataQueueWorker implements WorkHandler<MeasurementData> {
    private volatile AtomicInteger pingTurn;
    private volatile AtomicInteger traceTurn;
    private volatile Map<String, Object> rawData;
    private DataCollector dataCollector;
    private MeasurementDataComputer computer;
    private Lock lock;
    private Writeable writer;
    private Logger logger = LoggerFactory.getLogger(DataQueueWorker.class);

    public DataQueueWorker() {
        this.pingTurn = new AtomicInteger(0);
        this.traceTurn = new AtomicInteger(0);
        this.rawData = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
        this.writer = new InfluxdbStore();
        this.dataCollector = new ThreeLevelDataCollector();
        this.computer = new DefaultDataComputer();
    }

    public DataQueueWorker(AtomicInteger pingTurn, AtomicInteger traceTurn,
                           Lock lock,  Map<String, Object> rawData) {
        this.pingTurn = pingTurn;
        this.traceTurn = traceTurn;
        this.rawData = rawData;
        this.lock = lock;
        this.dataCollector = new ThreeLevelDataCollector();
        this.computer = new DefaultDataComputer();
    }

    @Override
    public void onEvent(MeasurementData event) {
        logger.info("get data: "+event);
        writer.writeDataByPojo(event);
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
}
