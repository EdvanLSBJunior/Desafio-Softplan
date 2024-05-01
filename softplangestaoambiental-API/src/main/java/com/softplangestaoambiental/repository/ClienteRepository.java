package com.softplangestaoambiental.repository;

import com.softplangestaoambiental.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
