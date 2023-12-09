package com.rock.ai.bot.feginclient.n8n;

import com.rock.ai.bot.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: Rock.L
 * @date: 2023/10/22
 */
@Getter
@Setter
public class N8nQueryOrderResp  extends BaseEntity {

    private String tips;

    private String text;

    private String sls;

    private List<String> slsList;

    private String json;

    private String jsonTips;
}
