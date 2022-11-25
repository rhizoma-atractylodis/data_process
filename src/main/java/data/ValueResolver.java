package data;

import pojo.MeasurementData;

import java.lang.reflect.InvocationTargetException;

@FunctionalInterface
public interface ValueResolver {
    MeasurementData resolveValues(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
