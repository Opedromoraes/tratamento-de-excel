package com.tratamento_de_excel.api.controller.excel.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ExcelRequest {

    @Schema(description = "Nomes no excel", example = "Pedro, Guilherme, Romulo")
    private List<String> nomes;

    @Schema(description = "Nome do arquivo", example = "Nomes")
    private String nome;

//    private MultipartFile file;
//    private String filtro;
}
