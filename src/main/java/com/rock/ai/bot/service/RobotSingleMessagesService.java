package com.rock.ai.bot.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalkrobot_1_0.Client;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOHeaders;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOResponse;
import com.aliyun.tea.TeaException;
import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import com.rock.ai.bot.common.DingChatMessage;
import com.rock.ai.bot.common.DingChatSession;
import com.rock.ai.bot.config.DingBotConfig;
import com.rock.ai.bot.constant.DingChatType;
import com.rock.ai.bot.feginclient.n8n.N8nChatInput;
import com.rock.ai.bot.feginclient.n8n.N8nChatOutput;
import com.rock.ai.bot.service.impl.N8nChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 机器人单聊回复
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Slf4j
@Service
public class RobotSingleMessagesService {
    private Client robotClient;
    private final AccessTokenService accessTokenService;

    @Resource
    private DingBotConfig dingBotConfig;

    @Autowired
    public RobotSingleMessagesService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Resource
    private N8nChatService n8nChatService;

    @PostConstruct
    public void init() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        robotClient = new Client(config);
    }


    public Boolean wsSend(ChatbotMessage message) {

        String senderId = message.getSenderStaffId();
        String senderNick = message.getSenderNick();

        List<String> atUserId = Collections.singletonList(senderId);

        DingChatSession chatSession = new DingChatSession();
        chatSession.setChatType(DingChatType.SINGLE.getType());
        chatSession.setSenderId(senderId);
        chatSession.setSenderNick(senderNick);

        N8nChatInput input = new N8nChatInput();
        input.setMessageContent(message.getText());
        input.setChatSession(chatSession);

        N8nChatOutput chatMessage = n8nChatService.chat(input);

        if (chatMessage != null && chatMessage.getChatMessage() != null) {
            return sendSingleMessage(chatMessage.getChatMessage(), atUserId);
        } else {
            return true;
        }
    }


    public Boolean apiSend(N8nChatOutput n8nChatOutput) {
        DingChatMessage chatMessage = n8nChatOutput.getChatMessage();
        DingChatSession chatSession = n8nChatOutput.getChatSession();
        List<String> atUserId = Collections.singletonList(chatSession.getSenderId());
        return sendSingleMessage(chatMessage, atUserId);
    }

    private Boolean sendSingleMessage(DingChatMessage chatMessage, List<String> atUserId) {
        BatchSendOTOHeaders headers = new BatchSendOTOHeaders();
        headers.setXAcsDingtalkAccessToken(accessTokenService.getAccessToken());

        BatchSendOTORequest request = new BatchSendOTORequest();
        // 不同类型消息 传参方式不同 有坑 群聊单聊还不一样（吐槽）
        request.setMsgKey(chatMessage.getMsgType().getSingle());
        request.setRobotCode(dingBotConfig.getRobotCode());
        request.setUserIds(atUserId);

        JSONObject msgParam = new JSONObject();
        msgParam.put("title", chatMessage.getTitle());
        msgParam.put("text", chatMessage.getText());
        request.setMsgParam(msgParam.toJSONString());

        try {
            BatchSendOTOResponse response = robotClient.batchSendOTOWithOptions(request,
                    headers, new com.aliyun.teautil.models.RuntimeOptions());
            if (Objects.isNull(response) || Objects.isNull(response.getBody())) {
                log.error("RobotSingleMessagesService_send  return error, response={}",
                        response);
                return false;
            }
            return true;
        } catch (TeaException e) {
            log.error("RobotSingleMessagesService_send throw TeaException, errCode={}, errorMessage={}",
                    e.getCode(), e.getMessage(), e);
            return false;
        } catch (Exception e) {
            log.error("RobotSingleMessagesService_send throw Exception", e);
            return false;
        }
    }
}
