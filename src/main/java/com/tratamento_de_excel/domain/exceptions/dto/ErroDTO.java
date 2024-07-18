package com.tratamento_de_excel.domain.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErroDTO{

    private final String message;

}