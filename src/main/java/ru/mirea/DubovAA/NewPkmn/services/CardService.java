package ru.mirea.DubovAA.NewPkmn.services;

import ru.mirea.DubovAA.NewPkmn.entities.CardEntity;
import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;

import java.util.List;
import java.util.UUID;

public interface CardService {
    CardEntity getCardById(UUID id);

    CardEntity getCardByFIO(StudentEntity student);

    CardEntity getCardByName(String name);

    CardEntity saveCard(CardEntity card);

    List<CardEntity> findAllCards();
}