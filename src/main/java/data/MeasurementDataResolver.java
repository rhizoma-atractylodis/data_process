package data;

import java.util.Map;

@FunctionalInterface
public interface MeasurementDataResolver {
    Object resolveLineData(String line);
}
