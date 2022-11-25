package data;

import pojo.MeasurementData;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@FunctionalInterface
public interface DataCollector {
//    void collect(Map<String, Object> dataset, MeasurementData data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    void collect(Float data, Object... collectors);
}
