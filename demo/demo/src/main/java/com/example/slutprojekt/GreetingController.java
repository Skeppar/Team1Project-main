package com.example.slutprojekt;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;
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

