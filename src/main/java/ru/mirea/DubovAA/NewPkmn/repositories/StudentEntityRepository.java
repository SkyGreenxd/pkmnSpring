package ru.mirea.DubovAA.NewPkmn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, UUID> {
    List<StudentEntity> findByGroup(String group);

    List<StudentEntity> findByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName);

    boolean existsByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName);

    List<StudentEntity> findAll();

    Optional<StudentEntity> findByFirstNameAndSurNameAndFamilyNameAndGroup(String firstName, String surName, String familyName, String group);
}
