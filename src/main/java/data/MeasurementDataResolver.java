package data;

import pojo.MeasurementData;

import java.util.Map;

@FunctionalInterface
public interface MeasurementDataResolver {
    Object resolveLineData(String line);
}
