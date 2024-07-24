package com.tratamento_de_excel.service;

import com.tratamento_de_excel.api.controller.excel.request.ExcelRequest;
import com.tratamento_de_excel.domain.entity.Cliente;
import com.tratamento_de_excel.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelService {

    private static final String fileName = "C:/testePlanilha/novoExcel.xlsx";
    private final ClienteRepository repository;
    private static final String editarExcel = "C:/testePlanilha/editarExcel";

//    private MockMvc mockMvc;

    public String gerarExcel(ExcelRequest request) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Nomes");

        int numeroDaLinha = 0;
        for (String nome : request.getNomes()) {
            Row row = sheet.createRow(numeroDaLinha++);
            row.createCell(0).setCellValue(nome);
        }

        try {
            FileOutputStream out = new FileOutputStream(fileName);
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException ex) {
            log.error("Arquivo não encontrado!" + ex.getMessage());
        } catch (IOException ex) {
            log.error("Erro na edição do arquivo!" + ex.getMessage());
        }

        return "Planilha criada com sucesso: " + fileName;
    }

    public List<String> lerExcel(String file) {
        List<String> list = new ArrayList<>();
        try {
            Workbook workbook = getWorkbookFromFile(file);
            Sheet sheet = workbook.getSheetAt(0);
            //Criando objeto do tipo Sheet que vai representar o workbook

            for (Row row : sheet) {
                // Para cada linha dentro do excel faça:
                for (Cell cell : row) {
                    // Para cada célula dentro da linha faça:
                    list.add(cell.toString());
                    // Passando a célula pra String e adicionando ela na lista
                }
            }

            workbook.close();
            // fechando o arquivo.

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void salvarClientesDoExcel(MultipartFile file) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                Cliente cliente = new Cliente();
                cliente.setCpf(row.getCell(0).getStringCellValue());
                cliente.setEmail(row.getCell(1).getStringCellValue());
                cliente.setNome(row.getCell(2).getStringCellValue());

                if (cliente.getCpf().isEmpty()) break;

                clientes.add(cliente);

            }
            repository.saveAll(clientes);
        }
    }

    public String carregarExcel(MultipartFile file) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    row.createCell(5).setCellValue("media");
                    continue;
                }
                Cliente cliente = new Cliente();
                cliente.setNome(row.getCell(0).getStringCellValue());
                cliente.setCpf(row.getCell(1).getStringCellValue());
                cliente.setEmail(row.getCell(2).getStringCellValue());
                cliente.setNota1(row.getCell(3).getStringCellValue());
                cliente.setNota2(row.getCell(4).getStringCellValue());

                if (cliente.getNome().isEmpty()) break;

                String media = calcularMedia(cliente.getNota1(), cliente.getNota2());
                cliente.setMedia(media);

                row.createCell(5).setCellValue(media);

                clientes.add(cliente);

            }
            repository.saveAll(clientes);

            FileOutputStream out = new FileOutputStream(editarExcel.concat(".xlsx"));
            workbook.write(out);
            out.close();

            return "Planilha carregada com sucesso";
        }

    }

    public String calcularMedia(String nota1, String nota2) {
        double notaDouble1 = Double.parseDouble(nota1);
        double notaDouble2 = Double.parseDouble(nota2);
        Double mediaDouble = (notaDouble1 + notaDouble2) / 2;
        return mediaDouble.toString();
    }

    public Workbook getWorkbookFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return WorkbookFactory.create(fileInputStream);
            // estou passando o caminho do arquivo por uma String (o caminho que vou colocar no swagger)
            // Criando um objeto do tipo File, que vai representar esse caminho
            // FileInputStream vai ler o arquivo de acordo com o caminho que passei
            // Cria um objeto Woorkbook(um arquivo) através do FileInputStream para retornar ele no método "lerExcel"
        }
    }
}
