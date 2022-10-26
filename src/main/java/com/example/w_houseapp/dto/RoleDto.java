package com.example.w_houseapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RoleDto {
    private String name;
    private boolean active;
}
