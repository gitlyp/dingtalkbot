package com.rock.ai.bot.feginclient;

import com.rock.ai.bot.config.N8nFeignConfig;
import com.rock.ai.bot.contoller.DingCallBackController;
import com.rock.ai.bot.feginclient.n8n.N8nChatInput;
import com.rock.ai.bot.feginclient.n8n.N8nChatOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: Rock.L
 * @date: 2023/10/22
 */
@FeignClient(value = "n8n", url = "${n8n.baseurl:}", configuration = N8nFeignConfig.class)
public interface N8nClient {

    /**
     * n8n单轮对话
     * 响应为空则通过api回调发送内容
     * @see DingCallBackController#n8nSend(com.rock.ai.bot.feginclient.n8n.N8nChatOutput)
     * @param input 请求内容
     * @return 对话响应
     */
    @PostMapping(value = "/chat")
    N8nChatOutput chat(@RequestBody N8nChatInput input);

}
