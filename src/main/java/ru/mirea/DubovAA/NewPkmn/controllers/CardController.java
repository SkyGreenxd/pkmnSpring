package ru.mirea.DubovAA.NewPkmn.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.DubovAA.NewPkmn.entities.CardEntity;
import ru.mirea.DubovAA.NewPkmn.entities.StudentEntity;
import ru.mirea.DubovAA.NewPkmn.rest.PokemonTcgService;
import ru.mirea.DubovAA.NewPkmn.services.CardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final PokemonTcgService pokemonTcgService;

    @GetMapping("")
    public List<CardEntity> getAllCards() {
        return cardService.findAllCards();
    }

    @GetMapping("/{name}")
    public CardEntity getCardByName(@PathVariable String name) {
        return cardService.getCardByName(name);
    }

    @GetMapping("/owner")
    public CardEntity getCardByFIO(@RequestBody StudentEntity student) {
        return cardService.getCardByFIO(student);
    }

    @GetMapping("/id/{id}")
    public CardEntity getCardById(@PathVariable UUID id) {
        return cardService.getCardById(id);
    }

    @PostMapping
    public ResponseEntity<String> createCard(@RequestBody CardEntity card) {
        if (card.getPokemonOwner() == null){
            return ResponseEntity.badRequest().body("Карте нужен владелец.");
        }
        return ResponseEntity.ok(cardService.saveCard(card).toString());
    }

    @GetMapping("/card-image")
    public ResponseEntity<String> getCardImage(@RequestBody CardEntity card) {
        try {
            String imageUrl = pokemonTcgService.getCardImageUrl(card.getName(), card.getNumber());
            return ResponseEntity.status(HttpStatus.OK).body(imageUrl);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card image not found.");
        }
    }
}
