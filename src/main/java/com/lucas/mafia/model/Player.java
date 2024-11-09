package com.lucas.mafia.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String name;
    private boolean ready;

    public Player(String name) {
        this.name = name;
        ready = false;
    }
}
