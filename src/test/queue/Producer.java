package queue;

import com.lmax.disruptor.RingBuffer;
import pojo.MeasurementData;

public class Producer {
    private final RingBuffer<MeasurementData> ringBuffer;

    public Producer(RingBuffer<MeasurementData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(MeasurementData data){
        long sequence = ringBuffer.next();
        try{
            MeasurementData event = ringBuffer.get(sequence);
            event.setByData(data);
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
