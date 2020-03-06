package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.NumberOfSeats;
import com.gdziepotanczymy.service.dto.CreateUpdateNumberOfSeatsDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class NumberOfSeatsModelMapper {
    @Transactional
    public NumberOfSeats toModel(CreateUpdateNumberOfSeatsDto createUpdateNumberOfSeatsDto) {
        return NumberOfSeats.builder()
                .allSeats(createUpdateNumberOfSeatsDto.getAllSeats())
                .freeSeats(createUpdateNumberOfSeatsDto.getFreeSeats())
                .confirmedSeats(createUpdateNumberOfSeatsDto.getConfirmedSeats())
                .unconfirmedSeats(createUpdateNumberOfSeatsDto.getUnconfirmedSeats())
                .build();
    }
}
