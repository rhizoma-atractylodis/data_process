package net;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import data.DataQueue;
import data.MeasurementDataResolver;
import data.impl.DefaultDataResolver;
import exception.DataTypeException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.DisruptorEvent;
import pojo.MeasurementData;
import pojo.PingData;
import pojo.TraceData;

import java.util.Arrays;

public class MeasurementDataHandler extends ChannelInboundHandlerAdapter {
    private String buffer;
    private DataQueue dataQueue;
    private final Logger logger = LoggerFactory.getLogger(MeasurementDataHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.dataQueue = DataQueue.QUEUE;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg != null) {
            ByteBuf buf = (ByteBuf) msg;
            int i = buf.readableBytes();
            byte[] arr = new byte[i];
            buf.readBytes(arr);
//            byte[] bytes = Arrays.copyOfRange(arr, 6, arr.length);
//            for (byte b : arr) {
//                System.out.print(b + " ");
//            }
//            System.out.print(new String(arr) + "\n");
//            String[] messages = new String(bytes).split("\n");
//            this.buffer = messages[messages.length - 1];


//            System.out.println(msg);
//            System.out.println(message);
//            byte[] bytes = Arrays.copyOfRange(arr, 6, arr.length);
//            System.out.println(new String(arr));
//            if (!this.buffer.equals("")) {
//                messages[0] = this.buffer+messages[0];
//            }
//            for (String message : messages) {
//                System.out.println(message);
//            }
            //数据放到queue里
//            for (int j = 0; j < messages.length - 1; j++) {
//
//            }
            Disruptor<DisruptorEvent> queue = dataQueue.getMeasurementDataQueue();
            RingBuffer<DisruptorEvent> ringBuffer = queue.getRingBuffer();
            long seq = ringBuffer.next();
            try {
                DisruptorEvent dataSlot = ringBuffer.get(seq);
                String line = new String(arr);
                logger.info(line);
                MeasurementDataResolver resolver = new DefaultDataResolver();
                Object result = resolver.resolveLineData(line);
                if (result instanceof PingData ping) {
                    dataSlot.setData(ping);
                } else if (result instanceof TraceData trace) {
                    dataSlot.setData(trace);
                } else {
                    throw new DataTypeException();
                }
            } catch (DataTypeException e) {
                logger.error(e.getMessage());
            } finally {
                //发布
                ringBuffer.publish(seq);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
//        logger.error("error: " + cause.p());
    }
}
