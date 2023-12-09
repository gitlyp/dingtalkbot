package com.rock.ai.bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Configuration
@Getter
@Setter
public class DingBotConfig {

    @Value("${dingtalk.appKey}")
    private String appKey;

    @Value("${dingtalk.appSecret}")
    private String appSecret;

    @Value("${dingtalk.robot.code}")
    private String robotCode;

    /**
     * 标识 gpt 是否可用
     * 测试模式关闭 gpt 避免浪费token
     */
    @Value("${fastgpt.enabled:false}")
    private Boolean gptEnabled;

    /**
     * gpt 不可用时默认响应或异常统一回复
     */
    @Value("${fastgpt.resp:小助手暂时不可用，请稍后重试。}")
    private String gptResp;
}
