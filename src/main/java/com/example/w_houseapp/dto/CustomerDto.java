package com.example.w_houseapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CustomerDto {
    private String name;
    private String phone;
    private boolean active;
}
