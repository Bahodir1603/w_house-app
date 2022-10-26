package com.example.w_houseapp.dto;

import com.example.w_houseapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {

    private String email;
    private String phone;
    private String password;
    private Long roleId;

}
