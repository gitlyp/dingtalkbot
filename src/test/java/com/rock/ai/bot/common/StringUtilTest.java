package com.rock.ai.bot.common;

import com.alibaba.fastjson.JSONObject;
import com.rock.ai.bot.util.StringUtil;
import org.junit.jupiter.api.Test;

/**
 * 指令对话内容拆分测试
 *
 * @author: Rock.L
 * @date: 2023/12/6
 */
public class StringUtilTest {


    @Test
    public void getChat() {
        String input = "xxdd    你s好ss sf";
        String[] chat = StringUtil.getChat(input);
        System.out.println(JSONObject.toJSONString(chat));
    }
    //["xxdd","你s好ss sf"]


    @Test
    public void getChat2() {
        String input = "   doc ss   你s好ss sf";
        String[] chat = StringUtil.getChat(input);
        System.out.println(JSONObject.toJSONString(chat));
    }
    // ["doc","ss   你s好ss sf"]


    @Test
    public void getChat3() {
        String input = "    你s好ss sf";
        String[] chat = StringUtil.getChat(input);
        System.out.println(JSONObject.toJSONString(chat));
    }
    // ["ss","sf"]


    @Test
    public void getChat4() {
        String input = "sls 你s好ss sf";
        String[] chat = StringUtil.getChat(input);
        System.out.println(JSONObject.toJSONString(chat));
    }
    // ["sls","你s好ss sf"]

    @Test
    public void getChat5() {
        String input = "  ";
        String[] chat = StringUtil.getChat(input);
        System.out.println(JSONObject.toJSONString(chat));
    }
    // [null,""]

    @Test
    public void getChat6() {
        String input = null;
        String[] chat = StringUtil.getChat(input);
        System.out.println(JSONObject.toJSONString(chat));
    }

    // [null,null]
}