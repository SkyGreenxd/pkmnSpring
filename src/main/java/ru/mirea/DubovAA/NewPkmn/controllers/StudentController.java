package ru.mirea.DubovAA.NewPkmn.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;
import ru.mirea.DubovAA.NewPkmn.services.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/all")
    public List<StudentEntity> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{group}")
    public List<StudentEntity> getStudentsByGroup(@PathVariable String group) {
        return studentService.getStudentsByGroup(group);
    }

    @GetMapping("")
    public ResponseEntity<String> getStudentByFullName(@RequestBody StudentEntity student) {
        List<StudentEntity> students = studentService.getStudentByFullName(student);

        if (students.size() > 1) {
            throw new IllegalArgumentException("Найдено несколько пользователей с такими ФИО.");
        }
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент не найден.");
        }
        return ResponseEntity.ok("Студент найден: " + students.get(0).toString());
    }


    @PostMapping("")
    public ResponseEntity<String> createStudent(@RequestBody StudentEntity student) {
        List<StudentEntity> students = studentService.getStudentByFullName(student);
        if (!students.isEmpty()) {
            return ResponseEntity.badRequest().body("Студент с такими ФИО уже существует.");
        }
        return ResponseEntity.ok(studentService.save(student).toString());
    }
}
