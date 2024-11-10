package com.lucas.mafia.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Player implements Serializable {
    @Id
    private UUID id;
    private String name;
    private boolean leader;
    private boolean ready;

    public Player(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.ready = false;
    }

    public Player(String name, boolean leader) {
        this(name);
        this.leader = leader;
    }
}
