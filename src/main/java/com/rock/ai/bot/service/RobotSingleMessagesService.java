package com.rock.ai.bot.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalkrobot_1_0.Client;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOHeaders;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOResponse;
import com.aliyun.tea.TeaException;
import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import com.dingtalk.open.app.api.models.bot.MessageContent;
import com.rock.ai.bot.config.DingBotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
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
    private GptService fastGptService;

    @PostConstruct
    public void init() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        robotClient = new Client(config);
    }


    public String send(ChatbotMessage message) throws Exception {

        MessageContent messageText = message.getText();
        String textContent = messageText.getContent();
        String userId = message.getSenderStaffId();

        BatchSendOTOHeaders batchSendOTOHeaders = new BatchSendOTOHeaders();
        batchSendOTOHeaders.setXAcsDingtalkAccessToken(accessTokenService.getAccessToken());

        BatchSendOTORequest batchSendOTORequest = new BatchSendOTORequest();
        // 不同类型消息 传参方式不同 有坑 群聊单聊还不一样（吐槽）
        batchSendOTORequest.setMsgKey("sampleMarkdown");
        batchSendOTORequest.setRobotCode(dingBotConfig.getRobotCode());
        batchSendOTORequest.setUserIds(Collections.singletonList(userId));

        JSONObject msgParam = new JSONObject();
        String chat = fastGptService.chat(textContent, userId);
        msgParam.put("title", textContent);
        msgParam.put("text", chat);
        batchSendOTORequest.setMsgParam(msgParam.toJSONString());

        try {
            BatchSendOTOResponse batchSendOTOResponse = robotClient.batchSendOTOWithOptions(batchSendOTORequest,
                    batchSendOTOHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            if (Objects.isNull(batchSendOTOResponse) || Objects.isNull(batchSendOTOResponse.getBody())) {
                log.error("RobotSingleMessagesService_send batchSendOTOWithOptions return error, response={}",
                        batchSendOTOResponse);
                return null;
            }
            return batchSendOTOResponse.getBody().getProcessQueryKey();
        } catch (TeaException e) {
            log.error("RobotSingleMessagesService_send batchSendOTOWithOptions throw TeaException, errCode={}, " +
                    "errorMessage={}", e.getCode(), e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("RobotSingleMessagesService_send batchSendOTOWithOptions throw Exception", e);
            throw e;
        }
    }
}
