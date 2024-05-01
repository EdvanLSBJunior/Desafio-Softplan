package com.softplangestaoambiental.services.extrato;

import com.softplangestaoambiental.domain.cliente.Cliente;
import com.softplangestaoambiental.domain.transacao.Transacao;
import com.softplangestaoambiental.domain.transacao.enums.TipoTransacao;
import com.softplangestaoambiental.exceptions.ClienteNaoEncontradoException;
import com.softplangestaoambiental.repository.ClienteRepository;
import com.softplangestaoambiental.repository.TransacaoRepository;
import com.softplangestaoambiental.services.responses.extrato.RetornoExtrato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ExtratoServiceTest {
    @InjectMocks
    private ExtratoService extratoService;
    private Cliente cliente;
    @Mock
    private ClienteRepository clienteRepository;
    private List<Transacao> transacoes;
    @Mock
    private TransacaoRepository transacaoRepository;

    @BeforeEach
    public void setup() {
        cliente = new Cliente();
        transacoes = new ArrayList<>();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalcularSaldoTotalComTransacoesRecebiveis() {
        Transacao transacao1 = new Transacao();
        transacao1.setTipo(TipoTransacao.RECEBIVEL);
        transacao1.setValor(100);

        Transacao transacao2 = new Transacao();
        transacao2.setTipo(TipoTransacao.RECEBIVEL);
        transacao2.setValor(200);

        transacoes.add(transacao1);
        transacoes.add(transacao2);

        int saldoTotal = extratoService.calcularSaldoTotal(cliente, transacoes);

        assertEquals(cliente.getSaldoInicial() + 100 + 200, saldoTotal);
    }

    @Test
    public void testCalcularSaldoTotalComTransacoesNaoRecebiveis() {
        Transacao transacao1 = new Transacao();
        transacao1.setTipo(TipoTransacao.DEBITO);
        transacao1.setValor(50);

        Transacao transacao2 = new Transacao();
        transacao2.setTipo(TipoTransacao.DEBITO);
        transacao2.setValor(75);

        transacoes.add(transacao1);
        transacoes.add(transacao2);

        int saldoTotal = extratoService.calcularSaldoTotal(cliente, transacoes);

        assertEquals(cliente.getSaldoInicial() - 50 - 75, saldoTotal);
    }

    @Test
    public void testObterExtratoClienteComSucesso() {
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        cliente.setLimite(1000);
        List<Transacao> transacoes = criarTransacoesDeExemplo();

        when(clienteRepository.findById(clienteId)).thenReturn(java.util.Optional.of(cliente));
        when(transacaoRepository.findTop10ByClienteIdOrderByRealizadaEmDesc(clienteId)).thenReturn(transacoes);

        RetornoExtrato retornoExtrato = extratoService.obterExtratoCliente(clienteId);

        assertNotNull(retornoExtrato);
        assertNotNull(retornoExtrato.getSaldo());
        assertEquals(cliente.getLimite(), retornoExtrato.getSaldo().getLimite());
        assertEquals(transacoes.size(), retornoExtrato.getUltimas_transacoes().size());
    }

    @Test
    public void testObterExtratoClienteComClienteNaoEncontradoException() {
        Long clienteId = 1L;

        when(clienteRepository.findById(clienteId)).thenReturn(java.util.Optional.empty());

        assertThrows(ClienteNaoEncontradoException.class, () -> {
            extratoService.obterExtratoCliente(clienteId);
        });
    }

    private List<Transacao> criarTransacoesDeExemplo() {
        List<Transacao> transacoes = new ArrayList<>();

        Transacao transacao1 = new Transacao();
        transacao1.setTipo(TipoTransacao.RECEBIVEL);
        transacao1.setValor(100);

        Transacao transacao2 = new Transacao();
        transacao2.setTipo(TipoTransacao.DEBITO);
        transacao2.setValor(75);

        return transacoes;
    }
}

