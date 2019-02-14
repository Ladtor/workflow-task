package com.ladtor.workflow.task.config;

import com.ladtor.workflow.task.config.properties.HttpSenderProperties;
import com.ladtor.workflow.task.sender.HttpSender;
import com.ladtor.workflow.task.sender.Sender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liudongrong
 * @date 2019/2/5 16:18
 */
@Configuration
@EnableConfigurationProperties(HttpSenderProperties.class)
public class SenderConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Sender sender(HttpSenderProperties httpSenderProperties){
        return new HttpSender(httpSenderProperties);
    }
}
