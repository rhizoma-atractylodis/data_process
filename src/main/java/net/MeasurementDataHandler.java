package net;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import data.DataQueue;
import data.MeasurementDataResolver;
import exception.DataTypeException;
import data.impl.DefaultDataResolver;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.MeasurementData;

import java.util.Arrays;

public class MeasurementDataHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf buffer;
    private DataQueue dataQueue = DataQueue.QUEUE;
    private final Logger logger = LoggerFactory.getLogger(MeasurementDataHandler.class);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buffer = ctx.alloc().buffer(1024);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buffer.release();
        buffer = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        int i = buf.readableBytes();
        byte[] arr = new byte[i];
        buf.readBytes(arr);
        byte[] bytes = Arrays.copyOfRange(arr, 6, arr.length);
        Disruptor<MeasurementData> queue = dataQueue.getMeasurementDataQueue();
        RingBuffer<MeasurementData> ringBuffer = queue.getRingBuffer();
        long seq = ringBuffer.next();
        try {
            MeasurementData dataSlot = ringBuffer.get(seq);
            String line = new String(bytes);
            logger.info(line);
            MeasurementDataResolver resolver = new DefaultDataResolver();
            Object result = resolver.resolveLineData(line);
            if (result instanceof MeasurementData) {
                dataSlot.setByData(((MeasurementData) result));
            } else {
                throw new DataTypeException();
            }
        } catch (DataTypeException e) {
            logger.error(e.getMessage());
        } finally {
            ringBuffer.publish(seq);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("error: " + cause.getMessage());
    }
}
