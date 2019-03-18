package com.ladtor.workflow.task.sender;

import com.alibaba.fastjson.JSONObject;
import com.ladtor.workflow.task.bo.Key;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author liudongrong
 * @date 2019/2/5 16:12
 */
public class HttpSender implements Sender {

    private final RestTemplate restTemplate;
    private final String remoteUrl;

    public HttpSender(RestTemplate restTemplate, String remoteUrl) {
        this.restTemplate = restTemplate;
        this.remoteUrl = remoteUrl;
    }

    @Override
    public void success(Key key, JSONObject result) {
        restTemplate.postForObject(getSuccessUrl(key), result, JSONObject.class);
    }

    @Override
    public void fail(Key key, JSONObject result) {
        restTemplate.postForObject(getFailUrl(key), result, JSONObject.class);
    }

    private String getFailUrl(Key key) {
        return String.format("%s/node/fail/%s/%d/%d/%s", remoteUrl, key.getSerialNo(), key.getVersion(), key.getRunVersion(), key.getNodeId());
    }

    private String getSuccessUrl(Key key) {
        return String.format("%s/node/success/%s/%d/%d/%s", remoteUrl, key.getSerialNo(), key.getVersion(), key.getRunVersion(), key.getNodeId());
    }
}
