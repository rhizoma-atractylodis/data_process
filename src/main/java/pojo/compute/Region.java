package pojo.compute;

import lombok.*;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Region extends Country {
    protected String region;
    private SynchronizedDescriptiveStatistics collector;

    public Region(String country, String region, SynchronizedDescriptiveStatistics collector) {
        super(country);
        this.region = region;
        this.collector = collector;
    }

    public Region(String country, String region) {
        super(country);
        this.region = region;
    }
}
