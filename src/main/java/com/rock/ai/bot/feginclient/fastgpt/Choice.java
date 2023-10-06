package com.rock.ai.bot.feginclient.fastgpt;

import com.rock.ai.bot.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Getter
@Setter
public class Choice extends BaseEntity {

    /**
     * 消息
     */
    private Message message;
}
