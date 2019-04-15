package com.ladtor.workflow.task.task;

import com.alibaba.fastjson.JSONObject;
import com.ladtor.workflow.task.bo.Key;
import com.ladtor.workflow.task.exception.TaskFailException;
import com.ladtor.workflow.task.sender.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liudongrong
 * @date 2019/2/3 23:00
 */
@Slf4j
public abstract class AbstractTask {
    @Autowired
    private Sender sender;

    private String key;

    public AbstractTask(String key) {
        this.key = key;
    }

    private void success(Key key, JSONObject result) {
        try {
            sender.success(key, result);
        } catch (Exception e) {
            log.error("notify success error ", e);
        }
    }

    private void fail(Key key, JSONObject result) {
        try {
            sender.fail(key, result);
        } catch (Exception e) {
            log.error("notify fail error ", e);
        }
    }

    private void fail(Key key, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        fail(key, jsonObject);
    }

    public void execute(Key key, JSONObject params) {
        try {
            JSONObject result = doExecute(key, params);
            success(key, result);
        } catch (TaskFailException e) {
            fail(key, e.getParams());
        } catch (Exception e) {
            fail(key, e.getMessage());
        }
    }

    protected JSONObject doExecute(Key key, JSONObject params) throws TaskFailException {
        return doExecute(params);
    }

    protected abstract JSONObject doExecute(JSONObject params) throws TaskFailException;


    public String getKey() {
        return key;
    }

}
