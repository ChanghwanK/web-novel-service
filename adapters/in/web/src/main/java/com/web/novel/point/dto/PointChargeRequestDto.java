package com.web.novel.point.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PointChargeRequestDto {

    @NotBlank
    String payMethod;

    @Min(value = 5000, message = "충전 금액은 최소 5000원입니다.")
    @NotNull(message = "charge amount required")
    Integer chargeAmount;
}
