package com.rock.ai.bot.service.impl;

import com.rock.ai.bot.config.DingBotConfig;
import com.rock.ai.bot.service.ChatInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Rock.L
 * @date: 2023/12/6
 */
@Service
@Slf4j
public class SimpleChatService implements ChatInterface<String, String> {

    @Resource
    private DingBotConfig dingBotConfig;

    /**
     * 对话能容测试
     *
     * @param userId  用户id
     * @param content 聊天内容
     * @return 聊天响应
     */
    @Override
    public String chat(String content) {
        if (!dingBotConfig.getGptEnabled()) {
            return dingBotConfig.getGptResp();
        }
        content = content.trim();
        log.info("input={}", content);
        return "## 好好说话 " +
                "\n\n [钉钉消息发送](dtmd://dingtalkclient/sendMessage?content=%E5%8F%91%E7%A5%A8%E8%A6%81%E6%99%AE%E7%A5%A8%E8%BF%98%E6%98%AF%E4%B8%93%E7%A5%A8)" +
                "\n\n [召唤客服](dtmd://dingtalkclient/sendMessage?content=%40%E5%BB%96%E8%BF%9C%E4%BD%A9)" +
                "\n\n [私聊机器人](dingtalk://dingtalkclient/action/sendmsg?dingtalk_id=$:LWCP_v1:$lGDj/ZVTpuNGrZVduW0eOBofMZzefqz8) " +
                "\n\n [私聊客服](dingtalk://dingtalkclient/action/sendmsg?dingtalk_id=1ub-ih4ag7o9sp)";
    }
}
