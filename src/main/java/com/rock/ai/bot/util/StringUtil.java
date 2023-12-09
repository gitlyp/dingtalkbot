package com.rock.ai.bot.util;

import org.apache.logging.log4j.util.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Rock.L
 * @date: 2023/12/6
 */
public class StringUtil {


    /**
     * 定义对话正则表达式模式 字母+任意数量空格+内容
     */
    private static final Pattern CHAT_PATTERN = Pattern.compile("(\\w+\\s+)(.*)");


    /**
     * 提取用户会话
     *
     * @param input 用户内容
     * @return 指令+内容
     */
    public static String[] getChat(String input) {

        if (Strings.isNotBlank(input)) {
            input = input.trim();
            Matcher matcher = CHAT_PATTERN.matcher(input);
            if (matcher.find()) {
                String cmd = matcher.group(1).trim();
                String content = matcher.group(2).trim();

                return new String[]{cmd, content};
            }
        }

        return new String[]{null, input != null ? input.trim() : null};
    }
}
