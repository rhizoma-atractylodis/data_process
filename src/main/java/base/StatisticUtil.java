package base;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class StatisticUtil {
    public static double[] computeMultiPercentile(DescriptiveStatistics collector, int n) {
        double[] result = new double[n-1];
        for (int i = 1; i < n; i++) {
            result[i-1] = collector.getPercentile(i);
        }
        return result;
    }
}
