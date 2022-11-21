package data;

import java.util.Map;

public interface IPLocation {
    Map<String, Object> LocateBySingleIP(String ip);
    Map<String, Object> LocateBySingleIPWithLoadBalance(String ip);
}
