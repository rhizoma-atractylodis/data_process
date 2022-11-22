package net;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.MeasurementData;
import pojo.PingData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class ServerTest {
    private Logger logger = LoggerFactory.getLogger(ServerTest.class);

    @Test
    public void cast() {
        MeasurementData data = new PingData();
        PingData ping = (PingData) data;
//        System.out.println();
        data.setByData(new PingData());
    }

    @Test
    public void nettyTest() {
        MeasurementDataServer server = new MeasurementDataServer(12345);
        server.run();
    }

    @Test
    public void client() {
        try {
            InetAddress host = InetAddress.getLocalHost();
            logger.info(host.getHostAddress());
            Socket socket = null;
            ObjectOutputStream oos = null;
            for(int i=0; i<5;i++){
                socket = new Socket("192.168.0.191", 12345);
                oos = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Sending request to Socket Server");
                if(i==4)oos.write("li xin ni ma si le".getBytes());
                else oos.write("li xin wo cao ni ma".getBytes());
                oos.close();
                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void nettyClient() {
        NettyClient nettyClient = new NettyClient("192.168.0.191", 12345);
        nettyClient.start();
    }
}
