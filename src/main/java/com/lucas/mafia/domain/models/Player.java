package com.lucas.mafia.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

import com.lucas.mafia.enums.PlayerType;

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
    private PlayerType type;
    private boolean alive = false;
    private boolean shooted = false;
    private boolean healed = false;
    private boolean investigeted = false;

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
        this.ready = false;
        this.type = PlayerType.NONE;
    }

    public Player(String name, boolean leader) {
        this(0L, name);
        this.leader = leader;
    }

    public void starGame() {
        this.alive = true;
        this.shooted = false;
        this.healed = false;
        this.investigeted = false;
    }
}
