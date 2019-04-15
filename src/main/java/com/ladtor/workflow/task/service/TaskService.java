package com.ladtor.workflow.task.service;

import com.alibaba.fastjson.JSONObject;
import com.ladtor.workflow.task.bo.Key;
import com.ladtor.workflow.task.sender.Sender;
import com.ladtor.workflow.task.task.AbstractTask;
import com.ladtor.workflow.task.task.TaskFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liudongrong
 * @date 2019/2/9 18:58
 */
@Service
public class TaskService {

    @Autowired
    ThreadPoolExecutor taskExecutor;

    @Autowired
    private Sender sender;

    public void execute(String taskKey, Key key, JSONObject params) {
        AbstractTask task = TaskFactory.getTask(taskKey);
        if (task == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", String.format("Task %s Not Found", taskKey));
            try {
                sender.fail(key, jsonObject);
            } catch (IOException ignored) {

            }
            return;
        }
        taskExecutor.execute(()-> task.execute(key, params));
    }
}
