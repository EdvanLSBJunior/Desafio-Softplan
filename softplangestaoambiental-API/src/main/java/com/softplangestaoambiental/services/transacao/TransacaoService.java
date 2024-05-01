package com.softplangestaoambiental.services.transacao;

import com.softplangestaoambiental.domain.cliente.Cliente;
import com.softplangestaoambiental.services.responses.transacao.RetornoTransacao;
import com.softplangestaoambiental.domain.transacao.Transacao;
import com.softplangestaoambiental.repository.TransacaoRepository;
import com.softplangestaoambiental.services.transacao.strategy.DebitoTransacaoStrategy;
import com.softplangestaoambiental.services.transacao.strategy.RecebivelTransacaoStrategy;
import com.softplangestaoambiental.services.transacao.strategy.TipoTransacaoStrategy;
import com.softplangestaoambiental.dtos.TransacaoDTO;
import com.softplangestaoambiental.exceptions.ClienteNaoEncontradoException;
import com.softplangestaoambiental.exceptions.TransacaoInvalidaException;
import com.softplangestaoambiental.repository.ClienteRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransacaoService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Setter
    private TipoTransacaoStrategy tipoTransacaoStrategy;

    public RetornoTransacao realizarTransacao(Long clienteId, TransacaoDTO transacaoDTO) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(ClienteNaoEncontradoException::new);

        // Configura a estratégia com base no tipo de transação fornecido na requisição
        if ("r".equals(transacaoDTO.tipo())) {
            setTipoTransacaoStrategy(new RecebivelTransacaoStrategy());
        } else if ("d".equals(transacaoDTO.tipo())) {
            setTipoTransacaoStrategy(new DebitoTransacaoStrategy());
        } else {
            throw new TransacaoInvalidaException();
        }
        tipoTransacaoStrategy.executarTransacao(cliente, transacaoDTO.valor());

        Transacao transacao = new Transacao();
        transacao.setClienteId(cliente);
        transacao.setValor(transacaoDTO.valor());
        transacao.setTipo(tipoTransacaoStrategy.determinarTipoTransacao(cliente, transacaoDTO.valor()));
        transacao.setDescricao(transacaoDTO.descricao());
        transacao.setRealizadaEm(LocalDateTime.now());

        transacaoRepository.save(transacao);
        clienteRepository.save(cliente);

        return new RetornoTransacao(cliente.getLimite(), cliente.getSaldoInicial());
    }
}
