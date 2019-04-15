package com.ladtor.workflow.task.task;

import com.alibaba.fastjson.JSONObject;
import com.ladtor.workflow.task.bo.Key;
import com.ladtor.workflow.task.exception.TaskFailException;
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
    public JSONObject doExecute(JSONObject params) {
        JSONObject result = new JSONObject();
        result.put("message", "Ok");
        return result;
    }
}
