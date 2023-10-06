package com.rock.ai.bot.service;

/**
 * @author: Rock.L
 * @date: 2023/10/6
 */
public interface GptService {

    /**
     * 对话
     *
     * @param userId  用户id
     * @param content 聊天内容
     * @return 聊天响应
     */
    String chat(String userId, String content);
}
