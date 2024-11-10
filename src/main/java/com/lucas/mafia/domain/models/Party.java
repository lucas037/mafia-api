package com.lucas.mafia.domain.models;

import java.util.ArrayList;
import java.util.UUID;

import com.lucas.mafia.enums.GameStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private final int minPlayers = 4;
    private int changes;
    private ArrayList<Player> players;
    private GameStatus status;

    public Party() {}

    public Party(String leaderName) {
        players = new ArrayList<>();
        players.add(new Player(leaderName, true));

        status = GameStatus.LOBBY;
    }

    public void addPlayer(String name) {
        changes++;
        players.add(new Player((long)changes, name));
    }

    public void removePlayer(Long id) {
        changes++;
        players.removeIf(player -> player.getId().equals(id));

        if (!players.get(0).isLeader())
            players.get(0).setLeader(true);
    }

    public void playerReady(Long id) {
        changes++;
        int playersReady = 0;

        for (Player player : players) {
            if (player.getId().equals(id))
                player.setReady(true);

            if (player.isReady())
                playersReady++;
        }

        if (playersReady == players.size() && playersReady >= minPlayers)
            status = GameStatus.ASSASSIN_TURN;
    }

    public void startGame() {
        changes++;
        status = GameStatus.ASSASSIN_TURN;
    }
}
