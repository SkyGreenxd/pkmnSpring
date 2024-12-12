package ru.mirea.DubovAA.NewPkmn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.DubovAA.NewPkmn.dao.CardDao;
import ru.mirea.DubovAA.NewPkmn.dao.StudentDao;
import ru.mirea.DubovAA.NewPkmn.entities.CardEntity;
import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardDao cardDao;
    private final StudentDao studentDao;

    @Override
    public CardEntity getCardByName(String name) {
        return cardDao.getCardByName(name);
    }

    @Override
    public CardEntity getCardById(UUID id) {
        return cardDao.getCardById(id);
    }

    @Override
    public CardEntity getCardByFIO(StudentEntity student) {
        return cardDao.getCardByStudent(student);
    }

    @Override
    @Transactional
    public CardEntity saveCard(CardEntity card) {
        if (cardDao.cardExists(card)) {
            throw new IllegalArgumentException("Карта с именем " + card.getName() + " уже существует");
        }
        if(card.getPokemonOwner() != null){
            if(studentDao.studentExists(card.getPokemonOwner())){
                card.setPokemonOwner(studentDao.getStudentsByFIO(card.getPokemonOwner()).getFirst());
            }
            else {
                card.setPokemonOwner(studentDao.saveStudent(card.getPokemonOwner()));
            }
        }
        if(card.getEvolvesFrom() != null)
        {
            if(cardDao.cardExists(card.getEvolvesFrom())){
                card.setEvolvesFrom(cardDao.getCardByName(card.getEvolvesFrom().getName()));
            }
            else {
                card.setEvolvesFrom(cardDao.saveCard(card.getEvolvesFrom()));
            }
        }
        return cardDao.saveCard(card);
    }


    @Override
    public List<CardEntity> findAllCards(){
        return cardDao.findAllCard();
    }
}
