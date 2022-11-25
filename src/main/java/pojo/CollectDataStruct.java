package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CollectDataStruct {
    private String country;
    private String region;
    private String city;
    private String netSegment;
    private List<Float> rttList;
    private Integer ipCount;
}
