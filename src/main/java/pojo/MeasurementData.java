package pojo;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@Measurement(name = "base")
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDestA() {
        return destA;
    }

    public void setDestA(String destA) {
        this.destA = destA;
    }

    public String getDestB() {
        return destB;
    }

    public void setDestB(String destB) {
        this.destB = destB;
    }

    public String getDestC() {
        return destC;
    }

    public void setDestC(String destC) {
        this.destC = destC;
    }

    public String getDestD() {
        return destD;
    }

    public void setDestD(String destD) {
        this.destD = destD;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getMeasurementPrefix() {
        return measurementPrefix;
    }

    public void setMeasurementPrefix(String measurementPrefix) {
        this.measurementPrefix = measurementPrefix;
    }

    public double getRtt() {
        return rtt;
    }

    public void setRtt(double rtt) {
        this.rtt = rtt;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "MeasurementData{" +
                "host='" + host + '\'' +
                ", destA='" + destA + '\'' +
                ", destB='" + destB + '\'' +
                ", destC='" + destC + '\'' +
                ", destD='" + destD + '\'' +
                ", protocol='" + protocol + '\'' +
                ", round=" + round +
                ", measurementPrefix='" + measurementPrefix + '\'' +
                ", rtt=" + rtt +
                ", time=" + time +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                '}';
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
