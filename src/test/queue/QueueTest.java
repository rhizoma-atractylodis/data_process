package queue;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import data.DataQueue;
import org.junit.Test;
import pojo.MeasurementData;
import pojo.PingData;

import java.util.concurrent.atomic.AtomicInteger;

public class QueueTest {
    private AtomicInteger global = new AtomicInteger(0);

    @Test
    public void TestDisruptor() throws InterruptedException {
        DataQueue queue = DataQueue.QUEUE;
        Disruptor<MeasurementData> measurementDataQueue = queue.getMeasurementDataQueue();
        measurementDataQueue.handleEventsWithWorkerPool(new Consumer(global), new Consumer(global));
        measurementDataQueue.start();
        RingBuffer<MeasurementData> ringBuffer = measurementDataQueue.getRingBuffer();
        Producer producer = new Producer(ringBuffer);

        for(int i=0; i<100; i++){
            producer.pushData(new PingData());
            Thread.sleep(100);
            System.out.println("push data " + i);
        }
        measurementDataQueue.shutdown();
        System.out.println(this.global.get());
    }
}
