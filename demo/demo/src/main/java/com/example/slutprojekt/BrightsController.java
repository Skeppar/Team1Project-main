package com.example.slutprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.activation.MimeType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



@Controller
public class BrightsController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CityRepo cityRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, HttpServletRequest request, Model model) {
        String email = request.getRemoteUser();
        session.setAttribute("userEmail", request.getRemoteUser());


        Date birth = null;

        for (Student student : studentRepo.findAll()) {
            if (student.getEmail().equals(email)) {
                model.addAttribute("student", student);
                if (student.getDateOfBirth() != null) {
                    birth = student.getDateOfBirth();
                }
            }
        }

        List<String> cityNames = new ArrayList<>();
        for (City city2 : cityRepo.findAll()) {
            cityNames.add(city2.getName());
        }

        model.addAttribute("birth", birth);
        session.setAttribute("birth2", birth);
        model.addAttribute("stader", cityNames);

        return "profile";
    }


    @PostMapping("/profile")
    public String profilePost(@ModelAttribute Student student, HttpServletRequest request) throws IOException {

        String newCity = request.getParameter("cities");

        City newCity2 = null;
        for (City city : cityRepo.findAll()) {
            if (city.getName().equals(newCity)) {
                newCity2 = city;
            }
        }

        student.setCity(newCity2);

        Date date2 = Date.valueOf(request.getParameter("date"));

        student.setDateOfBirth(date2);

        studentRepo.save(student);

        return "redirect:/profileSaved";
    }

    @GetMapping("/profileSaved")
    public String profileSaved() {
        return "profileSaved";
    }

    @GetMapping("/uploadAss")
    public String assignment() {
        return "uploadAss";
    }
    @GetMapping("/logoutuser")
    public String logout(HttpSession session, HttpServletResponse res)
    {
        session.removeAttribute("student");
        return "redirect:/";
    }
}
