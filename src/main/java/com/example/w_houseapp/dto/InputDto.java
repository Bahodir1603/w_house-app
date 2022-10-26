package com.example.w_houseapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class InputDto {
    @NotNull(message = "Supplier kiritish majburiry")
    private String supplierId;
    @NotNull(message = "facture kiritish shart")
    private int facture_number;
    @NotNull(message = "Warehouse kiritish shart")
    private Long warehouseId;
    @NotNull(message = "Currency kiritish shart")
    private String currency;
}
