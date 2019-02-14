package com.ladtor.workflow.task.task;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author liudongrong
 * @date 2019/2/6 12:11
 */
@Component
public class TaskFactory implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static AbstractTask getTask(String taskKey){
        Map<String, AbstractTask> beansOfType = applicationContext.getBeansOfType(AbstractTask.class);
        for (AbstractTask abstractTask : beansOfType.values()) {
            if (abstractTask.getKey().equals(taskKey)) {
                return abstractTask;
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
