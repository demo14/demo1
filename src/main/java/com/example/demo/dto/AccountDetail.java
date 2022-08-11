package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class AccountDetail {

    @ApiModelProperty(value = "Имя")
    private String name;
    @ApiModelProperty(value = "Фамилия")
    private String lastName;
}
