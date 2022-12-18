package net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.ByteProcessor;

import java.util.Arrays;

public class MeasurementDataDecoder extends DelimiterBasedFrameDecoder {
    private ByteBuf buf;
    private int maxLength;
    private boolean stripDelimiter;
    private boolean failFast;

    public MeasurementDataDecoder(int maxFrameLength, boolean stripDelimiter, boolean failFast) {
        super(maxFrameLength, stripDelimiter, failFast, Unpooled.copiedBuffer("\n".getBytes()));
        this.maxLength = maxFrameLength;
        this.stripDelimiter = stripDelimiter;
        this.failFast = failFast;
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
        byte[] arr = new byte[buffer.readableBytes()];
        buffer.readBytes(arr);
//        String s = new String(arr);
//        if (s.contains("z")) {
//            System.out.println(s);
//        }

        for (byte b : arr) {
            System.out.print(b + " ");
        }
        System.out.print(new String(arr) + "\n");
//        final int eol = findEndOfLine(buffer);
//        String result = "";
//        if (eol >= 0) {
//            // 1.计算分隔符和包长度
//            final int length = eol - buffer.readerIndex();
//            final int delimLength = buffer.getByte(eol) == '\r'? 2 : 1;
////            ByteBuf frame;
////            if (this.buf != null) {
////                frame = ctx.alloc().buffer(this.buf.readableBytes()+length);
////            } else {
////                frame = ctx.alloc().buffer(length);
////            }
//            // 2.丢弃异常数据
//            if (length > maxLength) {
//                buffer.readerIndex(eol + delimLength);
//                fail(ctx, length);
//                return null;
//            }
//            // 3.取包的时候是否包括分隔符
//            if (stripDelimiter) {
//                if (this.buf != null && this.buf.readableBytes() > 0) {
//                    byte[] arr = new byte[this.buf.readableBytes()];
//                    this.buf.readBytes(arr);
//                    result+=new String(arr);
////                    frame.writeBytes(this.buf.readBytes(this.buf.readableBytes()));
//                }
//                ByteBuf frame = buffer.readRetainedSlice(length);
//                byte[] newData = new byte[frame.readableBytes()];
//                frame.readBytes(newData);
//                byte[] bytes = Arrays.copyOfRange(newData, 5, newData.length);
//                result+=new String(bytes);
////                frame.writeBytes(buffer.readRetainedSlice(length));
//                buffer.skipBytes(delimLength);
//                this.buf = buffer.readRetainedSlice(buffer.readableBytes());
//                if (this.buf != null) {
//                    byte[] arr = new byte[this.buf.readableBytes()];
//                    this.buf.readBytes(arr);
////                    System.out.println(new String(arr));
//                }
//            } else {
//                // todo
////                frame = buffer.readRetainedSlice(length + delimLength);
//            }
//            System.out.println(result);
//            return result;
//        } else {
//            if (this.buf == null) {
//                this.buf = buffer;
//            } else {
//                ByteBuf buf = ctx.alloc().buffer(this.buf.readableBytes() + buffer.readableBytes());
//                buf.writeBytes(this.buf).writeBytes(buffer);
//                this.buf = buf;
//            }
//            return null;
//        }
        return null;
    }

    private void fail(ChannelHandlerContext ctx, int length) {
        this.fail(ctx, String.valueOf(length));
    }

    private void fail(ChannelHandlerContext ctx, String length) {
        ctx.fireExceptionCaught(new TooLongFrameException("frame length (" + length + ") exceeds the allowed maximum (" + this.maxLength + ')'));
    }

    private static int findEndOfLine(final ByteBuf buffer) {
        int i = buffer.forEachByte(ByteProcessor.FIND_LF);
        if (i > 0 && buffer.getByte(i - 1) == '\r') {
            i--;
        }
        return i;
    }
}
