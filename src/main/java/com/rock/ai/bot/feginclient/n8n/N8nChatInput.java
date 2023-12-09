package com.rock.ai.bot.feginclient.n8n;

import com.rock.ai.bot.common.ChatInput;
import com.rock.ai.bot.common.DingChat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.util.Strings;
import shade.io.netty.util.internal.StringUtil;

import java.util.Objects;

/**
 * @author: Rock.L
 * @date: 2023/12/5
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class N8nChatInput extends DingChat implements ChatInput {

    private String cmd;

    private String content;
}
