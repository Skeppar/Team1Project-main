package com.example.slutprojekt;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
public class GreetingController {

    @GetMapping("/chat")
    public String post() {
        return "chat";
    }


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(ChatMessage message,Principal principal) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting( HtmlUtils.htmlEscape(message.getMessage()),principal.getName());

    }

}

