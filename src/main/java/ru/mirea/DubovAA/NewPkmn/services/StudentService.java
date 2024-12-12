package ru.mirea.DubovAA.NewPkmn.services;


import org.springframework.stereotype.Service;
import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;

import java.util.Optional;
import java.util.List;

@Service
public interface StudentService {
    List<StudentEntity> getStudentsByGroup(String group);

    Optional<StudentEntity> getStudentByFIO(StudentEntity student);

    List<StudentEntity> findAllStudents();

    StudentEntity save(StudentEntity studentEntity);
}
