package config.cfg;

import base.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisConfig {
    DEFAULT_REDIS_CONFIG(Constants.REDIS_HOST, Constants.REDIS_PORT, Constants.REDIS_TIMEOUT, Constants.REDIS_PASSWORD,
            Constants.REDIS_MAX_TOTAL, Constants.REDIS_MAX_IDLE, Constants.REDIS_MIN_IDLE,
            Constants.REDIS_BLOCK_WHEN_EXHAUSTED, Constants.REDIS_MAX_WAIT_MILLS, Constants.REDIS_TEST_ON_BORROW,
            Constants.REDIS_TEST_ON_RETURN, Constants.REDIS_JMX_ENABLE, Constants.REDIS_TEST_WHILE_IDLE,
            Constants.REDIS_TIME_BETWEEN_EVICTION_RUNS_MILLS, Constants.REDIS_MIN_EVICTABLE_IDLE_TIME_MILLS,
            Constants.REDIS_NUM_TESTS_PER_EVICTION_RUN);

    private String host;
    private int port;
    private int timeout;
    private String password;
    // jedis pool config
    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    private boolean blockWhenExhausted;
    private int maxWaitMills;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean jmxEnable;
    private boolean testWhileIdle;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private int numTestsPerEvictionRun;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public void setBlockWhenExhausted(boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public void setMaxWaitMills(int maxWaitMills) {
        this.maxWaitMills = maxWaitMills;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public void setJmxEnable(boolean jmxEnable) {
        this.jmxEnable = jmxEnable;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }
}
