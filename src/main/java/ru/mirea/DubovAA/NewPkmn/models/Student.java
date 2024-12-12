package ru.mirea.DubovAA.NewPkmn.models;

import lombok.*;
import java.io.Serializable;

import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    public static final long serialVersionUID = 1L;
    private String firstName;
    private String surName;
    private String familyName;
    private String group;

    public static Student fromEntityStudent(StudentEntity entity) {
        return Student.builder()
                .firstName(entity.getFirstName())
                .surName(entity.getSurName())
                .familyName(entity.getFamilyName())
                .group(entity.getGroup())
                .build();
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
