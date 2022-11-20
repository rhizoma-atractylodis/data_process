package pojo;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

/**
 * @author atractylodis
 */
@Measurement(name = "ping")
public class PingData extends MeasurementData {
    @Override
    public String getType() {
        return "ping";
    }

    @Override
    public void setByData(MeasurementData data) {
        logger.info("ping data");
        super.setByData(data);
    }

//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public String getDestA() {
//        return destA;
//    }
//
//    public void setDestA(String destA) {
//        this.destA = destA;
//    }
//
//    public String getDestB() {
//        return destB;
//    }
//
//    public void setDestB(String destB) {
//        this.destB = destB;
//    }
//
//    public String getDestC() {
//        return destC;
//    }
//
//    public void setDestC(String destC) {
//        this.destC = destC;
//    }
//
//    public String getDestD() {
//        return destD;
//    }
//
//    public void setDestD(String destD) {
//        this.destD = destD;
//    }
//
//    public String getProtocol() {
//        return protocol;
//    }
//
//    public void setProtocol(String protocol) {
//        this.protocol = protocol;
//    }
//
//    public int getRound() {
//        return round;
//    }
//
//    public void setRound(int round) {
//        this.round = round;
//    }
//
//    public String getMeasurementPrefix() {
//        return measurementPrefix;
//    }
//
//    public void setMeasurementPrefix(String measurementPrefix) {
//        this.measurementPrefix = measurementPrefix;
//    }
//
//    public double getRtt() {
//        return rtt;
//    }
//
//    public void setRtt(double rtt) {
//        this.rtt = rtt;
//    }
//
//    public Instant getTime() {
//        return time;
//    }
//
//    public void setTime(Instant time) {
//        this.time = time;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getRegion() {
//        return region;
//    }
//
//    public void setRegion(String region) {
//        this.region = region;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }

    @Override
    public String toString() {
        return super.toString();
    }
}
