package com.lucas.mafia.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.mafia.domain.models.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    
}
