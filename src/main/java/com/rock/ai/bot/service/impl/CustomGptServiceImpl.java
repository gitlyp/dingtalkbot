package com.rock.ai.bot.service.impl;

import com.rock.ai.bot.config.DingBotConfig;
import com.rock.ai.bot.service.GptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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


    @Override
    public String chat(String content, String userId) {
        if (!dingBotConfig.getGptEnabled()) {
            return dingBotConfig.getGptResp();
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
