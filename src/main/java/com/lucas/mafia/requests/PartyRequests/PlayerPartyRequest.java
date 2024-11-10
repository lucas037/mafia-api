package com.lucas.mafia.requests.PartyRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerPartyRequest {
    private Long playerId;
    private Long partyId; 
}
