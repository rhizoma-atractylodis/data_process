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
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import store.impl.RedisStore;

import java.util.HashMap;
import java.util.Map;

public class DefaultIPLocation implements IPLocation {
    private RedisStore redis;
    private Logger logger = LoggerFactory.getLogger(IPLocation.class);

    public DefaultIPLocation() {
        this.redis = new RedisStore();
    }

    @Override
    public Map<String, Object> LocateBySingleIP(String ip) {
        Map<String, String> replacer = new HashMap<>();
        replacer.put("{host}", Constants.DEFAULT_IP_SEARCH_SERVERS);
        replacer.put("{ip}", ip);
        Object o = redis.get(ip);
        Map<String, Object> result = ((Map<String, Object>) o);
        if (result == null) {
            try {
                String api = StringUtil.dynamicStringReplace(Constants.IP_SEARCH_URL + Constants.SINGLE_IP_API, replacer);
                String response = HttpClientUtil.getRequest(api);
                result = JsonUtil.fromJson(response);
            } catch (TargetNotFoundException e) {
                logger.error("arg replace error: "+e.getMessage());
            } catch (RestApiException e) {
                logger.error("request error: "+e.getMessage());
            }
        } else {
            logger.info("read from redis");
        }
        return result;
    }

    @Override
    public Map<String, Object> LocateBySingleIPWithLoadBalance(String ip) {
        return null;
    }
}
