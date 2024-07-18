package com.tratamento_de_excel.api.controller.excel;

import com.tratamento_de_excel.api.controller.excel.request.ExcelRequest;
import com.tratamento_de_excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        service.carregarExcel(String.valueOf(file));
        return new ResponseEntity<>("Arquivo carregado e salvo com sucesso", HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<List<String>> lerExcel(MultipartFile file) throws IOException {
//        service.lerExcel(file);
//    }


    //1:
    // criar endpoint para ler uma planilha utilizando header
    // receber um arquivo excel (multpartfiledata), em que o cabeçalho vai ser todos os campos do objeto cliente
    // no service ler o arquivo excel, validar e salvar o cliente no banco
    // 2:
    // planilha de notas de alunos (Coluna A = nomes; Coluna B = notas)
    // acrescentar uma coluna com a média
    // Focar em olhar documentação
    // https://www.devmedia.com.br/apache-poi-manipulando-documentos-em-java/31778
    // https://www.baeldung.com/spring-multipartfile-to-file
    // https://naveenrk22.medium.com/import-wizard-reading-csvs-and-excel-sheets-in-spring-boot-d509b3d5cc1e
    // https://github.com/bezkoder/spring-boot-upload-excel-files/tree/master/src/main

}