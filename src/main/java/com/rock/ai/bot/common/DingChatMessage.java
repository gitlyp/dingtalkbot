package com.rock.ai.bot.common;

import com.rock.ai.bot.constant.DingMsgType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Rock.L
 * @date: 2023/12/6
 */
@Getter
@Setter
public class DingChatMessage extends BaseEntity {

    private String title;

    private String text;

    private DingMsgType msgType;
}
