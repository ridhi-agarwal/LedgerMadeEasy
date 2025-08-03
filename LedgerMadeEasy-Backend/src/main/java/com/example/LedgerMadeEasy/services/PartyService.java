package com.example.LedgerMadeEasy.services;

import com.example.LedgerMadeEasy.dtos.CreatePartyRequest;
import com.example.LedgerMadeEasy.dtos.PartyDto;
import com.example.LedgerMadeEasy.entities.Party;
import com.example.LedgerMadeEasy.entities.Transaction;

import java.util.List;
import java.util.UUID;

public interface PartyService {

        Party createParty(UUID userId, Party party);

        List<Party> getPartiesByUser(UUID userId);

        Party updateOpeningBalance(UUID partyId, Double openingBalance);

        Party resetBalance(UUID partyId, Double newBalance);

        Party getPartySummary(UUID partyId);

        List<Transaction> getTransactionsForParty(UUID partyId);


}
