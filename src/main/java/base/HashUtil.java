package base;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class HashUtil {
    public static String hashMurMurHash(String key, int seed) {
        HashFunction hashFunction = Hashing.murmur3_128(seed);
        return hashFunction.hashString(key, StandardCharsets.UTF_8).toString();
    }

    public static String hashMurMurHash(String key) {
        Random random = new Random(System.currentTimeMillis());
        return hashMurMurHash(key, random.nextInt());
    }
}
