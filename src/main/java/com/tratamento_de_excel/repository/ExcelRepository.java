package com.tratamento_de_excel.repository;

import com.tratamento_de_excel.domain.entity.Excel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelRepository extends JpaRepository<Excel,Long> {

}