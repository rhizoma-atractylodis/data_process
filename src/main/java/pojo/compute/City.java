package pojo.compute;

import lombok.*;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class City extends Region{
    protected String city;
    private SynchronizedDescriptiveStatistics collector;

    public City(String country, String region, String city, SynchronizedDescriptiveStatistics collector) {
        super(country, region);
        this.city = city;
        this.collector = collector;
    }

    public City(String country, String region, String city) {
        super(country, region);
        this.city = city;
    }
}
