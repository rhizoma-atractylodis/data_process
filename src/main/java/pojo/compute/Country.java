package pojo.compute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    protected String country;
    private SynchronizedDescriptiveStatistics collector;

    public Country(String country) {
        this.country = country;
    }
}
