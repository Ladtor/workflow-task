package com.ladtor.workflow.task.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ladtor.workflow.task.config.properties.HttpSenderProperties;
import com.ladtor.workflow.task.sender.HttpSender;
import com.ladtor.workflow.task.sender.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liudongrong
 * @date 2019/2/5 16:18
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(HttpSenderProperties.class)
public class SenderConfiguration {

    @Bean
    @ConditionalOnMissingBean(Sender.class)
    public RestTemplate restTemplate() {
        FastJsonHttpMessageConverter jsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("YYYY-MM-dd hh:mm:ss");
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        jsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        jsonHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
        return new RestTemplateBuilder()
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .errorHandler(new DefaultResponseErrorHandler())
                .messageConverters(jsonHttpMessageConverter)
                .additionalInterceptors((ClientHttpRequestInterceptor) (httpRequest, bytes, clientHttpRequestExecution) -> {
                    log.info("HTTP OUT: {} {} {}", httpRequest.getMethod(), httpRequest.getURI(), new String(bytes));
                    return clientHttpRequestExecution.execute(httpRequest, bytes);
                })
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public Sender sender(HttpSenderProperties httpSenderProperties, RestTemplate restTemplate){
        return new HttpSender(restTemplate, httpSenderProperties.getUrl());
    }
}
