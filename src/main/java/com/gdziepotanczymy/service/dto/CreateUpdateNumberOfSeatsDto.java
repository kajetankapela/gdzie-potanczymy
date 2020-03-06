package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateNumberOfSeatsDto {
    @NotBlank
    private Integer allSeats;
    private Integer freeSeats;
    private Integer unconfirmedSeats;
    private Integer confirmedSeats;
}
