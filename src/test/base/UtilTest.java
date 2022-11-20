package base;

import exception.TargetNotFoundException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilTest {
    @Test
    public void stringUtilTest() {
        String s = "http://{host}:{port}";
        Pattern pattern = Pattern.compile("\\{([^}])*}");
        Matcher matcher = pattern.matcher(s);
        String result = "";
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            s = s.replace(s.substring(matcher.start(), matcher.end()), "1");
            pattern = Pattern.compile("\\{([^}])*}");
            matcher = pattern.matcher(s);
        }
        System.out.println(s);
    }

    @Test
    public void dynamicReplaceTest() throws TargetNotFoundException {
        Map<String, String> map = new HashMap<>();
        map.put("{host}", "127.0.0.1");
        map.put("{port}", "8086");
        String s = StringUtils.dynamicStringReplace("http://{host}:{port}", map);
        System.out.println(s);
    }
}
