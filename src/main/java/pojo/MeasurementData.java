package pojo;

import com.influxdb.annotations.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class MeasurementData {
    @Column(name = "host", tag = true)
    private String host;
    @Column(name = "dest_A", tag = true)
    private String destA;
    @Column(name = "dest_B", tag = true)
    private String destB;
    @Column(name = "dest_C", tag = true)
    private String destC;
    @Column(name = "dest_D", tag = true)
    private String destD;
    @Column(name = "protocol", tag = true)
    private String protocol;
    @Column(name = "round", tag = true)
    private int round;
    @Column(name = "measurement_prefix", tag = true)
    private String measurementPrefix;
    @Column(name = "RTT")
    private double rtt;
    @Column(timestamp = true)
    private Instant time;
    @Column(name = "country", tag = true)
    private String country;
    @Column(name = "region", tag = true)
    private String region;
    @Column(name = "city", tag = true)
    private String city;
    protected Logger logger = LoggerFactory.getLogger(MeasurementData.class);

    public String getType() {
        return "unknown";
    }

    public void setByData(MeasurementData data) {
        if (data.getType().equals("unknown")) {
            logger.warn("unknown data type");
        }
        this.host = data.host;
        this.destA = data.destA;
        this.destB = data.destB;
        this.destC = data.destC;
        this.destD = data.destD;
        this.protocol = data.protocol;
        this.round = data.round;
        this.measurementPrefix = data.measurementPrefix;
        this.rtt = data.rtt;
        this.time = data.time;
    }
}
