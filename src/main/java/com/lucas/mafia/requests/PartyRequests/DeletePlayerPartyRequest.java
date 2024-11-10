package com.lucas.mafia.requests.PartyRequests;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePlayerPartyRequest {
    private UUID playerId;
    private Long partyId; 
}
