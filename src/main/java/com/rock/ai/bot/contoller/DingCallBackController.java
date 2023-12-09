package com.rock.ai.bot.contoller;

import com.rock.ai.bot.constant.DingChatType;
import com.rock.ai.bot.feginclient.n8n.N8nChatOutput;
import com.rock.ai.bot.service.RobotGroupMessagesService;
import com.rock.ai.bot.service.RobotSingleMessagesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: Rock.L
 * @date: 2023/12/6
 */
@RestController
@RequestMapping("/dingCallBack")
public class DingCallBackController {

    @Resource
    private RobotGroupMessagesService groupMessagesService;

    @Resource
    private RobotSingleMessagesService singleMessagesService;

    @PostMapping("/n8nSend")
    @ResponseBody
    public Boolean n8nSend(@RequestBody N8nChatOutput n8nChatOutput) {
        if (DingChatType.GROUP.getType().equals(n8nChatOutput.getChatSession().getChatType())) {
            return groupMessagesService.apiSend(n8nChatOutput);
        } else {
            return singleMessagesService.apiSend(n8nChatOutput);
        }

    }


}
