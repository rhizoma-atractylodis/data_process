package net;

import base.HttpClientUtil;
import base.JsonUtil;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import data.IPLocation;
import data.impl.DefaultIPLocation;
import exception.RestApiException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HttpClientTest {
    private Logger logger = LoggerFactory.getLogger(HttpClientTest.class);

    @Test
    public void httpClientTest() {
        try {
            String request = HttpClientUtil.getRequest("http://192.168.0.248:8000/club203/ipipnet/123.123.123.123");
            Map<String, Object> map = JsonUtil.fromJson(request);
            logger.info(map.toString());
        } catch (RestApiException e) {
            logger.error(e.getMessage());
        }

    }

    @Test
    public void ipLocate() {
        IPLocation ipLocation = new DefaultIPLocation();
        Map<String, Object> stringObjectMap = ipLocation.LocateBySingleIP("123.123.123.123");
        logger.info(stringObjectMap.toString());
    }
}
