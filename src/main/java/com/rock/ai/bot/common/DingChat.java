package com.rock.ai.bot.common;

import com.dingtalk.open.app.api.models.bot.MessageContent;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Rock.L
 * @date: 2023/12/5
 */
@Getter
@Setter
public class DingChat extends BaseEntity {

    private DingChatSession chatSession;

    private transient MessageContent messageContent;
}
