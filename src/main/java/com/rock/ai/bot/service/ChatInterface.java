package com.rock.ai.bot.service;

/**
 * @author: Rock.L
 * @date: 2023/10/6
 */
public interface ChatInterface<T,R> {
    /**
     * 对话
     *
     * @param input 输入
     * @return 输出
     */
    R chat(T input);
}
