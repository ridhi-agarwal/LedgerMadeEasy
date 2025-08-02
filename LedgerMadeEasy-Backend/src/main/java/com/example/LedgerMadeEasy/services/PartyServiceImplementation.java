package com.example.LedgerMadeEasy.services;

import com.example.LedgerMadeEasy.entities.Party;
import com.example.LedgerMadeEasy.entities.Transaction;
import com.example.LedgerMadeEasy.entities.User;
import com.example.LedgerMadeEasy.repositories.PartyRepository;
import com.example.LedgerMadeEasy.repositories.UserRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PartyServiceImplementation implements PartyService{
    private final PartyRepository partyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Party createParty(UUID userId, Party party) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Party createdParty = Party.builder()
                .name(party.getName())
                .balance(0.0)
                .user(user)
                .build();
        return partyRepository.save(createdParty);
    }

    @Override
    public List<Party> getPartiesByUser(UUID userId) {
        return partyRepository.findAll().stream()
                .filter(p -> p.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Party updateOpeningBalance(UUID partyId, Double openingBalance) {
        return null;
    }

    @Override
    public Party resetBalance(UUID partyId, Double newBalance) {
        return null;
    }

    @Override
    public Party getPartySummary(UUID partyId) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsForParty(Long partyId) {
        return List.of();
    }
}
