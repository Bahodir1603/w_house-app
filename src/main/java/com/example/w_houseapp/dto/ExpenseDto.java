package com.example.w_houseapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ExpenseDto {

    private String description;
    @NotNull(message = "Supplier tanlash shart")
    private String supplierId;
    @NotNull(message = "QAysi Warehousga ekanligi tanlanmadi")
    private Long warehouseId;
    @NotNull(message = "ExpenseType bo'sh bolishi mumkin emas")
    private String expenseType;
    @NotNull(message = "Amountni kiritilmadi")
    private Double amount;
    @NotNull(message = "To'lov turi aytilmadi")
    private String payType;
}
