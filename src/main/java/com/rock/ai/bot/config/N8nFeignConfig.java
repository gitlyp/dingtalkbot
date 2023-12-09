package com.rock.ai.bot.config;

import com.rock.ai.bot.interceptor.FastGptRequestInterceptor;
import com.rock.ai.bot.interceptor.N8nRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fastGPT feign配置
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Configuration
public class N8nFeignConfig {

    @Bean
    public N8nRequestInterceptor n8nFeignInterceptor() {
        return new N8nRequestInterceptor();
    }

}
