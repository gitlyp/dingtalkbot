package com.rock.ai.bot.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Rock.L
 * @date: 2023/10/6
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "hi,I'm bot.";
    }


}
