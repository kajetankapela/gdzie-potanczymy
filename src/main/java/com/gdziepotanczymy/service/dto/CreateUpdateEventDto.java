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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;
    @NotBlank
    private String description;
    private String comments;
    @JsonUnwrapped
    @Builder.Default
    private CreateUpdateAddressDto createUpdateAddressDto = new CreateUpdateAddressDto();
    @JsonUnwrapped
    @Builder.Default
    private CreateUpdateNumberOfSeatsDto createUpdateNumberOfSeatsDto = new CreateUpdateNumberOfSeatsDto();
}
