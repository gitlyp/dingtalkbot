package com.rock.ai.bot.listener;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import com.dingtalk.open.app.api.models.bot.MessageContent;
import com.rock.ai.bot.constant.DingChatType;
import com.rock.ai.bot.service.RobotGroupMessagesService;
import com.rock.ai.bot.service.RobotSingleMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 机器人消息回调监听
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Component
@Slf4j
public class ChatBotCallbackListener implements OpenDingTalkCallbackListener<ChatbotMessage, JSONObject> {

    @Resource
    private RobotSingleMessagesService robotSingleMessagesService;

    @Resource
    private RobotGroupMessagesService robotGroupMessagesService;

    @Override
    public JSONObject execute(ChatbotMessage message) {
        try {
            MessageContent text = message.getText();
            if (text != null) {
                String msg = text.getContent();
                log.info("receive bot message from userNick={}, userId={}, chatType={}, msg={}", message.getSenderNick(), message.getSenderStaffId(), message.getConversationType(), msg);
                try {
                    //发送机器人消息
                    if (DingChatType.GROUP.getType().equals(message.getConversationType())) {
                        robotGroupMessagesService.wsSend(message);
                    } else {
                        robotSingleMessagesService.wsSend(message);
                    }

                } catch (Exception e) {
                    log.error("send message by robot error:" + e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error("receive group message by robot error:" + e.getMessage(), e);
        }
        return new JSONObject();
    }
}
