package net;

import base.Constants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeasurementDataServer {
    private int port;
    private Logger logger = LoggerFactory.getLogger(MeasurementDataServer.class);

    public MeasurementDataServer() {
        this(Constants.NETTY_PORT);
    }

    public MeasurementDataServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(Constants.NETTY_BOSS_THREAD_NUMBER);
        EventLoopGroup workGroup = new NioEventLoopGroup(Constants.NETTY_WORKER_THREAD_NUMBER);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
//                                    .addLast(new MeasurementDataDecoder(2048, true, false))
                                    .addLast(new DelimiterBasedFrameDecoder(4096, Unpooled.copiedBuffer("\n".getBytes())))
                                    .addLast("measurement", new MeasurementDataHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            logger.info("waiting data");
            ChannelFuture f = b.bind(this.port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("connection error " + e.getMessage());
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
