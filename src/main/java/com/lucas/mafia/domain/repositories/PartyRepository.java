package com.lucas.mafia.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.mafia.domain.models.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    
}
