package com.ladtor.workflow.task.sender;

import com.alibaba.fastjson.JSONObject;
import com.ladtor.workflow.task.bo.Key;
import com.ladtor.workflow.task.config.properties.HttpSenderProperties;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liudongrong
 * @date 2019/2/5 16:12
 */
public class HttpSender implements Sender {

    public static final String POST = "POST";
    private final HttpClient httpClient;
    private final HttpSenderProperties properties;

    public HttpSender(HttpSenderProperties properties) {
        this.properties = properties;
        this.httpClient = HttpClients.createDefault();
    }

    @Override
    public void success(Key key, JSONObject result) throws IOException {
        HttpUriRequest httpUriRequest = getHttpUriRequest(getSuccessUrl(key), result);
        httpClient.execute(httpUriRequest);
    }

    @Override
    public void fail(Key key, JSONObject result) throws IOException {
        HttpUriRequest httpUriRequest = getHttpUriRequest(getFailUrl(key), result);
        httpClient.execute(httpUriRequest);
    }

    private String getFailUrl(Key key) {
        return String.format("%s/node/fail/%s/%d/%d/%s", properties.getUrl(), key.getSerialNo(), key.getVersion(), key.getRunVersion(), key.getNodeId());
    }

    private String getSuccessUrl(Key key) {
        return String.format("%s/node/success/%s/%d/%d/%s", properties.getUrl(), key.getSerialNo(), key.getVersion(), key.getRunVersion(), key.getNodeId());
    }

    private HttpUriRequest getHttpUriRequest(String url, JSONObject params) {
        return RequestBuilder
                .create(HttpSender.POST)
                .addHeader("Content-Type", ContentType.APPLICATION_JSON.toString())
                .setUri(url)
                .setEntity(new StringEntity(params.toJSONString(), "UTF-8"))
                .build();
    }

    private NameValuePair[] getNameValuePair(Map<String, Object> map) {
        List<BasicNameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            String name = e.getKey();
            String value = e.getValue().toString();
            BasicNameValuePair pair = new BasicNameValuePair(name, value);
            params.add(pair);
        }
        return params.toArray(new BasicNameValuePair[params.size()]);
    }
}
