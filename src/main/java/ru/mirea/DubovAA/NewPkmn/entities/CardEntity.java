package ru.mirea.DubovAA.NewPkmn.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import ru.mirea.DubovAA.NewPkmn.models.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static org.hibernate.type.SqlTypes.JSON;

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

    @JdbcTypeCode(JSON)
    @Column(name="attack_skills", columnDefinition = "JSON")
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

    @Override
    public String toString() {
        return "Card{" +
                "pokemonStage=" + pokemonStage +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", evolvesFrom=" + evolvesFrom +
                ", skills=" + skills +
                ", pokemonType=" + pokemonType +
                ", weaknessType=" + weaknessType +
                ", resistanceType=" + resistanceType +
                ", retreatCost='" + retreatCost + '\'' +
                ", gameSet='" + gameSet + '\'' +
                ", regulationMark=" + regulationMark +
                ", owner=" + ((pokemonOwner != null) ? pokemonOwner.toString() : pokemonOwner)+
                '}';
    }
}
