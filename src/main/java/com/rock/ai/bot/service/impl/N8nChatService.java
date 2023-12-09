package com.rock.ai.bot.service.impl;

import com.rock.ai.bot.feginclient.N8nClient;
import com.rock.ai.bot.feginclient.n8n.N8nChatInput;
import com.rock.ai.bot.feginclient.n8n.N8nChatOutput;
import com.rock.ai.bot.service.ChatInterface;
import com.rock.ai.bot.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * fastGPT 聊天实现
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Service
@Slf4j
public class N8nChatService implements ChatInterface<N8nChatInput, N8nChatOutput> {

    @Resource
    private N8nClient n8nClient;


    @Override
    public N8nChatOutput chat(N8nChatInput input) {
        String[] chat = StringUtil.getChat(input.getMessageContent().getContent());
        input.setCmd(chat[0]);
        input.setContent(chat[1]);

        N8nChatOutput resp = null;

        try {
            resp = n8nClient.chat(input);
        } catch (Exception e) {
            log.error("n8nClient_chat error", e);
        }

        return resp;
    }
}
