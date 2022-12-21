package data;

import base.Constants;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.jetbrains.annotations.NotNull;
import pojo.DisruptorEvent;
import pojo.MeasurementData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public enum DataQueue {
    QUEUE(Constants.DATA_QUEUE_MAX_LEN, Constants.DATA_STORE_WORKER_NUMBER);

    private Disruptor<DisruptorEvent> measurementDataQueue;
    private BlockingWaitStrategy strategy = new BlockingWaitStrategy();
    private int bufferSize;
    private EventFactory<DisruptorEvent> measurementFactory;
    private ThreadFactory threads;
    private WorkHandler<DisruptorEvent>[] consumer;
    private int dataStoreWorkers;
    private AtomicInteger pingTurn;
    private AtomicInteger traceTurn;
    private Map<String, Object> rawData;


    DataQueue(int maxLength, int storeWorkerNum) {
        this.bufferSize = maxLength;
        this.dataStoreWorkers = storeWorkerNum;
        this.pingTurn = new AtomicInteger(0);
        this.traceTurn = new AtomicInteger(0);
        this.rawData = new ConcurrentHashMap<>();
        Lock lock = new ReentrantLock();
        this.threads = new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                return new Thread(r);
            }
        };
        this.measurementFactory = new EventFactory<DisruptorEvent>() {
            @Override
            public DisruptorEvent newInstance() {
                return new DisruptorEvent();
            }
        };
        this.consumer = new DataQueueWorker[this.dataStoreWorkers];
//        this.consumer = new Consumer[this.dataStoreWorkers];
        for (int i = 0; i < this.dataStoreWorkers; i++) {
            this.consumer[i] = new DataQueueWorker(this.pingTurn, this.traceTurn, lock, this.rawData);
//            this.consumer[i] = new Consumer();
        }
        this.measurementDataQueue = new Disruptor<>(this.measurementFactory, this.bufferSize, this.threads, ProducerType.MULTI, this.strategy);
        measurementDataQueue.handleEventsWithWorkerPool(this.consumer);
        this.measurementDataQueue.start();
    }

    public Disruptor<DisruptorEvent> getMeasurementDataQueue() {
        return this.measurementDataQueue;
    }

    public void start() {
        this.measurementDataQueue.start();
    }

    public void stop() {
        this.measurementDataQueue.shutdown();
    }
}
