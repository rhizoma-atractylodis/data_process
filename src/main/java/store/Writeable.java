package store;

import pojo.MeasurementData;

import java.util.List;

public interface Writeable {
    void writeDatasetByPojo(List<MeasurementData> dataset);

    void writeDatasetByPoint();

    void writeDataByPojo(MeasurementData data);

    void writeDataByPoint();
}
