package com.example.w_houseapp.dto;

import com.example.w_houseapp.entity.enums.PositionType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class UserDto {

    private String state;
    private Long roleId;
    private String fullName;
    private String phone;
    private String email;
    private Double salary;
    private Long warehouseId;
    private String password;
    private String positionType;
}
