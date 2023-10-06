package com.rock.ai.bot.feginclient.fastgpt;

import com.rock.ai.bot.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * chat请求类
 *
 * @author: Rock.L
 * @date: 2023/10/6
 */
@Getter
@Setter
public class ChatRequest extends BaseEntity {
    /**
     * 对话id 非必填
     * 传了可以使用对话上下文功能
     */
    private String chatId;

    /**
     * stream模式
     */
    private boolean stream;

    /**
     * 是否返回详情值
     */
    private boolean detail;

    /**
     * 变量内容
     * cTime 必须
     */
    private Map<String, String> variables;

    /**
     * content，role 必须
     */
    private List<Message> messages;
}
