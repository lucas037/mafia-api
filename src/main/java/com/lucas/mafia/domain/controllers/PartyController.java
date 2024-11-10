package com.lucas.mafia.domain.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.mafia.domain.models.Party;
import com.lucas.mafia.domain.services.PartyService;
import com.lucas.mafia.requests.PartyRequests.CheckPartyRequest;
import com.lucas.mafia.requests.PartyRequests.CreatePartyRequest;
import com.lucas.mafia.requests.PartyRequests.GetPartyRequest;
import com.lucas.mafia.requests.PartyRequests.JoinPartyRequest;
import com.lucas.mafia.requests.PartyRequests.PlayerPartyRequest;

@RequestMapping("api/mafia/party")
@RestController
public class PartyController {
    @Autowired
    PartyService partyService;


    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody CreatePartyRequest request) {
        Party party = partyService.create(request.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("id", party.getId());
        response.put("playerId", party.getPlayers().get(0).getId());

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestBody GetPartyRequest request) {
        Party party = partyService.get(request.getPartyId());

        if (party == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Party not found."));

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(party);
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(@RequestBody CheckPartyRequest request) {
        Party party = partyService.get(request.getPartyId());

        if (party == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Party not found."));
        }

        boolean changed = false;

        if (party.getChanges() > request.getChanges())
            changed = true;

        Map<String, Object> response = new HashMap<>();
        response.put("changed", changed);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> join(@RequestBody JoinPartyRequest request) {
        Party party = partyService.join(request.getName(), request.getPartyId());
    
        if (party == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Party not found with ID: " + request.getPartyId()));
    
        Map<String, Object> response = new HashMap<>();
        response.put("partyId", request.getPartyId());
        response.put("id", party.getPlayers().get(party.getPlayers().size() - 1).getId());
    
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removePlayer(@RequestBody PlayerPartyRequest request) {
        Party party = partyService.removePlayer(request.getPartyId(), request.getPlayerId());
        
        if (party == null)
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Party not found."));

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null);
    }

    @PostMapping("/ready")
    public ResponseEntity<?> playerReady(@RequestBody PlayerPartyRequest request) {
        Party party = partyService.playerReady(request.getPartyId(), request.getPlayerId());
        
        if (party == null)
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Party not found."));

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null);

    }
    
}
