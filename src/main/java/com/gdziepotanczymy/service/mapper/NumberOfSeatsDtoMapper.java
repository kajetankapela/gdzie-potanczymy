package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.NumberOfSeats;
import com.gdziepotanczymy.service.dto.NumberOfSeatsDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class NumberOfSeatsDtoMapper {
    @Transactional
    public NumberOfSeatsDto toDto(NumberOfSeats numberOfSeats) {
        return NumberOfSeatsDto.builder()
                .allSeats(numberOfSeats.getAllSeats())
                .freeSeats(numberOfSeats.getFreeSeats())
                .confirmedSeats(numberOfSeats.getConfirmedSeats())
                .unconfirmedSeats(numberOfSeats.getUnconfirmedSeats())
                .build();
    }
}
