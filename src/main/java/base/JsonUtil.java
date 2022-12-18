package base;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static Map<String, Object> fromJson(String json) {
        Gson gson = new Gson();
        Map<String, Object> result = null;
        try {
            result = gson.fromJson(json, Map.class);
        } catch (Exception e) {
            System.out.println("json error: " + json);
        }
        return result;
    }

    public static List<Object> fromJsonList(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, List.class);
    }

    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static Map<String, Object> mapDeepcopy(Map<String, Object> map) {
        return JsonUtil.fromJson(JsonUtil.toJson(map));
    }
}
