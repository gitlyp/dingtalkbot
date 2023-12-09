package com.rock.ai.bot.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: Rock.L
 * @date: 2023/12/6
 */
@Getter
@Setter
public class DingChatSession extends BaseEntity {

    /**
     * 对话方式
     * 1 单聊 2 群聊 3 私聊
     *
     * @see com.rock.ai.bot.constant.DingChatType
     */
    private String chatType;

    /**
     * 群聊回调session
     */
    private String sessionWebHook;

    /**
     * 群聊@
     */
    private List<String> atUserIds;

    /**
     * 消息发送者
     */
    private String senderId;

    /**
     * 消息发送人昵称
     */
    private String senderNick;
}
