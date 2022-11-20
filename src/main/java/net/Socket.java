package net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class Socket {
    private Logger logger = LoggerFactory.getLogger(Socket.class);

    public ServerSocket connect(int port, int retry) {
        int retryNum = 0;
        ServerSocket socket = null;
        while (retryNum < retry && socket == null) {
            try {
                socket = new ServerSocket(port);
            } catch (IOException e) {
                logger.warn("connect fail, retry: " + retryNum);
            }
        }
        return socket;
    }
}
