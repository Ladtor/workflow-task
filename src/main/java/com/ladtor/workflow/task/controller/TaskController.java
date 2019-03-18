package com.ladtor.workflow.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.ladtor.workflow.task.bo.Key;
import com.ladtor.workflow.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liudongrong
 * @date 2019/2/6 12:08
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/execute/{taskKey}/{serialNo}/{version}/{runVersion}/{nodeId}")
    public Key execute(@PathVariable String taskKey, @PathVariable String serialNo, @PathVariable Integer version, @PathVariable Integer runVersion, @PathVariable String nodeId, @RequestBody JSONObject params){
        Key key = Key.builder()
                .serialNo(serialNo)
                .version(version)
                .runVersion(runVersion)
                .nodeId(nodeId)
                .build();
        taskService.execute(taskKey, key, params);
        return key;
    }
}
