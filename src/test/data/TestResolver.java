package data;

import data.impl.DefaultDataResolver;
import org.junit.Test;

public class TestResolver {
    @Test
    public void testResolver() {
        String data = "ping,host=cb52f4c932cb,influx_data_bucket=FK-SDZX-EasternEurope-Origin proto_base64=Ci0IydLYiMUwGgw4My4xOS42NS4xMzMgASgFMhJFYXN0ZXJuRXVyb3BlLVBpbmcVCCxCQg== 1667807805234783964";
        DefaultDataResolver resolver = new DefaultDataResolver();
        resolver.resolveLineData(data);
    }
}
