package com.example.exam1.Services;


import com.example.exam1.Models.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class TeacherServices {

    ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher newTeacher) {
        teachers.add(newTeacher);
    }

    public boolean updateTeacher(Integer id, Teacher newTeacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (Objects.equals(teachers.get(i).getId(), id)) {
                teachers.set(i, newTeacher);
                return true;
            }
        }
        return false;
    }


    public boolean deleteTeacher(Integer id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (Objects.equals(teachers.get(i).getId(), id)) {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public Teacher serachTeacher(Integer id) {
        for (Teacher t : teachers
        ) {
            if (Objects.equals(t.getId(), id)) {
                return t;
            }
        }
        return new Teacher(-1, "ERROR", 0);
    }
}
