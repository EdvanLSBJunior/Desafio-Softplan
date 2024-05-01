package com.softplangestaoambiental.services.extrato;

import com.softplangestaoambiental.domain.cliente.Cliente;
import com.softplangestaoambiental.domain.transacao.Transacao;
import com.softplangestaoambiental.domain.transacao.enums.TipoTransacao;
import com.softplangestaoambiental.dtos.ExtratoDTO;
import com.softplangestaoambiental.exceptions.ClienteNaoEncontradoException;
import com.softplangestaoambiental.repository.ClienteRepository;
import com.softplangestaoambiental.repository.TransacaoRepository;
import com.softplangestaoambiental.services.responses.extrato.RetornoExtrato;
import com.softplangestaoambiental.services.responses.extrato.RetornoSaldoTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtratoService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

public RetornoExtrato obterExtratoCliente(Long clienteId) {
    Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(ClienteNaoEncontradoException::new);

    List<Transacao> transacoes = transacaoRepository.findTop10ByClienteIdOrderByRealizadaEmDesc(clienteId);

    int saldoTotal = calcularSaldoTotal(cliente, transacoes);

    RetornoSaldoTotal saldo = new RetornoSaldoTotal(saldoTotal, LocalDateTime.now(), cliente.getLimite());
    List<ExtratoDTO> ultimasTransacoes = transacoes.stream()
            .map(transacao -> new ExtratoDTO(
                    transacao.getValor(),
                    transacao.getTipo().name(),
                    transacao.getDescricao(),
                    transacao.getRealizadaEm()
            ))
            .collect(Collectors.toList());

    return new RetornoExtrato(saldo, ultimasTransacoes);
}

    public int calcularSaldoTotal(Cliente cliente, List<Transacao> transacoes) {
        int saldoTotal = cliente.getSaldoInicial();
        for (Transacao transacao : transacoes) {
            if (transacao.getTipo() == TipoTransacao.RECEBIVEL) {
                saldoTotal += transacao.getValor();
            } else {
                saldoTotal -= transacao.getValor();
            }
        }
        return saldoTotal;
    }
}
