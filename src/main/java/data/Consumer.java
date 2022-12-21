package data;

import com.lmax.disruptor.WorkHandler;
import pojo.DisruptorEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements WorkHandler<DisruptorEvent> {
    private AtomicInteger global;

    public Consumer() {}

    public Consumer(AtomicInteger global) {
        this.global = global;
    }

    @Override
    public void onEvent(DisruptorEvent measurementData) throws Exception {
        System.out.println("get data: "+measurementData.getData());
    }
}
