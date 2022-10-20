package com.example.slutprojekt;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import org.junit.jupiter.api.Test;

import org.junit.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.security.Principal;
import java.util.List;


	@SpringBootTest
	@RunWith(SpringRunner.class)
	class SlutprojektApplicationTestss {
		@Autowired
		TeacherRepo teacherRepo;
		@Autowired
		TeacherAnnouncementRepo teacherAnnouncementRepo;
		@Autowired
		StudentRepo studentRepo;
		@Autowired
		PostController postController;
		@Autowired
		BrightsController brightsController;
		@Autowired
		GreetingController greetingController;
		@Autowired
		SecurityConfig securityConfig;
		@Autowired
		SecurityUserDetailsService securityUserDetailsService;
		@Autowired
		SlutprojektApplication slutprojektApplication;




		@Test
		void contextLoads() {
		}
		@Test
		public void FindTeachByName() {
			Teacher teacher = new Teacher("Stefan","Stefansson","stefan@stefanmail.com","Stefanslinkedin","Password");
			Assert.assertEquals("Stefan",teacher.getFirstName());
		}
		@Test
		public void teacherAnnoucementTest () {
			List<TeacherAnnouncement> testAnnouncement = (List<TeacherAnnouncement>)teacherAnnouncementRepo.findAll();
			Assert.assertEquals(3,testAnnouncement.size());
		}

		@Test
		public void teacherTestSomething() {
			Teacher teacher = teacherRepo.findByEmail("olsson@mail.com");
			Assert.assertEquals("Andreas",teacher.getFirstName());
		}
		@Test
		public void testSomethingElse() {
			List <Student>  student = (List<Student>) studentRepo.findAll();
			Assert.assertEquals(6,student.size());
		}
		@Test
		public void testThisThingHere() {

			Teacher teacher = teacherRepo.findByEmail("olsson@mail.com");
			TeacherAnnouncement hello = new TeacherAnnouncement();
			hello.setTeacher(teacher);
			hello.setTeacherName(teacher.getFirstName());
			Assert.assertEquals("Andreas",hello.getTeacherName());

		}
		@Test
		public void runningOutOfNames () {
			List <Teacher>  teachers = (List<Teacher>) teacherRepo.findAll();
			Assert.assertEquals(2,teachers.size());
			Teacher teacher = new Teacher("Stefan","Stefansson","stefan@stefanmail.com","Stefanslinkedin","Password");
			teacherRepo.save(teacher);
			teachers = (List<Teacher>)teacherRepo.findAll();
			Assert.assertEquals(3,teachers.size());
			teacherRepo.findByEmail("stefan@stefanmail.com");



		}
	}


