package com.example.LedgerMadeEasy.mappers;

import com.example.LedgerMadeEasy.dtos.CreatePartyRequest;
import com.example.LedgerMadeEasy.dtos.PartyDto;
import com.example.LedgerMadeEasy.entities.Party;
import com.example.LedgerMadeEasy.entities.Transaction;
import com.example.LedgerMadeEasy.TransactionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartyMapper {

    @Mapping(target = "partyId", source = "party.id")
    @Mapping(target = "partyName", source = "party.name")
    @Mapping(target = "totalCredit", source = "transactions", qualifiedByName = "calculateCredit")
    @Mapping(target = "totalDebit", source = "transactions", qualifiedByName = "calculateDebit")
    @Mapping(target = "currentBalance", source = "party.balance")
    PartyDto toPartyDto(Party party, List<Transaction> transactions);

    @Mapping(target = "partyId", source = "id")
    @Mapping(target = "partyName", source = "name")
    @Mapping(target = "totalCredit", constant = "0.0")
    @Mapping(target = "totalDebit", constant = "0.0")
    @Mapping(target = "currentBalance", source = "balance")
    PartyDto toPartyDto(Party party);


    @Named("calculateCredit")
    default double calculateCredit(List<Transaction> transactions) {
        if (transactions == null) return 0;
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.CREDIT)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Named("calculateDebit")
    default double calculateDebit(List<Transaction> transactions) {
        if (transactions == null) return 0;
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.DEBIT)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    Party toEntity(CreatePartyRequest createPartyRequest);
}
