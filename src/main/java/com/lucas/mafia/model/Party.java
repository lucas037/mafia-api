package com.lucas.mafia.model;

import java.util.ArrayList;

import com.lucas.mafia.enums.GameStatus;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private ArrayList<Player> players;
    private GameStatus status;

    public Party(String leaderName) {
        players = new ArrayList<>();
        players.add(new Player(leaderName));

        status = GameStatus.LOBBY;
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public void removePlayer(Long id) {
        players.removeIf(player -> player.getId().equals(id));
    }

    public void playerReady(Long id) {
        int playersReady = 0;

        for (Player player : players) {
            if (player.getId().equals(id))
                player.setReady(true);

            if (player.isReady())
                playersReady++;
        }

        if (playersReady == players.size())
            status = GameStatus.ASSASSIN_TURN;
    }
}
