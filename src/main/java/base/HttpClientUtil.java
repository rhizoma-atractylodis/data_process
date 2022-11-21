package base;

import exception.RestApiException;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientUtil {
    public static String getRequest(String path, List<NameValuePair> parametersBody) throws RestApiException {
        HttpGet get = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(path);
            uriBuilder.setParameters(parametersBody);
            get = new HttpGet(uriBuilder.build());
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new RuntimeException("Could not access protected resource. Server returned http code: " + code);
            return EntityUtils.toString(response.getEntity());
        }
        catch (ClientProtocolException e) {
            throw new RestApiException("postRequest -- Client protocol exception! -- " + e.getMessage());
        }
        catch (IOException e) {
            throw new RestApiException("postRequest -- IO error!" + e.getMessage());
        } catch (URISyntaxException e) {
            throw new RestApiException("postRequest -- URI syntax error!" + e.getMessage());
        } finally {
            if (get != null) {
                get.releaseConnection();
            }
        }
    }

    public static String getRequest(String path) throws RestApiException {
        return getRequest(path, new ArrayList<>());
    }

    public static String postForm(String path, List<NameValuePair> parametersBody) throws RestApiException {
        HttpEntity entity = new UrlEncodedFormEntity(parametersBody, Charsets.UTF_8);
        return postRequest(path, "application/x-www-form-urlencoded", entity);
    }

    public static String postJSON(String path, String json) throws RestApiException {
        StringEntity entity = new StringEntity(json, Charsets.UTF_8);
        return postRequest(path, "application/json", entity);
    }

    public static String postRequest(String path, String mediaType, HttpEntity entity) throws RestApiException {
        HttpPost post = new HttpPost(path);
        post.addHeader("Content-Type", mediaType);
        post.addHeader("Accept", "application/json");
        post.setEntity(entity);
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new RestApiException(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            throw new RestApiException("postRequest -- Client protocol exception! -- " + e.getMessage());
        } catch (IOException e) {
            throw new RestApiException("postRequest -- IO error! -- " + e.getMessage());
        } finally {
            post.releaseConnection();
        }
    }
}
