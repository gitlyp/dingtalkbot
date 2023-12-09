package com.rock.ai.bot.service.impl;

import com.rock.ai.bot.config.DingBotConfig;
import com.rock.ai.bot.feginclient.FastGptClient;
import com.rock.ai.bot.feginclient.fastgpt.*;
import com.rock.ai.bot.service.ChatInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * fastGPT 聊天实现
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Service
@Slf4j
public class FastGptService implements ChatInterface<FastGptInput, FastGptOutput> {

    @Resource
    private DingBotConfig dingBotConfig;

    @Resource
    private FastGptClient fastGptClient;

    @Override
    public FastGptOutput chat(FastGptInput input) {
        if (!dingBotConfig.getGptEnabled()) {
            return new FastGptOutput(dingBotConfig.getGptResp());
        }

        ChatRequest req = buildChatRequest(input.getUserId(), input.getContent());
        ChatResponse resp = null;
        try {
            resp = fastGptClient.chat(req);
        } catch (Exception e) {
            log.error("fastGptClient_chat error", e);
        }

        if (resp != null && !CollectionUtils.isEmpty(resp.getChoices())) {
            Choice choice = resp.getChoices().get(0);

            Message message = choice.getMessage();
            if (message != null && message.getContent() != null) {
                return new FastGptOutput(message.getContent());
            }
        }

        return new FastGptOutput(dingBotConfig.getGptResp());
    }

    /**
     * 构建对话请求参数
     *
     * @param userId  用户id
     * @param content 对话内容
     * @return 对话请求
     */
    private ChatRequest buildChatRequest(String userId, String content) {
        ChatRequest req = new ChatRequest();
        // 用 userId 作为 chatId 方便 gpt 记住对话类型另外也无需维护 chatID
        req.setChatId(userId);
        req.setDetail(false);
        req.setStream(false);

        List<Message> messages = new ArrayList<>(1);
        Message currentMessage = new Message();
        currentMessage.setContent(content);
        currentMessage.setRole("user");
        messages.add(currentMessage);
        req.setMessages(messages);

        return req;
    }
}
