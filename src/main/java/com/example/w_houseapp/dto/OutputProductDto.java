package com.example.w_houseapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class OutputProductDto {
    @NotNull(message = "Input kiritish shart")
    private String outputId;
    @NotNull(message = "Product ni kiritish shart")
    private Long productId;
    @NotNull(message = "Amoutnni kiritish shart")
    private Double amount;
    @NotNull(message = "Priceni kiritish shart")
    private Double price;

}
