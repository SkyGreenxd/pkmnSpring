package ru.mirea.DubovAA.NewPkmn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.DubovAA.NewPkmn.entities.CardEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardEntityRepository extends JpaRepository<CardEntity, UUID> {
    <S extends CardEntity> S save(S entity);

    Optional<CardEntity> findByName(String name);

    Optional<CardEntity> findById(UUID id);

    List<CardEntity> findByPokemonOwner_id(UUID studentId);

    boolean existsByName(String name);
}
