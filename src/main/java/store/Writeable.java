package store;

import com.influxdb.client.write.Point;
import pojo.MeasurementData;

import java.util.List;

public interface Writeable {
    void writeDatasetByPojo(List<MeasurementData> dataset);

    void writeDatasetByPoint(List<Point> dataset);

    void writeDataByPojo(MeasurementData data);

    void writeDataByPoint(Point data);
}
