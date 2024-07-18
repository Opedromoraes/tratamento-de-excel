package com.tratamento_de_excel.repository;

import com.tratamento_de_excel.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
