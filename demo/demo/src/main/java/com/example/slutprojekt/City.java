package com.example.slutprojekt;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    private List<Student> students;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    private List<Teacher> teachers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    private List<Course> courses;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /*public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }*/

    /*public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }*/

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
