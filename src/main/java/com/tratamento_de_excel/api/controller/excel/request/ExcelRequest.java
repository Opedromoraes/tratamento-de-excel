package com.tratamento_de_excel.api.controller.excel.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ExcelRequest {

    @Schema(description = "Nomes no excel", example = "['Pedro', 'Guilherme', 'Romulo'] <- aspas duplas")
    private List<String> nomes;

//    private MultipartFile file;
//    private String filtro;
}
