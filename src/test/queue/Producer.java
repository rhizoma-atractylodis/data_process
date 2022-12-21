package queue;

import com.lmax.disruptor.RingBuffer;
import pojo.DisruptorEvent;
import pojo.MeasurementData;

public class Producer {
    private final RingBuffer<DisruptorEvent> ringBuffer;

    public Producer(RingBuffer<DisruptorEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(DisruptorEvent data){
        long sequence = ringBuffer.next();
        try{
            DisruptorEvent event = ringBuffer.get(sequence);
            event.getData().setByData(data.getData());
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
