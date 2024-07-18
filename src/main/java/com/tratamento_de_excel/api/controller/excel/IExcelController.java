package com.tratamento_de_excel.api.controller.excel;

import com.tratamento_de_excel.api.controller.excel.request.ExcelRequest;
import com.tratamento_de_excel.api.controller.excel.response.ExcelResponse;
import com.tratamento_de_excel.domain.exceptions.dto.ErroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(name = "Excel")
@RequestMapping(value = "/excel")
@Validated
public interface IExcelController {

    @Operation(
            summary = "Criar Excel",
            description = "Endpoint responsável por salvar um novo excel",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Excel criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = ExcelResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
                            content = @Content(schema = @Schema(implementation = ErroDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErroDTO.class)))
            })
    @PostMapping
    @ResponseStatus(CREATED)
    ResponseEntity<String> gerarExcel(@RequestBody ExcelRequest request);

    @Operation(
            summary = "Ler Excel",
            description = "Endpoint responsável por ler um novo excel",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Excel lido com sucesso.",
                            content = @Content(schema = @Schema(implementation = ExcelResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
                            content = @Content(schema = @Schema(implementation = ErroDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErroDTO.class)))
            })
    @GetMapping("/upload/ler-excel")
    @ResponseStatus(OK)
    ResponseEntity<List<String>> lerExcel(@RequestParam("file") String file) throws IOException;

    @Operation(
            summary = "Carregar Excel",
            description = "Endpoint responsável por carregar um novo excel",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Excel carregado com sucesso.",
                            content = @Content(schema = @Schema(implementation = ExcelResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
                            content = @Content(schema = @Schema(implementation = ErroDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErroDTO.class)))
            })
    @GetMapping("/upload")
    @ResponseStatus(OK)
    ResponseEntity<String> carregarExcel(@RequestParam("file") MultipartFile file);
    }


