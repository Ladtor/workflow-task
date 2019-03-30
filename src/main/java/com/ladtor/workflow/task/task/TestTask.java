package com.ladtor.workflow.task.task;

import com.alibaba.fastjson.JSONObject;
import com.ladtor.workflow.task.bo.Key;
import org.springframework.stereotype.Component;

/**
 * @author liudongrong
 * @date 2019/2/10 16:09
 */
@Component
public class TestTask extends AbstractTask {
    public TestTask() {
        super("testKey");
    }

    @Override
    public void execute(Key key, JSONObject params) {
        JSONObject result = new JSONObject();
        result.put("message", "Ok");
        this.success(key, result);
    }
}
