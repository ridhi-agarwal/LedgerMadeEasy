package com.example.LedgerMadeEasy.controllers;

import com.example.LedgerMadeEasy.dtos.CreatePartyRequest;
import com.example.LedgerMadeEasy.dtos.PartyDto;
import com.example.LedgerMadeEasy.entities.Party;
import com.example.LedgerMadeEasy.entities.Transaction;
import com.example.LedgerMadeEasy.mappers.PartyMapper;
import com.example.LedgerMadeEasy.services.PartyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/parties")
public class PartyController {
    private final PartyService partyService;
    private final PartyMapper partyMapper;

    @GetMapping
    public ResponseEntity<List<PartyDto>> getPartiesByUser(@RequestParam UUID userId) {
        List<Party> parties = partyService.getPartiesByUser(userId);
        List<PartyDto> result = parties.stream()
                .map(party -> {
                    List<Transaction> txns = partyService.getTransactionsForParty(party.getId());
                    return partyMapper.toPartyDto(party, txns);
                }).toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<PartyDto> createParty(@RequestParam UUID userId, @Valid @RequestBody CreatePartyRequest createPartyRequest){
        Party party = partyMapper.toEntity(createPartyRequest);
        Party saveParty = partyService.createParty(userId, party);
        return new ResponseEntity<>(partyMapper.toPartyDto(saveParty), HttpStatus.CREATED);
    }





}
