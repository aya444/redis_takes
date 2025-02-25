package com.aya.learnredis.controller;

import com.aya.learnredis.entity.Student;
import com.aya.learnredis.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/create")
    public String createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/getAll")
    public List<Student> getListOfStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/get/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/update")
    public String updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }
}
