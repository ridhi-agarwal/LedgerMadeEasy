package com.example.LedgerMadeEasy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePartyRequest {
    @NotBlank(message = "Party name is required")
    @Size(min=2,max=50, message="Party must be between{min} and {max} characters")
    @Pattern(regexp = "^[\\\\w\\\\s-]+$" , message = "Party name can only contain letters, numbers and spaces")
    private String name;
}
