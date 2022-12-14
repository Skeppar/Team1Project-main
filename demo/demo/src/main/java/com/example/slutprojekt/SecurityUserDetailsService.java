package com.example.slutprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        Student student = studentRepo.findByEmail(username);
        Teacher teacher = teacherRepo.findByEmail(username);
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
