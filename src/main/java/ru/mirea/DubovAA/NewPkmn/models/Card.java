package ru.mirea.DubovAA.NewPkmn.models;

import lombok.*;
import ru.mirea.DubovAA.NewPkmn.entities.CardEntity;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card implements Serializable {
    public static final long serialVersionUID = 1L;
    private PokemonStage pokemonStage;
    private String name;
    private int hp;
    private EnergyType pokemonType;
    private Card evolvesFrom;
    private List<AttackSkill> skills;
    private EnergyType weaknessType;
    private EnergyType resistanceType;
    private String retreatCost;
    private String gameSet;
    private char regulationMark;
    private Student pokemonOwner;
    private String number;

    public static Card fromEntityCard(CardEntity entity) {
        return Card.builder()
                .pokemonStage(entity.getPokemonStage())
                .name(entity.getName())
                .hp(entity.getHp())
                .pokemonType(entity.getPokemonType())
                .evolvesFrom(entity.getEvolvesFrom() != null ? fromEntityCard(entity.getEvolvesFrom()) : null)
                .skills(entity.getSkills())
                .weaknessType(entity.getWeaknessType())
                .resistanceType(entity.getResistanceType())
                .retreatCost(entity.getRetreatCost())
                .gameSet(entity.getGameSet())
                .regulationMark(entity.getRegulationMark())
                .pokemonOwner(entity.getPokemonOwner() != null ? Student.fromEntityStudent(entity.getPokemonOwner()) : null)
                .number(entity.getNumber())
                .build();
    }

    @Override
    public String toString() {
        return "Card{" +
                "pokemonStage=" + pokemonStage +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", pokemonType=" + pokemonType +
                ", evolvesFrom=" + evolvesFrom +
                ", skills=" + skills +
                ", weaknessType=" + weaknessType +
                ", resistanceType=" + resistanceType +
                ", retreatCost='" + retreatCost + '\'' +
                ", gameSet='" + gameSet + '\'' +
                ", regulationMark=" + regulationMark +
                ", pokemonOwner=" + pokemonOwner +
                ", number=" + number +
                '}';
    }
}
