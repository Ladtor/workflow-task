package com.ladtor.workflow.task.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liudongrong
 * @date 2019/2/5 18:53
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "workflow.sender.http")
public class HttpSenderProperties {
    private String url;
}
