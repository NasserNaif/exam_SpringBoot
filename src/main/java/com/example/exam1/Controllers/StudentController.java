package com.example.exam1.Controllers;


import com.example.exam1.API.ApiResponse;
import com.example.exam1.Models.Student;
import com.example.exam1.Models.Teacher;
import com.example.exam1.Services.StudentServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentServices studentServices;

    @GetMapping("/get")
    public ArrayList<Student> getAllStudent() {
        return studentServices.getStudents();
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student newStudent, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        } else {
            studentServices.addStudent(newStudent);
            return ResponseEntity.status(201).body(new ApiResponse("student added"));
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody @Valid Student newStudent, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = studentServices.updateStudent(id, newStudent);
        if (isUpdated) {
            return ResponseEntity.status(201).body(new ApiResponse("student updated"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("sorry! there's no student with this ID : " + id));

        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        boolean isDeleted = studentServices.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("student deleted"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("sorry! there's no student with this ID : " + id));

        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity searchTeacher(@PathVariable String name) {
        Student result = studentServices.studentSearch(name);

        if (result.getId() < 0) {
            return ResponseEntity.status(400).body(new ApiResponse("there's no student with this name : " + name));
        } else {
            return ResponseEntity.status(200).body(result);
        }
    }
}
