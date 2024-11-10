package com.lucas.mafia.requests.PartyRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinPartyRequest {
    private String name;
    private Long partyId;
}
