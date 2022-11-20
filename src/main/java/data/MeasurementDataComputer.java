package data;

import java.util.Map;

@FunctionalInterface
public interface MeasurementDataComputer {
    void compute(Map<String, Map<String, Map<String, Object>>> data);
}