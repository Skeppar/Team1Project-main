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
import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

import static java.sql.Timestamp.valueOf;

@Controller
public class PostController {
    private static final int PAGE_SIZE = 10;
    Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    TeacherAnnouncementRepo teacherAnnouncementRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    AssignmentRepo assignmentRepo;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    DocumentRepo documentRepo;

    @Autowired
    private CityRepo cityRepo;


    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable Long id) {
        //Item item1 = itemRepository.getItem(id);
        TeacherAnnouncement post1 = teacherAnnouncementRepo.findById(id).get();
        model.addAttribute("post1", post1);
        return "home";
    }

    @GetMapping("/")
    public String posts(HttpSession session,Principal principal, Model model,HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        //Long oldId = (Long) model.getAttribute("postId");
        /*if (studentRepo.findByEmail(principal.getName()) != null){
            Student student = studentRepo.findByEmail(principal.getName());


            //session.setAttribute("username1",userName1);
            model.addAttribute("username1",student.getFirstName());

        } else {
            Teacher teacher = teacherRepo.findByEmail(principal.getName());
            //session.setAttribute("username1",userName1);
            model.addAttribute("username1",teacher.getFirstName());
        }*/

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
    public String addPost(@ModelAttribute TeacherAnnouncement post,Principal principal, Model model, HttpSession session, HttpServletRequest request, @RequestParam("afile") MultipartFile multipartFile) throws IOException {



        Long millis=System.currentTimeMillis();
        java.sql.Timestamp date = new java.sql.Timestamp (millis);
        TeacherAnnouncement ta = new TeacherAnnouncement(post.getTitle(), post.getContent(),post.getTeacher(),date,post.getTeacherName());
        teacherAnnouncementRepo.save(ta);


        if (studentRepo.findByEmail(principal.getName()) != null){
            Student student = studentRepo.findByEmail(principal.getName());


            //session.setAttribute("username1",userName1);
           // model.addAttribute("username1",student.getFirstName());

        } else {
            Teacher teacher = teacherRepo.findByEmail(principal.getName());
            //session.setAttribute("username1",userName1);
            //model.addAttribute("username1",teacher);

            ta.setTeacherName(teacher.getFirstName());
            ta.setTeacherLastName(teacher.getLastName());
            ta.setTeacher(teacher);
            //model.addAttribute("teacherlastname",post.getTeacher().getLastName());
            // ta.setTeacher(teacher);
            // teacher.setId(teacher.getId());
            //teacher.setLastName(teacher.getLastName());
        }

        // Hämta filnamnet
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        // Har användaren inte laddat upp en bild så vill fortsätta använda default
        if (!fileName.equals("")) {
            ta.setImg(fileName);
            String folder = System.getProperty("user.dir") + "\\demo\\demo\\src\\main\\resources\\static\\files\\";
            System.out.println(System.getProperty(("user.dir")));
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + multipartFile.getOriginalFilename());
            Files.write(path, bytes);

            post.setImg(post.getImg()); // item.getImg()
        }

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        ta.setTeacher(teacher);

        teacherAnnouncementRepo.save(ta);
        logger.info("User added an item" + " " + ta );

        model.addAttribute("content",post);

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

    @GetMapping("/video")
    public String chat() {
        return "video";
    }

    @GetMapping("/people")
    public String people(Model model){
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("teachers", teacherRepo.findAll());
        return "people";
    }

    @GetMapping("/uploadAssignment")
    public String uploadAssignment(Model model, HttpSession session,Principal principal,HttpServletRequest request) {


        Assignment assignment = new Assignment();
        model.addAttribute("assignment", assignment);

        model.addAttribute("allCourses", courseRepo.findAll());

        Long millis=System.currentTimeMillis();
        java.sql.Timestamp date = new java.sql.Timestamp (millis);

        session.setAttribute("dateNum", date.toString());

        model.addAttribute("allAssignments", assignmentRepo.findAll());

        return "assignment";
    }

    @PostMapping("uploadAssignment")
    public String uploadAssignmentpost(@ModelAttribute Assignment assignment,Principal principal, @ModelAttribute Course course, HttpSession session, HttpServletRequest request,@RequestParam("afile") MultipartFile multipartFile) throws IOException {

        Teacher teacher = teacherRepo.findByEmail(principal.getName());
        Long millis=System.currentTimeMillis();
        java.sql.Timestamp date = new java.sql.Timestamp (millis);


        assignment.setCourse(course);

        System.out.println(assignment.getContent());

        assignment.setCreatedDate(date);

        String date2 = request.getParameter("date");
        String time = request.getParameter("time");
        String dateAndTime = date2 + " " + time + ":59";


        Timestamp date3 = Timestamp.valueOf(dateAndTime);

        assignment.setDueDate(date3);
        assignment.setTeacherName(teacher.getFirstName());
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (!fileName.equals("")) {
            assignment.setFileName(fileName);
            String folder = System.getProperty("user.dir") + "\\demo\\demo\\src\\main\\resources\\static\\files\\";
            System.out.println(System.getProperty(("user.dir")));
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            assignment.setFileName(assignment.getFileName()); // item.getImg()
        }
        assignmentRepo.save(assignment);

        return "redirect:/uploadAssignment";
    }

    @GetMapping("/addStudent")
    public String showStudent(Model model, Student newStudent) {
        model.addAttribute("student", new Student());
        return  "addStudent";
    }
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute Student student) {
        studentRepo.save(student);


        return "redirect:/addStudent";
    }


    @GetMapping("/filesUpload")
    public String filesUpload(HttpSession session,Principal principal, Model model,HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        Document document = new Document();
        List<Document> allDocuments = (List<Document>) documentRepo.findAll();
        model.addAttribute("document", document);
        model.addAttribute("allDocuments", allDocuments);
        model.addAttribute("allCourses", assignmentRepo.findAll());
        model.addAttribute("allContent",teacherAnnouncementRepo.findAll());

        return "filesUpload";
    }

    @PostMapping("/filesUpload")
    public String filesUpload(@ModelAttribute Document document , Principal principal, Model model, HttpSession session, HttpServletRequest request, @RequestParam("afile") MultipartFile multipartFile) throws IOException {

        Long millis=System.currentTimeMillis();
        java.sql.Timestamp date = new java.sql.Timestamp (millis);
        document.setDate(date);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (!fileName.equals("")) {
            document.setFileName(fileName);
            String folder = System.getProperty("user.dir") + "\\demo\\demo\\src\\main\\resources\\static\\files\\";
            System.out.println(System.getProperty(("user.dir")));
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + multipartFile.getOriginalFilename());
            Files.write(path, bytes);

            document.setFileName(document.getFileName()); //
        }

        documentRepo.save(document);
        logger.info("User added an item" + " " + document );


        return "redirect:/filesUpload";
    }

    @PostMapping("/deletePost")
    public String deletePost(@RequestParam Long id) {

        teacherAnnouncementRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/changePost")
    public String changePost(@RequestParam Long id2, Model model) {

        model.addAttribute("oldPost", teacherAnnouncementRepo.findById(id2));

        return "editPost";
    }

    @PostMapping("/editPostSave")
    public String editPostSave(@ModelAttribute TeacherAnnouncement teacherAnnouncement, @RequestParam("afile") MultipartFile multipartFile) throws IOException{

        TeacherAnnouncement ta2 = new TeacherAnnouncement();

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (!fileName.equals("")) {
            ta2.setImg(fileName);
            String folder = System.getProperty("user.dir") + "\\demo\\demo\\src\\main\\resources\\static\\files\\";
            System.out.println(System.getProperty(("user.dir")));
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + multipartFile.getOriginalFilename());
            Files.write(path, bytes);

            teacherAnnouncement.setImg(teacherAnnouncement.getImg()); // item.getImg()
        }



        Timestamp date = null;
        List<TeacherAnnouncement> lista = (List<TeacherAnnouncement>) teacherAnnouncementRepo.findAll();
        for (TeacherAnnouncement ta : lista) {
            if (ta.getId() == teacherAnnouncement.getId()) {
                date = ta.getDate();
                teacherAnnouncement.setTeacher(ta.getTeacher());
                teacherAnnouncement.setTeacherName(ta.getTeacherName());
                teacherAnnouncement.setTeacherLastName(ta.getTeacherLastName());
            }
        }



        teacherAnnouncement.setImg(ta2.getImg());
        teacherAnnouncement.setDate(date.toString());
        teacherAnnouncementRepo.save(teacherAnnouncement);

        return "redirect:/";
    }

    @GetMapping("/modules")
    public String modules() {
        return "/modules";
    }
    @GetMapping("/addCourse")
    public String addCourse(Model model, Course newCourse) {
        model.addAttribute("course", new Course());
        return "addCourse";
    }
    @PostMapping("addCourse")
    public String addCourse(@ModelAttribute Course course) {
        courseRepo.save(course);
        return "redirect:/addCourse";
    }
}

