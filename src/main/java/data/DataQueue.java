package data;

import base.Constants;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.jetbrains.annotations.NotNull;
import pojo.MeasurementData;
import store.impl.InfluxdbStore;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public enum DataQueue {
    QUEUE(Constants.DATA_QUEUE_MAX_LEN, Constants.DATA_STORE_WORKER_NUMBER);

    private Disruptor<MeasurementData> measurementDataQueue;
    private BlockingWaitStrategy strategy = new BlockingWaitStrategy();
    private int bufferSize;
    private EventFactory<MeasurementData> measurementFactory;
    private ThreadFactory threads;
    private WorkHandler<MeasurementData>[] consumer;
    private int dataStoreWorkers;
    private AtomicInteger pingTurn;
    private AtomicInteger traceTurn;
    private ConcurrentMap<String, Object> rawData;


    DataQueue(int maxLength, int storeWorkerNum) {
        this.bufferSize = maxLength;
        this.dataStoreWorkers = storeWorkerNum;
        this.pingTurn = new AtomicInteger(0);
        this.traceTurn = new AtomicInteger(0);
        this.rawData = new ConcurrentHashMap<>();
        this.threads = new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                return new Thread(r);
            }
        };
        this.measurementFactory = new EventFactory<MeasurementData>() {
            @Override
            public MeasurementData newInstance() {
                return new MeasurementData();
            }
        };
        this.consumer = new InfluxdbStore[this.dataStoreWorkers];
        for (int i = 0; i < this.dataStoreWorkers; i++) {
            this.consumer[i] = new InfluxdbStore(this.pingTurn, this.traceTurn, this.rawData);
        }
        this.measurementDataQueue = new Disruptor<>(this.measurementFactory, this.bufferSize, this.threads, ProducerType.MULTI, this.strategy);
    }

    public Disruptor<MeasurementData> getMeasurementDataQueue() {
        return this.measurementDataQueue;
    }
}
