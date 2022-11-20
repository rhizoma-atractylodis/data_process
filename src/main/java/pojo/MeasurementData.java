package pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeasurementData {
    protected Logger logger = LoggerFactory.getLogger(MeasurementData.class);

    public String getType() {
        return "unknown";
    }

    public void setByData(MeasurementData data) {
        if (data.getType().equals("unknown")) {
            logger.warn("unknown data type");
        }
    }
}
