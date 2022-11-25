package data;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@FunctionalInterface
public interface MeasurementDataComputer {
    void compute(Map<String, Object> data);
}