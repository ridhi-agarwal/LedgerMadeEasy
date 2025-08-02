package com.example.LedgerMadeEasy.controllers;

import com.example.LedgerMadeEasy.dtos.PartyDto;
import com.example.LedgerMadeEasy.entities.Party;
import com.example.LedgerMadeEasy.mappers.PartyMapper;
import com.example.LedgerMadeEasy.repositories.PartyRepository;
import com.example.LedgerMadeEasy.repositories.UserRepository;
import com.example.LedgerMadeEasy.services.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/parties")
public class PartyController {
    private final PartyService partyService;
    private final PartyMapper partyMapper;

    @GetMapping
    public ResponseEntity<List<PartyDto>> getPartiesByUser(@RequestParam UUID userId){
        List<Party> parties =partyService.getPartiesByUser(userId);
        return ResponseEntity.ok(
                parties.stream().map(partyMapper::toPartyDto).toList()
        );
    }

}
