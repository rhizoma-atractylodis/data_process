package base;

import com.google.gson.Gson;

import java.util.Map;

public class JsonUtil {
    public static Map<String, Object> fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Map.class);
    }
}
