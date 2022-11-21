package data.impl;

import base.Constants;
import base.HttpClientUtil;
import base.JsonUtil;
import base.StringUtil;
import data.IPLocation;
import exception.RestApiException;
import exception.TargetNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DefaultIPLocation implements IPLocation {
    private Logger logger = LoggerFactory.getLogger(IPLocation.class);

    @Override
    public Map<String, Object> LocateBySingleIP(String ip) {
        Map<String, String> replacer = new HashMap<>();
        replacer.put("{host}", Constants.DEFAULT_IP_SEARCH_SERVERS);
        replacer.put("{ip}", ip);
        Map<String, Object> result = null;
        try {
            String api = StringUtil.dynamicStringReplace(Constants.IP_SEARCH_URL + Constants.SINGLE_IP_API, replacer);
            String response = HttpClientUtil.getRequest(api);
            result = JsonUtil.fromJson(response);
        } catch (TargetNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RestApiException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> LocateBySingleIPWithLoadBalance(String ip) {
        return null;
    }
}
