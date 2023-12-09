package com.rock.ai.bot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Rock.L
 * @date: 2023/12/7
 */
@Getter
@AllArgsConstructor
public enum DingChatType {
    /**
     * 单聊
     */
    SINGLE("1"),
    /**
     * 群聊
     */
    GROUP("2"),

    /**
     * 两人群
     * 钉联高级功能 10万年费起
     */
    SINGLE_GROUP("3"),

    ;
    private final String type;
}
