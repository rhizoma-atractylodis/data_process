package data;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import data.impl.DefaultDataResolver;
import exception.DataTypeException;
import org.junit.Test;
import pojo.DisruptorEvent;
import pojo.PingData;
import pojo.TraceData;

public class TestResolver {
    private DataQueue dataQueue = DataQueue.QUEUE;
    @Test
    public void testResolver() throws InterruptedException {
        String data = "ping,host=cb52f4c932cb,influx_data_bucket=FK-SDZX-EasternEurope-Origin proto_base64=Ci0IydLYiMUwGgw4My4xOS42NS4xMzMgASgFMhJFYXN0ZXJuRXVyb3BlLVBpbmcVCCxCQg== 1667807805234783964";

        Disruptor<DisruptorEvent> queue = dataQueue.getMeasurementDataQueue();
        for (int i = 0; i < 10; i++) {
            RingBuffer<DisruptorEvent> ringBuffer = queue.getRingBuffer();
            long seq = ringBuffer.next();
            try {
                DisruptorEvent dataSlot = ringBuffer.get(seq);
                MeasurementDataResolver resolver = new DefaultDataResolver();
                Object result = resolver.resolveLineData(data);
                if (result instanceof PingData ping) {
                    dataSlot.setData(ping);
                } else if (result instanceof TraceData trace) {
                    dataSlot.setData(trace);
                } else {
                    throw new DataTypeException();
                }
            } catch (DataTypeException e) {
                System.out.println(e.getMessage());
            } finally {
                //发布
                ringBuffer.publish(seq);
            }
        }
        Thread.sleep(10000);
    }
}
