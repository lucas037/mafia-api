package com.lucas.mafia.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Player implements Serializable {
    @Id
    private Long id;
    private String name;
    private boolean leader;
    private boolean ready;

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
        this.ready = false;
    }

    public Player(String name, boolean leader) {
        this(0L, name);
        this.leader = leader;
    }
}
