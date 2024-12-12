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
    public Optional<StudentEntity> getUserByFullName(@RequestBody StudentEntity student) {
        return studentService.getStudentByFIO(student);
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentEntity student) {
        if (studentService.getStudentByFIO(student).isPresent()) {
            return ResponseEntity.badRequest().body("Такой студент уже существует.");
        }
        return ResponseEntity.ok(studentService.save(student).toString());
    }
}
