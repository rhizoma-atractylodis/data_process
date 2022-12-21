package queue;

import com.lmax.disruptor.WorkHandler;
import pojo.DisruptorEvent;
import pojo.MeasurementData;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements WorkHandler<DisruptorEvent> {
    private AtomicInteger global;

    public Consumer(AtomicInteger global) {
        this.global = global;
    }

    @Override
    public void onEvent(DisruptorEvent measurementData) throws Exception {
        System.out.println(Thread.currentThread().getId() +
                "Global = " + global.incrementAndGet());
    }
}
