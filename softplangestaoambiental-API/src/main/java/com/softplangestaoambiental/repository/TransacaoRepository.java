package com.softplangestaoambiental.repository;

import com.softplangestaoambiental.domain.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    @Query(value = "SELECT * FROM transacoes WHERE cliente_id = ?1 ORDER BY realizada_em DESC LIMIT 10", nativeQuery = true)
    List<Transacao> findTop10ByClienteIdOrderByRealizadaEmDesc(Long clienteId);
}
