package com.rock.ai.bot.feginclient;

import com.rock.ai.bot.config.FastGptFeignConfig;
import com.rock.ai.bot.feginclient.fastgpt.ChatRequest;
import com.rock.ai.bot.feginclient.fastgpt.ChatResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * fastgpt对外接口
 * 接口详情参考文档
 * https://doc.fastgpt.run/docs/development/openapi/#%E6%8E%A5%E5%8F%A3
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@FeignClient(value = "fastgpt", url = "${fastgpt.baseurl:}", configuration = FastGptFeignConfig.class)
public interface FastGptClient {
    /**
     * 对话接口
     *
     * @param req 对话请求
     * @return 对话响应
     */
    @PostMapping(value = "/v1/chat/completions")
    ChatResponse chat(@RequestBody ChatRequest req);
}
