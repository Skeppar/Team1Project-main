package com.example.slutprojekt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;
import java.sql.Date;
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String linkedIn;
    private String gitHub;


    //@Column(nullable = false, unique = true)



    //för security??

    private String username;



    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<TeacherAnnouncement> posts = new ArrayList<>();

   /* private List<Role> isAdmin = new ArrayList<>();

    */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
    private String address;
    private Date dateOfBirth;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Teacher_Course",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    Set<Course> course = new HashSet<>();

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email, String linkedIn, String password) {//, String gitHub, City city, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.linkedIn = linkedIn;
        this.password = password;


        //this.gitHub = gitHub;
        //this.city = city;
        //this.address = address;
    }
    public List<TeacherAnnouncement> getPosts() {return posts;}

    public void setPosts(List<TeacherAnnouncement> posts) {this.posts = posts;}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }


}
/*public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }*/


/*
INSERT INTO COURSE (TITLE, START_DATE, GRADUATION_DATE, CITY) VALUES ('Java HT 2022', 01-08-2022, 21-10-2022, 'Stockholm');
INSERT INTO COURSE (TITLE, START_DATE, GRADUATION_DATE, CITY) VALUES ('JavaScript HT 2022', 01-08-2022, 21-10-2022, 'Stockholm');
 */
