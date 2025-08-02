package com.example.LedgerMadeEasy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartyDto {
    UUID partyId;
    String partyName;
    double totalCredit;
    double totalDebit;
    double currentBalance;
}
