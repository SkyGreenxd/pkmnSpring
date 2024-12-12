package ru.mirea.DubovAA.NewPkmn.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;
import ru.mirea.DubovAA.NewPkmn.repositories.StudentEntityRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentDao {
    private final StudentEntityRepository studentRep;

    public List<StudentEntity> getStudentsByGroup(String group) {
        return studentRep.findByGroup(group);
    }

    public List<StudentEntity> getStudentByFullName(StudentEntity student) {
        return studentRep.findByFirstNameAndSurNameAndFamilyName(student.getFirstName(), student.getSurName(), student.getFamilyName());
    }

    public boolean studentExists(StudentEntity student) {
        return studentRep.existsByFirstNameAndSurNameAndFamilyName(student.getFirstName(),
                student.getSurName(),
                student.getFamilyName());
    }

    public StudentEntity saveStudent(StudentEntity student) {
        return studentRep.saveAndFlush(student);
    }

    public List<StudentEntity> findAllStudent(){
        return studentRep.findAll();
    }
}
