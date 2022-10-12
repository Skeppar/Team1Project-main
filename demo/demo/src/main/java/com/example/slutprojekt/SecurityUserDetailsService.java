package com.example.slutprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    /*@Autowired
    private UserRepository userRepository;*/

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    // returns the SecurityUserPincipal that Spring Security uses to check authentication (our user object is sent in the constructor)
    @Override
    public UserDetails loadUserByUsername(String username) {
        //User user = userRepository.findByUsername(username);
        Student student = studentRepo.findByUsername(username);
        Teacher teacher = teacherRepo.findByUsername(username);
        if (student == null && teacher == null) {
            throw new UsernameNotFoundException(username);
        }
        else if (student != null) {
            return new SecurityUserPrincipal(student);
        }

        //return new SecurityUserPrincipal(user);
        return new SecurityUserPrincipal(teacher);
    }
}
