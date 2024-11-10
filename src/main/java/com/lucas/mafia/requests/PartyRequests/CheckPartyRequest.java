package com.lucas.mafia.requests.PartyRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckPartyRequest {
    private Long partyId;
    private int changes;
}
