package ru.mirea.DubovAA.NewPkmn.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import ru.mirea.DubovAA.NewPkmn.converters.SkillDeserializer;
import ru.mirea.DubovAA.NewPkmn.models.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name="stage")
    private PokemonStage pokemonStage;

    @Column(name="name")
    private String name;

    @Column(columnDefinition = "integer")
    private int hp;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "evolves_from_id")
    private CardEntity evolvesFrom;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attack_skills")
    @JsonDeserialize(using = SkillDeserializer.class)
    private List<AttackSkill> skills;

    @Column(name="retreat_cost")
    private String retreatCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "pokemon_type", nullable = true)
    private EnergyType pokemonType;

    @Enumerated(EnumType.STRING)
    @Column(name = "weakness_type", nullable = true)
    private EnergyType weaknessType;

    @Enumerated(EnumType.STRING)
    @Column(name = "resistance_type", nullable = true)
    private EnergyType resistanceType;

    @Column(name="game_set")
    private String gameSet;

    @Column(name="regulation_mark")
    private char regulationMark;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "pokemon_owner_id")
    private StudentEntity pokemonOwner;



    @Column(name="card_number")
    private String number;

    public static CardEntity fromCardToEntity(Card card) {
        return CardEntity.builder()
                .pokemonStage(card.getPokemonStage())
                .name(card.getName())
                .hp(card.getHp())
                .pokemonType(card.getPokemonType())
                .evolvesFrom(card.getEvolvesFrom() != null ? fromCardToEntity(card.getEvolvesFrom()) : null)
                .skills(card.getSkills()) // Убедитесь, что формат списка AttackSkill совместим
                .weaknessType(card.getWeaknessType())
                .resistanceType(card.getResistanceType())
                .retreatCost(card.getRetreatCost())
                .gameSet(card.getGameSet())
                .regulationMark(card.getRegulationMark())
                .pokemonOwner(card.getPokemonOwner() != null ? StudentEntity.fromStudentToEntity(card.getPokemonOwner()) : null)
                .number(card.getNumber())
                .build();
    }
}
