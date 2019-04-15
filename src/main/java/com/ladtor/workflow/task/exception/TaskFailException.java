package com.ladtor.workflow.task.exception;

import com.alibaba.fastjson.JSONObject;

public class TaskFailException extends Exception {
    private JSONObject params;

    public TaskFailException(JSONObject params) {
        this.params = params;
    }

    public JSONObject getParams() {
        return params;
    }
}
