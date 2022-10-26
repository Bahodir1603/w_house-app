package com.example.w_houseapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ProductMeasurementDto {
    @NotNull(message = "Product ni kiritish shart")
    private Long productId;
    @NotNull(message = "Measurement ni kiritish shart")
    private String measurement;
}
