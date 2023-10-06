package com.rock.ai.bot.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Slf4j
public class FastGptRequestInterceptor implements RequestInterceptor {

    @Value("${fastgpt.apikey}")
    private String auth;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 统一添加授权信息
        String value = "Bearer " + auth;
        requestTemplate.header("Authorization", value);
    }
}
