package com.rock.ai.bot.service.impl;

import com.rock.ai.bot.config.DingBotConfig;
import com.rock.ai.bot.feginclient.N8nClient;
import com.rock.ai.bot.feginclient.n8n.N8nQueryOrderResp;
import com.rock.ai.bot.service.GptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * fastGPT 聊天实现
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Service
@Slf4j
public class CustomGptServiceImpl implements GptService {

    @Resource
    private DingBotConfig dingBotConfig;

    @Resource
    private N8nClient n8nClient;


    private static final Map<String, String> commandMap = new HashMap<>();

    static {
        commandMap.put("/cx", "查询订单");
        commandMap.put("/?", "帮助");
        commandMap.put("/help", "帮助");
    }


    @Override
    public String chat(String content, String userId) {
        if (!dingBotConfig.getGptEnabled()) {
            return dingBotConfig.getGptResp();
        }

        content = content.trim();

        Set<String> set = commandMap.keySet();

        String cmd = null;
        for (String command : set) {
            if (content.startsWith(command)) {
                cmd = command;
                content = content.replace(cmd, "");
            }
        }

        if (cmd != null) {
            if ("/cx".equals(cmd)) {
                N8nQueryOrderResp queryOrderResp = n8nClient.queryOrder(content);
                StringBuilder sb = new StringBuilder();
                if (!StringUtils.isEmpty(queryOrderResp.getTips())) {
                    sb.append(queryOrderResp.getTips());
                }
                if (!StringUtils.isEmpty(queryOrderResp.getText())) {
                    sb.append("\n\n");
                    sb.append(queryOrderResp.getText());
                    sb.append("\n\n");
                }

                if (!StringUtils.isEmpty(queryOrderResp.getSls())) {
                    sb.append("sls查询:\n\n```\n\n");
                    sb.append(queryOrderResp.getSls());
                    sb.append("\n\n```\n\n");
                }

                if (!CollectionUtils.isEmpty(queryOrderResp.getSlsList())) {
                    sb.append("sls查询(可依次查看相关日志):\n\n```\n\n");
                    queryOrderResp.getSlsList().forEach(k -> {
                        sb.append("\n\n```\n\n");
                        sb.append(k);
                        sb.append("\n\n```\n\n");
                    });
                }

                if (!StringUtils.isEmpty(queryOrderResp.getJson())) {
                    sb.append("解析结果:\n\n```\n\n");
                    sb.append(queryOrderResp.getJson());
                    sb.append("\n\n```\n\n");
                }

                if (!StringUtils.isEmpty(queryOrderResp.getJsonTips())) {
                    sb.append("解析结果:\n\n");
                    sb.append(queryOrderResp.getJson());
                    sb.append("\n\n");
                }

                return sb.toString();

            } else {
                StringBuilder sb = new StringBuilder();

                commandMap.forEach((k, v) -> {
                    sb.append(String.format("#### 指令列表\n\n%s %s\n\n", k, v));
                });

                return sb.toString();
            }

        }


        final String chat = content.trim();
        Map<String, String> rltMap = new HashMap<>(10);
        dingBotConfig.getDocumentMap().forEach((k, v) -> {
            if (k.contains(chat)) {
                rltMap.put(k, v);
            }
        });

        if (CollectionUtils.isEmpty(rltMap)) {
            return "暂时无法帮助你，抱歉";
        } else {
            StringBuilder sb = new StringBuilder();

            rltMap.forEach((k, v) -> {
                sb.append(String.format("[%s](%s)\n\n", k, v));
            });

            return sb.toString();
        }


    }
}
