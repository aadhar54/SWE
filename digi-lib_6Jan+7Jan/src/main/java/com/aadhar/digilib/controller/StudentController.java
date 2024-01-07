package com.aadhar.digilib.controller;

import com.aadhar.digilib.dto.CreateStudentRequest;
import com.aadhar.digilib.dto.UpdateStudentRequest;
import com.aadhar.digilib.model.Student;
import com.aadhar.digilib.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest){
        return studentService.create(createStudentRequest);
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") int studentId){
        return studentService.get(studentId);
    }

    @DeleteMapping
    public Student deleteStudent(@RequestParam("id") int studentId){
        return studentService.delete(studentId);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody @Valid UpdateStudentRequest updateStudentRequest){
        studentService.update(studentId, updateStudentRequest);
        return studentService.get(studentId);
    }
}
