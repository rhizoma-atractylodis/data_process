package data;

import data.impl.DefaultIPLocation;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.Test;
import pojo.MeasurementData;
import pojo.PingData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
}
