package com.example.w_houseapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ProductDto {
    private String name;
    private Double amount;
    private Long categoryId;
}
