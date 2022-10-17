INSERT INTO CITY (NAME) VALUES ('Stockholm');
INSERT INTO CITY (NAME) VALUES ('Göteborg');
INSERT INTO CITY (NAME) VALUES ('Malmö');
INSERT INTO CITY (NAME) VALUES ('Uppsala');
INSERT INTO CITY (NAME) VALUES ('Luleå');
INSERT INTO CITY (NAME) VALUES ('Karlstad');
INSERT INTO CITY (NAME) VALUES ('Örebro');
INSERT INTO CITY (NAME) VALUES ('Kristianstad');
INSERT INTO CITY (NAME) VALUES ('Halmstad');
INSERT INTO CITY (NAME) VALUES ('Västerås');
INSERT INTO CITY (NAME) VALUES ('Åre');
INSERT INTO CITY (NAME) VALUES ('Karlskrona');
INSERT INTO CITY (NAME) VALUES ('Linköping');
INSERT INTO CITY (NAME) VALUES ('Sundsvall');
INSERT INTO CITY (NAME) VALUES ('Visby');


INSERT INTO COURSE (TITLE, START_DATE, GRADUATION_DATE, CITY_ID) VALUES ('Java HT 2022', '2022-08-01', '2022-10-21', (SELECT ID FROM CITY WHERE NAME = 'Stockholm'));
INSERT INTO COURSE (TITLE, START_DATE, GRADUATION_DATE, CITY_ID) VALUES ('JavaScript HT 2022', '2022-08-01', '2022-10-21', (SELECT ID FROM CITY WHERE NAME = 'Stockholm'));

INSERT INTO STUDENT (FIRST_NAME, LAST_NAME, EMAIL, LINKED_IN, PASSWORD) VALUES ('Johan', 'Mattsson', 'johan@mail.com', 'https://se.linkedin.com/in/johan-mattsson-2b7468219?' ,'$2a$11$RCEkkIuaDpUlqn5PyzUjEOvwrNnLSJ4kQKke5QJV6nGaZQiXcoWPa');
INSERT INTO STUDENT (FIRST_NAME, LAST_NAME, EMAIL, LINKED_IN, PASSWORD) VALUES ('Carl', 'Norrland', 'carl@mail.com', 'https://se.linkedin.com/in/carl-mickelson-300901189/en','$2a$11$RCEkkIuaDpUlqn5PyzUjEOvwrNnLSJ4kQKke5QJV6nGaZQiXcoWPa');
INSERT INTO STUDENT (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES ('Carl Johan', 'Stefansson', 'carljohan@mail.com', '$2a$11$RCEkkIuaDpUlqn5PyzUjEOvwrNnLSJ4kQKke5QJV6nGaZQiXcoWPa');
INSERT INTO STUDENT (FIRST_NAME, LAST_NAME, EMAIL, LINKED_IN, PASSWORD) VALUES ('Alvin', 'Skeppar', 'alvin@mail.com', 'https://se.linkedin.com/in/alvin-skeppar-5a87a1122/en' ,'$2a$11$RCEkkIuaDpUlqn5PyzUjEOvwrNnLSJ4kQKke5QJV6nGaZQiXcoWPa');
INSERT INTO STUDENT (FIRST_NAME, LAST_NAME, EMAIL, LINKED_IN, PASSWORD) VALUES ('Markus', 'Turesson', 'markus@mail.com', 'https://se.linkedin.com/in/markus-turesson-sj%C3%B6man-095506115' ,'$2a$11$RCEkkIuaDpUlqn5PyzUjEOvwrNnLSJ4kQKke5QJV6nGaZQiXcoWPa');
INSERT INTO STUDENT (FIRST_NAME, LAST_NAME, EMAIL, LINKED_IN, PASSWORD) VALUES ('Rebecca', 'Rivera', 'rebecca@mail.com', 'https://se.linkedin.com/in/rebecca-rivera-0926a9227' ,'$2a$11$RCEkkIuaDpUlqn5PyzUjEOvwrNnLSJ4kQKke5QJV6nGaZQiXcoWPa');

INSERT INTO TEACHER (FIRST_NAME, LAST_NAME, EMAIL, LINKED_IN, PASSWORD) VALUES ('Andreas', 'Olsson', 'olsson@mail.com', 'https://se.linkedin.com/in/andreasols/en?', '$2a$11$RCEkkIuaDpUlqn5PyzUjEOvwrNnLSJ4kQKke5QJV6nGaZQiXcoWPa');
INSERT INTO TEACHER (FIRST_NAME, LAST_NAME, EMAIL, LINKED_IN, PASSWORD) VALUES ('Oscar', 'Olsson', 'Oscar.olsson@mail.com', 'https://se.linkedin.com/in/happybits?', '$2a$11$RCEkkIuaDpUlqn5PyzUjEOvwrNnLSJ4kQKke5QJV6nGaZQiXcoWPa');

--INSERT INTO TEACHER_ANNOUNCEMENT (TITLE, CONTENT, TEACHER_NAME, DATE) VALUES ('FIRST CEMENT','Hejsan hoppsan', (SELECT ID FROM TEACHER WHERE FIRST_NAME = 'Andreas'), '2022-10-10');
--INSERT INTO TEACHER_ANNOUNCEMENT (TITLE, CONTENT, TEACHER_NAME, DATE) VALUES ('SECOND CEMENT','Hejdå san hoppsan', (SELECT ID FROM TEACHER WHERE FIRST_NAME = 'Oscar'), '2022-09-27');