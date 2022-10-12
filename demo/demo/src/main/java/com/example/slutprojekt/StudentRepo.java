package com.example.slutprojekt;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Long>{

    Student findByUsername(String username);

}
