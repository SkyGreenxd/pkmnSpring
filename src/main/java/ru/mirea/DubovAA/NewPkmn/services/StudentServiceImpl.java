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
    public List<StudentEntity> getStudentByFullName(StudentEntity studentEntity) {
        List<StudentEntity> students = studentDao.getStudentByFullName(studentEntity);

        if (students.isEmpty()) {
            // Если студентов не найдено, возвращаем пустой список
            return students;
        } else if (students.size() > 1) {
            // Если найдено больше одного студента, выбрасываем исключение
            throw new IllegalArgumentException("Найдено больше одного студента с указанным ФИО: " + studentEntity);
        } else {
            // Если найден ровно один студент, возвращаем его в списке
            return students;
        }
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
