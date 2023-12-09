package com.rock.ai.bot.feginclient.n8n;

import com.rock.ai.bot.common.ChatInput;
import com.rock.ai.bot.common.ChatOutput;
import com.rock.ai.bot.common.DingChat;
import com.rock.ai.bot.common.DingChatMessage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Rock.L
 * @date: 2023/12/3
 */
@Getter
@Setter
public class N8nChatOutput extends DingChat implements ChatOutput {

    private DingChatMessage chatMessage;

}
