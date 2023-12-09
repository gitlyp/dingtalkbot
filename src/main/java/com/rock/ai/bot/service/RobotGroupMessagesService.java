package com.rock.ai.bot.service;

import com.aliyun.tea.TeaException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import com.dingtalk.open.app.api.models.bot.MentionUser;
import com.dingtalk.open.app.api.models.bot.MessageContent;
import com.rock.ai.bot.common.DingChatMessage;
import com.rock.ai.bot.common.DingChatSession;
import com.rock.ai.bot.feginclient.n8n.N8nChatInput;
import com.rock.ai.bot.feginclient.n8n.N8nChatOutput;
import com.rock.ai.bot.service.impl.N8nChatService;
import com.rock.ai.bot.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * bot群聊回复
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Slf4j
@Service
public class RobotGroupMessagesService {

    @Resource
    private N8nChatService n8nChatService;

    /**
     * websocket 对话
     * @param message
     * @return
     */
    public Boolean wsSend(ChatbotMessage message){
        String senderId = message.getSenderStaffId();
        String senderNick = message.getSenderNick();

        List<MentionUser> atUsers = message.getAtUsers();
        atUsers.removeIf(mentionUser -> message.getChatbotUserId().equals(mentionUser.getDingtalkId()));
        List<String> atUserIds = atUsers.stream().map(MentionUser::getDingtalkId).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(atUserIds)) {
            atUserIds = Collections.singletonList(senderId);
        }
        String sessionWebhook = message.getSessionWebhook();

        DingChatSession session = new DingChatSession();
        session.setAtUserIds(atUserIds);
        session.setSessionWebHook(sessionWebhook);

        N8nChatInput input = new N8nChatInput();
        input.setChatSession(session);
        input.setMessageContent(message.getContent());

        N8nChatOutput output = n8nChatService.chat(input);

        log.info("webhook={},atUserId={},senderId={},senderNick={}",
                sessionWebhook, atUserIds, senderId, senderNick);

        if (output != null && output.getChatMessage() != null) {
            return sendGroupMessage(output.getChatMessage(), session);
        } else {
            return null;
        }
    }

    /**
     * api回调对话
     * @param n8nChatOutput
     * @return
     */
    public Boolean apiSend(N8nChatOutput n8nChatOutput) {
        DingChatMessage chatMessage = n8nChatOutput.getChatMessage();
        DingChatSession chatSession = n8nChatOutput.getChatSession();
        return sendGroupMessage(chatMessage, chatSession);
    }

    private Boolean sendGroupMessage(DingChatMessage chatMessage, DingChatSession session) {
        DingTalkClient client = new DefaultDingTalkClient(session.getSessionWebHook());
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        // 不同类型消息 传参方式不同 有坑 群聊单聊还不一样（吐槽）
        request.setMsgtype(chatMessage.getMsgType().getGroup());

        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(chatMessage.getTitle());
        markdown.setText(chatMessage.getText());
        request.setMarkdown(markdown);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        //isAtAll类型如果不为Boolean，请升级至最新SDK
        at.setIsAtAll(false);
        at.setAtUserIds(session.getAtUserIds());
        request.setAt(at);
        try {
            OapiRobotSendResponse response = client.execute(request);
            return response.isSuccess();
        } catch (TeaException e) {
            log.error("RobotGroupMessagesService_sent throw TeaException, errCode={},errorMessage={}",
                    e.getCode(), e.getMessage(), e);
            return false;
        } catch (Exception e) {
            log.error("RobotGroupMessagesService_send throw Exception", e);
            return false;
        }
    }

}
