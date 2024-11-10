package com.lucas.mafia.domain.models;

import java.util.ArrayList;
import java.util.Random;

import com.lucas.mafia.enums.GameStatus;
import com.lucas.mafia.enums.PlayerType;

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
            startGame();
    }

    public void startGame() {
        changes++;
        status = GameStatus.ASSASSIN_TURN;

        Random rand = new Random();

        // Define todos os jogadores como cicadões
        for (Player player : players) {
            player.setType(PlayerType.CITIZEN);
            player.starGame();
        }

        // Define um assassino
        int var = rand.nextInt(players.size());
        players.get(var).setType(PlayerType.ASSASSIN);

        // Define um médico
        while (players.get(var).getType() != PlayerType.CITIZEN)
            var = rand.nextInt(players.size());
        players.get(var).setType(PlayerType.DOCTOR);

        // Define um detetive
        while (players.get(var).getType() != PlayerType.CITIZEN)
            var = rand.nextInt(players.size());
        players.get(var).setType(PlayerType.DETECTIVE);
        
    }
}
