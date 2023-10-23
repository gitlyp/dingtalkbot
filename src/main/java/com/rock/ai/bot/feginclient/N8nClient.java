package com.rock.ai.bot.feginclient;

import com.rock.ai.bot.feginclient.n8n.N8nQueryOrderResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Rock.L
 * @date: 2023/10/22
 */
@FeignClient(value = "n8n", url = "${n8n.baseurl:}")
public interface N8nClient {

    /**
     * 查询订单
     *
     * @param content 请求内容
     * @return 对话响应
     */
    @GetMapping(value = "/queryOrder")
    N8nQueryOrderResp queryOrder(@RequestParam("content") String content);

}
