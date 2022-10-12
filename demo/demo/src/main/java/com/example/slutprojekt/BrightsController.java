package com.example.slutprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class BrightsController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, HttpServletRequest request, Model model) {
        String email = request.getRemoteUser();

        for (Student student : studentRepo.findAll()) {
            if (student.getEmail().equals(email)) {
                model.addAttribute("student", student);
            }
        }
        return "profile";
    }

    @GetMapping("/people")
    @PostMapping("/profile")
    public String profilePost(@ModelAttribute Student student, HttpSession session, Model model) {
        student.setFirstName((String)model.getAttribute("firstName"));
        student.setLastName((String)model.getAttribute("lastName"));
        student.setEmail((String)model.getAttribute("email"));
        student.setLinkedIn((String)model.getAttribute("linkedIn"));
        student.setGitHub((String)model.getAttribute("gitHub"));
        //student.setUsername((String)session.getAttribute("username"));
        /*student.setFirstName((String)session.getAttribute("firstName"));
        student.setLastName((String)session.getAttribute("lastName"));
        student.setEmail((String)session.getAttribute("email"));
        student.setLinkedIn((String)session.getAttribute("linkedIn"));
        student.setGitHub((String)session.getAttribute("gitHub"));*/
        //student.setCity((City)session.getAttribute("city"));
        //student.setAddress((String)session.getAttribute("address"));
        //student.setDateOfBirth((Date)session.getAttribute("dateofBirth"));

        //student.setPassword((String)session.getAttribute("password"));
        studentRepo.save(student);


        //return "redirect:/profile";
        return "/home";
    }

    @GetMapping("/assignment")
    public String assignment() {
        return "assignment";
    }

}
