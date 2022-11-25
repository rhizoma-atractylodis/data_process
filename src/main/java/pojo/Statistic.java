package pojo;

import lombok.Data;

import java.util.List;

@Data
public class Statistic {
    private double avg;
    private double std;
    private double skew;
    private double kurt;
    private List<Double> quantile;
}
