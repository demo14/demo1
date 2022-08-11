package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Balance {

    @ApiModelProperty(value = "Сумма")
    private BigDecimal amount;
    @ApiModelProperty(value = "Номер Счета")
    private Integer accountNumber;
}
