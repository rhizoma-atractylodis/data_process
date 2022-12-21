package base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author atractylodis
 */
public class Constants {
    // etcd configuration
    public static String ETCD_KEY_DATABASE = "/DB/"+System.getenv("M_DB_NAME");
    public static String ETCD_HOST = System.getenv("M_CONFIG_ENDPOINT");

    // redis configuration
    public static String REDIS_HOST = "localhost";
    public static int REDIS_PORT = 6379;
    public static int REDIS_TIMEOUT = 100000;
    public static String REDIS_PASSWORD = "";
    public static int REDIS_MAX_TOTAL = 16;
    public static int REDIS_MAX_IDLE = 8;
    public static int REDIS_MIN_IDLE = 0;
    public static boolean REDIS_BLOCK_WHEN_EXHAUSTED = true;
    public static int REDIS_MAX_WAIT_MILLS = -1;
    public static boolean REDIS_TEST_ON_BORROW = false;
    public static boolean REDIS_TEST_ON_RETURN = false;
    public static boolean REDIS_JMX_ENABLE = true;
    public static boolean REDIS_TEST_WHILE_IDLE = false;
    public static int REDIS_TIME_BETWEEN_EVICTION_RUNS_MILLS = -1;
    public static int REDIS_MIN_EVICTABLE_IDLE_TIME_MILLS = 1800000;
    public static int REDIS_NUM_TESTS_PER_EVICTION_RUN = 3;

    public static final String KEY_INFLUXDB_URL = "influxdb_url";
    public static final String KEY_INFLUXDB_TOKEN = "influxdb_token";
    public static final String KEY_INFLUXDB_ORG = "influxdb_org";
    public static final String KEY_INFLUXDB_BUCKET = "influxdb_bucket";
    public static final String KEY_INFLUXDB_CLIENT_POOL_SIZE = "influxdb_client_number";
    public static final String KEY_INFLUXDB_BATCH_SIZE = "influxdb_batch_size";
    public static final String KEY_INFLUXDB_FLUSH_INTERVAL = "influxdb_flush_interval";
    public static final String KEY_INFLUXDB_JITTER_INTERVAL = "influxdb_jitter_interval";
    public static final String KEY_INFLUXDB_RETRY_INTERVAL = "influxdb_retry_interval";
    public static final String KEY_INFLUXDB_MAX_RETRY_TIME = "influxdb_max_retry_time";
    public static final String KEY_INFLUXDB_MAX_RETRIES = "influxdb_max_retries";
    public static final String KEY_INFLUXDB_MAX_RETRY_DELAY = "influxdb_max_retry_delay";

    // influxdb configuration
    public static String INFLUXDB_URL = "http://127.0.0.1:8086";
    public static String INFLUXDB_TOKEN = "ZaxSSq0KAGPawjylKOslrBTwj3vAMSeLcqXIBzRlxkySb9yzHEUa30zkaWkXk8CdQatPzsUe8j25TSoF9rAaKg==";
    public static String INFLUXDB_ORG = "club203";
    public static String INFLUXDB_BUCKET = "test-frankfurt-origin";
    public static int CLIENT_POOL_SIZE = 96;
    public static int BATCH_SIZE = 5000;
    public static int FLUSH_INTERVAL = 1000;
    public static int JITTER_INTERVAL = 0;
    public static int RETRY_INTERVAL = 5000;
    public static int MAX_RETRY_TIME = 100000;
    public static int MAX_RETRIES = 50;
    public static int MAX_RETRY_DELAY = 125000;

    // socket configuration
    public static String LOCAL_HOST = "127.0.0.1";
    public static int LOCAL_PORT = 12345;

    // netty configuration
    public static int NETTY_PORT = 12345;
    public static int NETTY_BOSS_THREAD_NUMBER = 3;
    public static int NETTY_WORKER_THREAD_NUMBER = Runtime.getRuntime().availableProcessors();

    // mq configuration
    public static int DATA_QUEUE_MAX_LEN = 1024*1024;
    public static int DATA_STORE_WORKER_NUMBER = Runtime.getRuntime().availableProcessors();

    // data type
    public static final String PING_DATA = "ping";
    public static final String TRACE_DATA = "trace";

    // ip search api
    public static String[] IP_SEARCH_SERVERS = new String[]{"", "", ""};
    public static String DEFAULT_IP_SEARCH_SERVERS = "192.168.0.248";
    public static String IP_SEARCH_URL = "http://{host}:8000";
    public static String SINGLE_IP_API = "/club203/ipipnet/{ip}";
    public static String MULTI_IP_API = "";
    public static String CIDR_SEARCH_API = "";
    public static String REVERSE_SEARCH_API = "";

    // regex detection
    public static String NOT_FOUND = "not found";

    // hash ring
    public static int HASH_RING_VIRTUAL_NODE = 10000;

    public static String[] PROTOCOL_MAP = new String[]{"unknown", "icmp", "udp", "tcp_sync", "tcp_ack"};
}
