package data;

import data.impl.DefaultIPLocation;
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
        files.forEach(System.out::println);
        try {
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = null;
            ObjectOutputStream oos = null;
            for (File file : files) {
                socket = new Socket("192.168.0.191", 12345);
                oos = new ObjectOutputStream(socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    oos.write(line.getBytes());
                }
                oos.close();
                Thread.sleep(100);
            }
            oos.write("end info".getBytes());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
