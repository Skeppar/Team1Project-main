package com.example.slutprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@Controller
public class BrightsController {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherAnnouncementRepo teacherAnnouncementRepo;
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


    @PostMapping("/profile")
    public String profilePost(@ModelAttribute Student student, HttpSession session, Model model) {
        student.setFirstName((String) model.getAttribute("firstName"));
        student.setLastName((String) model.getAttribute("lastName"));
        student.setEmail((String) model.getAttribute("email"));
        student.setLinkedIn((String) model.getAttribute("linkedIn"));
        student.setGitHub((String) model.getAttribute("gitHub"));
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

    @GetMapping("/uploadAss")
    public String assignment(Model model) {
        model.addAttribute("post",new TeacherAnnouncement());
        return "uploadAss";
    }
    @PostMapping("/uploadAss")
    public String addItem(@ModelAttribute TeacherAnnouncement ta, Model model, HttpSession session, HttpServletRequest request, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        model.addAttribute("post",ta);

        // Hämta filnamnet
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        // Har användaren inte laddat upp en bild så vill fortsätta använda default
        if(!fileName.equals(""))
        {
            ta.setImg(fileName);

            // Lägg till bilden i projektmappen /images/[bildnamn.typ]
            /* Med FileUploadUtil

            String uploadDir = "images/" + item.getId();
            FileUploadUtil.saveFile(uploadDir, item.getImg(), multipartFile);
             */

            // System.getProperty("user.dir") pekar på C:\Users\...\kvarteret
            String folder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\ads\\";
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + multipartFile.getOriginalFilename());
            Files.write(path,bytes);

            ta.setImg("images/ads/" + ta.getImg()); // item.getImg()
        }

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        ta.setTeacher(teacher);

        teacherAnnouncementRepo.save(ta);
        return "uploadAss";
    }

    @GetMapping("/logoutuser")
    public String logout(HttpSession session, HttpServletResponse res)
    {
        session.removeAttribute("student");
        return "redirect:/";
    }
}
