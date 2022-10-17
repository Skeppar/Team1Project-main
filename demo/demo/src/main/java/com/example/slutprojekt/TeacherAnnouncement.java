package com.example.slutprojekt;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TeacherAnnouncement {
    @Id // Primärnyckeln
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    private String title;
    private String content;
    private String img; // = "/files/default.jpg";
    private Timestamp date;
    private String teacherName;
    private String teacherLastName;

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastname) {
        this.teacherLastName = teacherLastname;
    }

    @ManyToOne
    private Teacher teacher;

    public TeacherAnnouncement(){}

    public TeacherAnnouncement(String title, String content, Timestamp  date, String image, String teacherName) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.img = image;
        this.teacherName = teacherName;
    }
    public TeacherAnnouncement(String title, String content, Timestamp  date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public TeacherAnnouncement(String title, String content, Teacher teacher, Timestamp date,String teacherName) {
        this.title = title;
        this.content = content;
        this.teacher = teacher;
        this.date = date;
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = Timestamp.valueOf(date);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {this.teacher = teacher;}
}
