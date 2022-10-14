package com.example.slutprojekt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import javax.activation.MimeType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



@Controller
public class BrightsController {
    Logger logger = LoggerFactory.getLogger(BrightsController.class);
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherAnnouncementRepo teacherAnnouncementRepo;
    @Autowired
    private CityRepo cityRepo;

    @Autowired
   private FileUploadUtil fileUploadUtil;

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
        public String profilePost (@ModelAttribute Student student, HttpServletRequest request) throws IOException {

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
        public String profileSaved () {
            return "profileSaved";
        }

        @GetMapping("/uploadAss")
        public String assignment(Model model){
            model.addAttribute("post", new TeacherAnnouncement());
            return "uploadAss";
        }
        @PostMapping("/uploadAss")
        public String addAss(@ModelAttribute TeacherAnnouncement ta, Model model, HttpSession
        session, HttpServletRequest request, @RequestParam("afile") MultipartFile multipartFile) throws IOException {
            model.addAttribute("post", ta);

            // Hämta filnamnet
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            // Har användaren inte laddat upp en bild så vill fortsätta använda default
            if (!fileName.equals("")) {
                ta.setImg(fileName);

                // Lägg till bilden i projektmappen /images/[bildnamn.typ]
            /* Med FileUploadUtil

            String uploadDir = "images/" + item.getId();
            FileUploadUtil.saveFile(uploadDir, item.getImg(), multipartFile);
             demo/demo/src/main/resources/static/files*/
                // System.getProperty("user.dir") pekar på C:\Users\...\kvarteret
                String folder = System.getProperty("user.dir") + "\\demo\\demo\\src\\main\\resources\\static\\files\\";
                System.out.println(System.getProperty(("user.dir")));
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get(folder + multipartFile.getOriginalFilename());
                Files.write(path, bytes);

                ta.setImg("/files/" + ta.getImg()); // item.getImg()
            }

            Teacher teacher = (Teacher) session.getAttribute("teacher");
            ta.setTeacher(teacher);

            teacherAnnouncementRepo.save(ta);
            logger.info("User added an item" + " " + ta );
            return "redirect:/uploadAss";
        }
        @GetMapping("/logoutuser")
        public String logout (HttpSession session, HttpServletResponse res)
        {
            session.removeAttribute("student");
            return "redirect:/";
        }
    }

