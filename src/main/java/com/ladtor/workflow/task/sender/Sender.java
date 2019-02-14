package com.ladtor.workflow.task.sender;

import com.alibaba.fastjson.JSONObject;
import com.ladtor.workflow.task.bo.Key;

import java.io.IOException;

/**
 * @author liudongrong
 * @date 2019/2/5 16:11
 */
public interface Sender {
    void success(Key key, JSONObject result) throws IOException;

    void fail(Key key, JSONObject result) throws IOException;
}
