package com.rock.ai.bot.feginclient.fastgpt;

import com.rock.ai.bot.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 消息内容
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Getter
@Setter
public class Message extends BaseEntity {
    /**
     * 对话内容
     */
    private String content;

    /**
     * 角色
     */
    private String role;
}
