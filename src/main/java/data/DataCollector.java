package data;

@FunctionalInterface
public interface DataCollector {
//    void collect(Map<String, Object> dataset, MeasurementData data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    void collect(Float data, Object... collectors);
}
