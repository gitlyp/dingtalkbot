package com.rock.ai.bot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Rock.L
 * @date: 2023/12/6
 */
@Getter
@AllArgsConstructor
public enum DingMsgType {
    /**
     * markdown
     */
    MARKDOWN("sampleMarkdown","markdown"),
    /**
     * 简单卡片
     */
    SAMPLE_ACTION_CARD_4("sampleActionCard4", "sampleActionCard4"),
    ;

    /**
     * 单聊类型key
     * 不同类型消息 传参方式不同 有坑 群聊 单聊还不一样（吐槽） 很傻逼
     */
    private final String single;

    /**
     * 群聊类型key
     */
    private final String group;
}
