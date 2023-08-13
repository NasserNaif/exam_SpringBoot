package com.example.exam1.Services;

import com.example.exam1.Models.Student;
import com.example.exam1.Models.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class StudentServices {
    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student newStudent) {
        students.add(newStudent);
    }

    public boolean updateStudent(Integer id, Student newStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (Objects.equals(students.get(i).getId(), id)) {
                students.set(i, newStudent);
                return true;
            }
        }
        return false;
    }


    public boolean deleteStudent(Integer id) {
        for (int i = 0; i < students.size(); i++) {
            if (Objects.equals(students.get(i).getId(), id)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public Student studentSearch(String name) {
        for (Student s : students
        ) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return new Student(-1, "ERROR", 0, "ERROR");
    }
}
