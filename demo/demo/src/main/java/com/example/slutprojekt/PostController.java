package com.example.slutprojekt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static javax.print.attribute.Size2DSyntax.MM;

@Controller
public class PostController {
    private static final int PAGE_SIZE = 10;
    Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    TeacherAnnouncementRepo teacherAnnouncementRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable Long id) {
        //Item item1 = itemRepository.getItem(id);
        TeacherAnnouncement post1 = teacherAnnouncementRepo.findById(id).get();
        model.addAttribute("post1", post1);
        return "home";
    }
    @GetMapping("/")
    public String posts(HttpSession session, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
      //List<TeacherAnnouncement> posts = getPage(page - 1, PAGE_SIZE);
        //int pageCount = numberOfPages(PAGE_SIZE);
        //int[] pages = toArray(pageCount);

        /*List<String> contentsTitle = new ArrayList<>();
        List<String> contentsContent = new ArrayList<>();
        for (TeacherAnnouncement strings : posts) {
            contentsTitle.add(strings.getTitle());
            contentsContent.add(strings.getContent());
        }

        session.setAttribute("contentTitle", contentsTitle);
        session.setAttribute("contentContent", contentsContent);*/

        List<TeacherAnnouncement> allContent = (List<TeacherAnnouncement>) teacherAnnouncementRepo.findAll();
        Collections.sort(allContent, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        /*for (TeacherAnnouncement announcement : allContent) {
            for (Teacher teacher : teacherRepo.findAll()) {
                if (Objects.equals(announcement.getTeacher().getId(), teacher.getId())) {
                    announcement.setTeacherName(teacher.getFirstName() + " " + teacher.getLastName());
                }
            }
        }*/
        model.addAttribute("allContent", allContent);
        //model.addAttribute("content", posts);
        //model.addAttribute("pages", pages);
        //model.addAttribute("currentPage", page);
        //model.addAttribute("showPrev", page > 1);
        //model.addAttribute("showNext", page < pageCount);
        //List<TeacherAnnouncement> posts = (List<TeacherAnnouncement>) teacherAnnouncementRepo.findAll();
        //.addAttribute("content", posts);

        model.addAttribute("newPost",new TeacherAnnouncement());

        return "home";
    }

    @PostMapping("/")
    public String addPost(@ModelAttribute TeacherAnnouncement post, Model model, HttpSession session, HttpServletRequest request /*, @RequestParam("image") MultipartFile multipartFile*/) throws IOException {
        model.addAttribute("content",post);

                /*
        // Hämta filnamnet
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());


        // Har användaren inte laddat upp en bild så vill fortsätta använda default
        if(!fileName.equals(""))
        {
            post.setImg(fileName);

            // Lägg till bilden i projektmappen /images/[bildnamn.typ]
            /* Med FileUploadUtil

            String uploadDir = "images/" + item.getId();
            FileUploadUtil.saveFile(uploadDir, item.getImg(), multipartFile);
             */

        /*
            // System.getProperty("user.dir") pekar på C:\Users\...\kvarteret
            String folder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\ads\\";
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + multipartFile.getOriginalFilename());
            Files.write(path,bytes);

            post.setImg("images/ads/" + post.getImg()); // item.getImg()
        }

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        post.setTeacher(teacher);

        teacherAnnouncementRepo.save(post);
        logger.info("User added an item" + " " + post );
        */

        Long millis=System.currentTimeMillis();
        java.sql.Timestamp date = new java.sql.Timestamp (millis);
        TeacherAnnouncement ta = new TeacherAnnouncement(post.getTitle(), post.getContent(), date);
        teacherAnnouncementRepo.save(ta);

        return "redirect:/";
    }
    private List<TeacherAnnouncement> getPage(int page, int pageSize) {
        List<TeacherAnnouncement> items = (List<TeacherAnnouncement>)teacherAnnouncementRepo.findAll();

        int from = Math.max(0,page*pageSize);
        int to = Math.min(items.size(),(page+1)*pageSize);
        return items.subList(from, to);
    }
    private int numberOfPages(int pageSize) {
        List<TeacherAnnouncement> books = (List<TeacherAnnouncement>)teacherAnnouncementRepo.findAll();
        return (int)Math.ceil((double) books.size() / pageSize);
    }
    private int[] toArray ( int num){
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    @GetMapping ("/video")
    public String chat() {
        return "video";
    }
}
