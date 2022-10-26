package com.example.w_houseapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OutputDto {
    @NotNull(message = "Customerni kiritish shart")
    private String customerId;
    @NotNull(message = "Warehouse ni kiritish shart")
    private Long warehouseId;
    @NotNull(message = "Currency ni kiritish shart")
    private String currency;
}
