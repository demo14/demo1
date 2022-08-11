package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferBetweenAccounts {

    @ApiModelProperty(value = "Номер счета с которого необходимо перевести средства")
    private Integer accountFrom;
    @ApiModelProperty(value = "Номер счета на который необходимо перевести средства")
    private Integer accountTo;
    @ApiModelProperty(value = "Сумма перевода")
    private BigDecimal amount;
}
