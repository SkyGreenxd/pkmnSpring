package ru.mirea.DubovAA.NewPkmn.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.DubovAA.NewPkmn.dao.StudentDao;
import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    @Override
    public List<StudentEntity> getStudentsByGroup(String group) {
        return studentDao.getStudentsByGroup(group);
    }

    @Override
    public Optional<StudentEntity> getStudentByFIO(StudentEntity student) {
        List<StudentEntity> students = studentDao.getStudentsByFIO(student);
        if (students.size() > 1) {
            throw new RuntimeException("More than one user found with the provided full name.");
        }
        return students.stream().findFirst();
    }


    @Override
    public List<StudentEntity> findAllStudents() {
        return studentDao.findAllStudent();
    }

    @Override
    public StudentEntity save(StudentEntity studentEntity) {
        return studentDao.saveStudent(studentEntity);
    }
}
