package com.example.slutprojekt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrightsController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/people")
    public  String people() {
        return "people";
    }

    @GetMapping("/assignment")
    public String assignment() {
        return "assignment";
    }

}
