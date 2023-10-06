package com.rock.ai.bot.common;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author: Rock.L
 * @date: 2023/10/6
 */
public class BaseEntity implements Serializable {
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
