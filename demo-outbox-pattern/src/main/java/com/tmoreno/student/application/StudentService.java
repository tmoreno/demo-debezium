package com.tmoreno.student.application;

import com.tmoreno.student.domain.DomainEvent;
import com.tmoreno.student.domain.StudentRepository;
import com.tmoreno.student.infraestructure.EventPublisher;
import com.tmoreno.student.infraestructure.StudentEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EventPublisher eventPublisher;

    public StudentService(StudentRepository studentRepository, EventPublisher eventPublisher) {
        this.studentRepository = studentRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public int createStudent(String name, String email, String address) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(name);
        studentEntity.setEmail(email);
        studentEntity.setAddress(address);

        studentRepository.save(studentEntity);

        DomainEvent event = new DomainEvent(
            "studentCreated",
            studentEntity.getStudentId(),
            Map.of("studentId", studentEntity.getStudentId(), "name", name, "email", email, "address", address)
        );

        eventPublisher.fire(event);

        return studentEntity.getStudentId();
    }

    @Transactional
    public void changeStudentEmail(int studentId, String email) {
        StudentEntity studentEntity = studentRepository.getOne(studentId);
        studentEntity.setEmail(email);

        studentRepository.save(studentEntity);

        DomainEvent event = new DomainEvent(
            "studentEmailChanged",
            studentId,
            Map.of("studentId", studentId, "email", email)
        );

        eventPublisher.fire(event);
    }
}
