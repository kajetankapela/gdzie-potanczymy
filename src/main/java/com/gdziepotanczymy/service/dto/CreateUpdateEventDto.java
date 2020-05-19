package com.gdziepotanczymy.service.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateEventDto {
    @NotBlank
    private String name;
    @NotBlank
    private Integer startDay;
    @NotBlank
    private Integer startMonth;
    @NotBlank
    private Integer startYear;
    @NotBlank
    private Integer endDay;
    @NotBlank
    private Integer endMonth;
    @NotBlank
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
