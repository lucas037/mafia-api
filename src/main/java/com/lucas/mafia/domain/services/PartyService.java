package com.lucas.mafia.domain.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.mafia.domain.models.Party;
import com.lucas.mafia.domain.repositories.PartyRepository;

@Service
public class PartyService {
    @Autowired
    PartyRepository partyRepository;

    public Party create(String name) {
        Party party = new Party(name);
        return partyRepository.save(party);
    }

    public Party get(Long partyId) {
        Optional<Party> partyOptional = partyRepository.findById(partyId);

        if (!partyOptional.isPresent())
            return null;

        return partyOptional.get();
    }

    public Party join(String name, Long partyId) {
        Optional<Party> partyOptional = partyRepository.findById(partyId);

        if (partyOptional.isPresent()) {
            Party party = partyOptional.get();
            party.addPlayer(name);
            return partyRepository.save(party);
        }
        
        return null;
    }

    public Party removePlayer(Long partyId, Long playerId) {
        Optional<Party> partyOptional = partyRepository.findById(partyId);

        if (!partyOptional.isPresent())
            return null;

        Party party = partyOptional.get();
        party.removePlayer(playerId);
        return partyRepository.save(party);
    }

    public Party playerReady(Long partyId, Long playerId) {
        Optional<Party> partyOptional = partyRepository.findById(partyId);

        if (!partyOptional.isPresent())
            return null;

        Party party = partyOptional.get();
        party.playerReady(playerId);
        return partyRepository.save(party);
    }
}
