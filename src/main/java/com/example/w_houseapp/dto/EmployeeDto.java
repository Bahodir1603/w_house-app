package com.example.w_houseapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class EmployeeDto {
    @NotNull(message = "Bo'sh  bo'lishi mumkin emas!")
    private String fullName;
    @NotNull(message = "Bo'sh  bo'lishi mumkin emas!")
    private Integer age;
    @NotNull(message = "Bo'sh  bo'lishi mumkin emas!")
    private Double salary;
    @NotNull(message = "Bo'sh  bo'lishi mumkin emas!")
    private String phone;
    @NotNull(message = "Bo'sh  bo'lishi mumkin emas!")
    private Long warehouseId;

}
