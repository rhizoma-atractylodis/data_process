package data;

import base.JsonUtil;
import data.impl.DefaultIPLocation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.Test;
import pojo.MeasurementData;
import pojo.PingData;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class TestDataProcess {
    @Test
    public void testIPLocate() {
        IPLocation location = new DefaultIPLocation();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> stringObjectMap = location.LocateBySingleIP("123.123.123.0");
            System.out.println(stringObjectMap);
        }
    }

    @Test
    public void testReflect() {
        PingData data = new PingData();
        MeasurementData mData = data;
        for (Method field : mData.getClass().getMethods()) {
            System.out.println(field);
        }
    }

    @Test
    public void testReflectInt() {
        System.out.println(int.class);
    }

    @Test
    public void testStatistic() {
        Map<String, Object> map = new HashMap<>();
        map.put("{host}", new HashMap<String, Object>());
        map.put("{port}", new HashMap<String, Object>());
        Map<String, Object> cityLevel = (Map<String, Object>)map.getOrDefault("city", new HashMap<String, Object>());
        cityLevel.put("aa", new HashMap<>());
        map.put("city", cityLevel);
        System.out.println(map);
    }

    @Test
    public void testDataComputer() throws FileNotFoundException {
//        BufferedReader reader = new BufferedReader(new FileReader(new File("")));
//        File file = new File("/home/data/ping/root/data");
//        File[] files1 = file.listFiles();
        List<File> files = FileUtils.listFiles(new File("/home/data/ping/root/data"), new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isFile()) {
                    return file.getName().endsWith(".txt");
                } else {
                    return !file.getName().equals("trace");
                }
            }

            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        }, new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isFile()) {
                    return file.getName().endsWith(".txt");
                } else {
                    return !file.getName().equals("trace");
                }
            }

            @Override
            public boolean accept(File dir, String name) {
                if (dir.isDirectory()) {
                    System.out.println(name);
                    return !dir.getName().equals("trace");
                }
                return true;
            }
        }).stream().toList();
        int index = 0;
        try {
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = null;
            ObjectOutputStream oos = null;
            socket = new Socket("106.3.133.4", 12345);
            oos = new ObjectOutputStream(socket.getOutputStream());
            for (File file : files) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                // real data test
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String s = line.split("\t")[1];
//                    List<Object> list = JsonUtil.fromJsonList(s);
//                    for (Object o : list) {
//                        oos.write((index+". "+o +"\n").getBytes());
//                        String s1 = index + ". " + o;
//                        if (s1.contains("z")) {
//                            System.out.println(s1);
//                        }
//                        index++;
//                    }
//                }
                // simple data test
                for (int i = 0; i < 1; i++) {
                    oos.write((index+"hhhhhhaaaaaa" +"\n").getBytes());
                    index++;
                }
                oos.close();
                Thread.sleep(100);
            }
            assert oos != null;
            oos.write("end info".getBytes());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadBuf() {
        ByteBuf buf = new EmptyByteBuf(new PooledByteBufAllocator());
        ByteBuf buffer = buf.alloc().buffer(1024);
        buffer.writeBytes("hello\nworld".getBytes());
        ByteBuf buf1 = buffer.readRetainedSlice(5);
        buffer.skipBytes(1);
        ByteBuf buf2 = buffer.readRetainedSlice(buffer.readableBytes());
        byte[] arr = new byte[5];
//        buf1.readBytes(arr);
//        System.out.println(new String(arr));
//        buf2.readBytes(arr);
//        System.out.println(new String(arr));

        ByteBuf buffer1 = buf.alloc().buffer(1024);
        buffer1.writeBytes("eello\noorld".getBytes());
        ByteBuf buf11 = buffer1.readRetainedSlice(5);
//        buffer.skipBytes(1);
        ByteBuf b = buf.alloc().buffer(1024);
        b.writeBytes(buf2).writeBytes(buf11);
//        System.out.println(b.readableBytes());
        byte[] arr1 = new byte[10];
        b.readBytes(arr1);
        System.out.println(new String(arr1));
        byte[] bytes = "z\u0000\u0000\u0004".getBytes();
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
//        buffer.writeBytes("eello\noorld".getBytes());
//        buf1 = buf2.writeBytes(buffer.readRetainedSlice(5));
//        arr = new byte[10];
//        buf1.readBytes(arr);
//        System.out.println(new String(arr));
    }
}
