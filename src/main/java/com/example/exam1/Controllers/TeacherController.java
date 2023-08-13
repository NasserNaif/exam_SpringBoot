package com.example.exam1.Controllers;


import com.example.exam1.API.ApiResponse;
import com.example.exam1.Models.Teacher;
import com.example.exam1.Services.TeacherServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherServices teacherServices;

    @GetMapping("/get")
    public ArrayList<Teacher> getAllTeacher() {
        return teacherServices.getTeachers();
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher newTeacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        teacherServices.addTeacher(newTeacher);
        return ResponseEntity.status(201).body(new ApiResponse("teacher added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable Integer id, @RequestBody @Valid Teacher newTeacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = teacherServices.updateTeacher(id, newTeacher);
        if (isUpdated) {
            return ResponseEntity.status(201).body(new ApiResponse("teacher updated"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("sorry! there's no teacher with this ID : " + id));

        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable Integer id) {
        boolean isDeleted = teacherServices.deleteTeacher(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("teacher deleted"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("sorry! there's no teacher with this ID : " + id));

        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity searchTeacher(@PathVariable Integer id) {
        Teacher result = teacherServices.serachTeacher(id);

        if (result.getId() < 0) {
            return ResponseEntity.status(400).body(new ApiResponse("there's no teacher with this ID : " + id));
        } else {
            return ResponseEntity.status(200).body(result);
        }
    }
}
