package com.example.w_houseapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentDto {
    @NotNull(message = "Customerni tanlash shart")
    private String customerId;
    @NotNull(message = "Amountni kiritilmadi")
    private Double amount;
    @NotNull(message = "QAysi Warehousga ekanligi tanlanmadi")
    private Long warehouseId;
    @NotNull(message = "To'lov turi aytilmadi")
    private String payType;
}
