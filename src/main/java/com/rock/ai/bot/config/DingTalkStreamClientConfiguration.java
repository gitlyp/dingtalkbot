package com.rock.ai.bot.config;

import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.callback.DingTalkStreamTopics;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import com.rock.ai.bot.listener.ChatBotCallbackListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 钉钉流模式客户端配置
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Configuration
public class DingTalkStreamClientConfiguration {

    @Resource
    private DingBotConfig dingBotConfig;

    @Bean(initMethod = "start")
    public OpenDingTalkClient configureStreamClient(@Autowired ChatBotCallbackListener chatBotCallbackListener) throws Exception {
        // 初始化 stream client
        return OpenDingTalkStreamClientBuilder.custom()
                //配置应用的身份信息, 企业内部应用分别为appKey和appSecret, 三方应用为suiteKey和suiteSecret
                .credential(new AuthClientCredential(dingBotConfig.getAppKey(), dingBotConfig.getAppSecret()))
                //注册机器人聊天回调
                .registerCallbackListener(DingTalkStreamTopics.BOT_MESSAGE_TOPIC, chatBotCallbackListener)
                .build();
    }
}
