package com.example.slutprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    /*@Autowired
    private UserRepository userRepository;*/

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/init")
    public String init(){
        // tries to find the user so that we only save the user if it does not exist
        //User user = userRepository.findByUsername("user");
        Student student = studentRepo.findByEmail("user");
        /*if (user == null) {
            user = new User();
            user.setUsername("user");
            user.setPassword(encoder.encode("123"));
            // svae the user with username user and an encoded value for 123 as password
            userRepository.save(user);*/
        if (student == null) {
            student = new Student();
            student.setEmail("user");
            student.setPassword(encoder.encode("123"));
            // svae the user with username user and an encoded value for 123 as password
            studentRepo.save(student);
        }

        return "ok";
    }
}
