package com.example.w_houseapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class WarehouseDto {
    private String name;
    private boolean active;
}
