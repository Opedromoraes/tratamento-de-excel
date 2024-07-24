package com.tratamento_de_excel.api.controller.excel;

import com.tratamento_de_excel.api.controller.excel.request.ExcelRequest;
import com.tratamento_de_excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExcelController implements IExcelController {

    private final ExcelService service;

    @Override
    public ResponseEntity<String> gerarExcel(ExcelRequest request) {
        String response = service.gerarExcel(request);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<List<String>> lerExcel(String file) {
        List<String> response = service.lerExcel(file);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<String> carregarExcel(MultipartFile file) {
        try {
            service.salvarClientesDoExcel(file);
            return ResponseEntity.ok("Planilha carregada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao carregar a planilha: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> editarExcel(MultipartFile file) {
        try {
            service.carregarExcel(file);
            return ResponseEntity.ok("Planilha editada com sucesso!");
        } catch (FileNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado" + e.getMessage());
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na edição do arquivo" + e.getMessage());
        }
    }
}