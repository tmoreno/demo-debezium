package com.tmoreno.student;

import com.tmoreno.student.application.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExamplesTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void create_student() {
        int studentId = studentService.createStudent("Tomas", "tomas@example.com", "Random Street");
        studentService.changeStudentEmail(studentId, "tomas2@empresa.com");
    }
}
