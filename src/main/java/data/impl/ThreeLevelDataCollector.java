package data.impl;

import data.DataCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.compute.City;
import pojo.compute.Country;
import pojo.compute.NetSegment;
import pojo.compute.Region;

public class ThreeLevelDataCollector implements DataCollector {
    private Logger logger = LoggerFactory.getLogger(ThreeLevelDataCollector.class);

    @Override
    public void collect(Float data, Object... collectors) {
        for (Object collector : collectors) {
            if (collector instanceof NetSegment segment) {
                segment.getCollector().addValue(data.doubleValue());
            } else if (collector instanceof City city) {
                city.getCollector().addValue(data.doubleValue());
            } else if (collector instanceof Region region) {
                region.getCollector().addValue(data.doubleValue());
            } else if (collector instanceof Country country) {
                country.getCollector().addValue(data.doubleValue());
            } else {
                logger.error("");
            }
        }

    }
}
