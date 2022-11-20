package net;

import org.junit.Test;
import pojo.MeasurementData;
import pojo.PingData;

public class ServerTest {
    @Test
    public void cast() {
        MeasurementData data = new PingData();
        PingData ping = (PingData) data;
//        System.out.println();
        data.setByData(new PingData());
    }
}
