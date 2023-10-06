package com.rock.ai.bot.feginclient.fastgpt;

import com.rock.ai.bot.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 对话响应
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Getter
@Setter
public class ChatResponse extends BaseEntity {
    /**
     * id
     */
    private String id;

    /**
     * 模型
     */
    private String model;

    /**
     * 选择
     */
    private List<Choice> choices;
}
