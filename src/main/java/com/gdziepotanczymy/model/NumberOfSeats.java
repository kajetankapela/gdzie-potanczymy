package com.gdziepotanczymy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumberOfSeats {
    private Integer allSeats;
    private Integer freeSeats;
    private Integer unconfirmedSeats;
    private Integer confirmedSeats;
}
