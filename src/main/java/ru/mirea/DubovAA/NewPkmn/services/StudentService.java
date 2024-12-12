package ru.mirea.DubovAA.NewPkmn.services;


import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;

import java.util.Optional;
import java.util.List;

public interface StudentService {
    List<StudentEntity> getStudentsByGroup(String group);

    List<StudentEntity> getStudentByFullName(StudentEntity student);

    List<StudentEntity> findAllStudents();

    StudentEntity save(StudentEntity studentEntity);
}
