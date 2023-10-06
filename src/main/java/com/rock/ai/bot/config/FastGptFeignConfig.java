package com.rock.ai.bot.config;

import com.rock.ai.bot.interceptor.FastGptRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fastGPT feign配置
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Configuration
public class FastGptFeignConfig {

    @Bean
    public FastGptRequestInterceptor feignInterceptor() {
        return new FastGptRequestInterceptor();
    }

}
