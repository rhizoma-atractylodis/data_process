package base;

/**
 * @author atractylodis
 */
public class Constants {
    // etcd configuration
//    public static String ETCD_KEY_DATABASE = "/DB/"+System.getenv("M_DB_NAME");
    public static String ETCD_KEY_DATABASE = "/DB/";
//    public static String ETCD_HOST = System.getenv("M_CONFIG_ENDPOINT");
    public static String ETCD_HOST = "http://192.168.0.191:8086";
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
    public static String INFLUXDB_URL = "http://192.168.0.191:8086";
    public static String INFLUXDB_TOKEN = "";
    public static String INFLUXDB_ORG = "club203";
    public static String INFLUXDB_BUCKET = "test-frankfurt";
    public static int CLIENT_POOL_SIZE = 96;
    public static int BATCH_SIZE = 5000;
    public static int FLUSH_INTERVAL = 1000;
    public static int JITTER_INTERVAL = 0;
    public static int RETRY_INTERVAL = 5000;
    public static int MAX_RETRY_TIME = 100000;
    public static int MAX_RETRIES = 50;
    public static int MAX_RETRY_DELAY = 125000;
//    public static int EXPONENTIAL_BASE = 2;

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
}
