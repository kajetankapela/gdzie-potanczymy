package com.gdziepotanczymy.service.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateEventDto {
    @NotBlank
    private String name;
    private Integer startDay;
    private Integer startMonth;
    private Integer startYear;
    private Integer endDay;
    private Integer endMonth;
    private Integer endYear;
    @NotBlank
    private String description;
    private String comments;
    private String organizerLogin;
    @JsonUnwrapped
    @Builder.Default
    private CreateUpdateAddressDto createUpdateAddressDto = new CreateUpdateAddressDto();
    @JsonUnwrapped
    @Builder.Default
    private CreateUpdateNumberOfSeatsDto createUpdateNumberOfSeatsDto = new CreateUpdateNumberOfSeatsDto();
}
