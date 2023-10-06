package com.rock.ai.bot.service;

import com.aliyun.tea.TeaException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import com.dingtalk.open.app.api.models.bot.MessageContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

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
    private GptService gptService;

    public String send(ChatbotMessage message) throws Exception {
        String userId = message.getSenderStaffId();

        MessageContent messageText = message.getText();

        String chat = gptService.chat(messageText.getContent(), userId);


        DingTalkClient client = new DefaultDingTalkClient(message.getSessionWebhook());
        try {

            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();

            text.setContent(chat + " \n " + "@" + userId);
            request.setText(text);
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            System.out.println(userId);
            at.setAtUserIds(Arrays.asList(userId));
//           isAtAll类型如果不为Boolean，请升级至最新SDK
            at.setIsAtAll(false);
            request.setAt(at);
            OapiRobotSendResponse response = client.execute(request);
            response.getRequestId();
        } catch (TeaException e) {
            log.error("RobotGroupMessagesService_send orgGroupSendWithOptions throw TeaException, errCode={}, " +
                    "errorMessage={}", e.getCode(), e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("RobotGroupMessagesService_send orgGroupSendWithOptions throw Exception", e);
            throw e;
        }

        return null;
    }
}
