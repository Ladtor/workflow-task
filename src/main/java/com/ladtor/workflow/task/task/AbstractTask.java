package com.ladtor.workflow.task.task;

import com.alibaba.fastjson.JSONObject;
import com.ladtor.workflow.task.bo.Key;
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

    public void success(Key key, JSONObject result){
        try {
            sender.success(key, result);
        } catch (Exception e) {
            log.error("notify success error ", e);
        }
    }

    public void fail(Key key, JSONObject result){
        try {
            sender.fail(key, result);
        } catch (Exception e) {
            log.error("notify fail error ", e);
        }
    }

    public abstract void execute(Key key, JSONObject params);


    public String getKey(){
        return key;
    }

}
