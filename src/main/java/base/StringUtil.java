package base;

import exception.TargetNotFoundException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String dynamicStringReplace(String s, Map<String, String> map) throws TargetNotFoundException {
        Matcher matcher = regMatch("\\{([^}])*\\}", s);
        while (matcher.find()) {
            String victim = s.substring(matcher.start(), matcher.end());
            if (map.getOrDefault(victim, Constants.NOT_FOUND).equals(Constants.NOT_FOUND)) {
                throw new TargetNotFoundException(victim);
            } else {
                s = s.replace(victim, map.get(victim));
            }
            matcher = regMatch("\\{([^}])*\\}", s);
        }
        return s;
    }

    private static Matcher regMatch(String reg, String target) {
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(target);
    }
}
