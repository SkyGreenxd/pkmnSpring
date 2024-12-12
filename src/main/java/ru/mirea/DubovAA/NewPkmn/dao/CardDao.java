package ru.mirea.DubovAA.NewPkmn.dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.mirea.DubovAA.NewPkmn.entities.CardEntity;
import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;
import ru.mirea.DubovAA.NewPkmn.repositories.CardEntityRepository;
import ru.mirea.DubovAA.NewPkmn.repositories.StudentEntityRepository;

import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class CardDao {
    private final CardEntityRepository cardRep;
    private final StudentEntityRepository studentRep;

    public CardEntity getCardByName(String name) {
        return cardRep.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("Card with this name " + name + " not found")
        );
    }

    public CardEntity getCardById(UUID id) {
        return cardRep.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Card with this id " + id + " not found")
        );
    }

    @SneakyThrows
    public CardEntity getCardByStudent(StudentEntity student) {
        StudentEntity students = studentRep.findByFirstNameAndSurNameAndFamilyNameAndGroup(student.getFirstName(),
                        student.getSurName(), student.getFamilyName(), student.getGroup())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return cardRep.findByPokemonOwner_id(students.getId()).getFirst();
    }

    public boolean cardExists(CardEntity card) {
        return cardRep.existsByName(card.getName());
    }

    public CardEntity saveCard(CardEntity card) {
        return cardRep.saveAndFlush(card);
    }

    public List<CardEntity> findAllCard(){
        return cardRep.findAll();
    }
}
