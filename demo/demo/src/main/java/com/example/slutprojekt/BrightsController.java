package com.example.slutprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BrightsController {



    /*@GetMapping("/")
    public String home() {
        return "/";
    }*/

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
