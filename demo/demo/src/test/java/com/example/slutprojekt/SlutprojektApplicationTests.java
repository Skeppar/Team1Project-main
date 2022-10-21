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
			//testar konstruktorn
			Teacher teacher = new Teacher("Stefan","Stefansson","stefan@stefanmail.com","Stefanslinkedin","Password");
			Assert.assertEquals("Stefan",teacher.getFirstName());
		}
		@Test
		public void teacherAnnoucementTest () {
			List<TeacherAnnouncement> testAnnouncement = (List<TeacherAnnouncement>)teacherAnnouncementRepo.findAll();
			Assert.assertEquals(6,testAnnouncement.size());
			//hittar alla teacher announcements som finns i anncoument.

		}

		@Test
		public void teacherTestSomething() {
			Teacher teacher = teacherRepo.findByEmail("olsson@mail.com");
			Assert.assertEquals("Andreas",teacher.getFirstName());
			//hittar en lärare hittar första nament på läraren som är kopplat till mailen vilket vi använder som principal.
		}
		@Test
		public void testSomethingElse() {
			List <Student>  student = (List<Student>) studentRepo.findAll();
			Assert.assertEquals(6,student.size());
			//hittar alla studenter och kollar om storleken på listan av studenter är 6.
		}
		@Test
		public void testThisThingHere() {

			Teacher teacher = teacherRepo.findByEmail("olsson@mail.com");//hittar läraren
			TeacherAnnouncement hello = new TeacherAnnouncement();//nytt teacherannouncement hello
			hello.setTeacher(teacher);//sätter läraren vi har hittat till Announcement hello
			hello.setTeacherName(teacher.getFirstName());//hittar namnet till läraren som existerar i hello
			Assert.assertEquals("Andreas",hello.getTeacherName());//kollar vad första namnet är på läraren


		}
		@Test
		public void runningOutOfNames () {
			List <Teacher>  teachers = (List<Teacher>) teacherRepo.findAll();//gör en lista av lärare
			Assert.assertEquals(2,teachers.size());//kollar så att storleken är 2
			Teacher teacher = new Teacher("Stefan","Stefansson","stefan@stefanmail.com","Stefanslinkedin","Password");
			//skapar en ny lärare
			teacherRepo.save(teacher);
			//sparar läraren
			teachers = (List<Teacher>)teacherRepo.findAll();//hittar alla lärare och nu när vi lagt till en ny lärare i databasen så hittas dom 2 gamla och den nya
			Assert.assertEquals(3,teachers.size());
			teacherRepo.findByEmail("stefan@stefanmail.com");



		}
	}


