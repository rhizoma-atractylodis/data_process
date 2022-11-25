package pojo.compute;

import lombok.*;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class NetSegment extends City{
    private String netId;
    private SynchronizedDescriptiveStatistics collector;

    public NetSegment(String country, String region, String city, String netId, SynchronizedDescriptiveStatistics collector) {
        super(country, region, city);
        this.netId = netId;
        this.collector = collector;
    }
}
