package base;

import com.google.gson.Gson;

import java.util.Map;

public class JsonUtil {
    public static Map<String, Object> fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Map.class);
    }

    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static Map<String, Object> mapDeepcopy(Map<String, Object> map) {
        return JsonUtil.fromJson(JsonUtil.toJson(map));
    }
}
