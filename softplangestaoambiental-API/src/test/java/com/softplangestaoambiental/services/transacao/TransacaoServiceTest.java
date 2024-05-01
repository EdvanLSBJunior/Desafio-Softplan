package com.softplangestaoambiental.services.transacao;

import com.softplangestaoambiental.domain.cliente.Cliente;
import com.softplangestaoambiental.dtos.TransacaoDTO;
import com.softplangestaoambiental.exceptions.ClienteNaoEncontradoException;
import com.softplangestaoambiental.exceptions.LimiteExcedidoException;
import com.softplangestaoambiental.exceptions.TransacaoInvalidaException;
import com.softplangestaoambiental.repository.ClienteRepository;
import com.softplangestaoambiental.repository.TransacaoRepository;
import com.softplangestaoambiental.services.responses.transacao.RetornoTransacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransacaoServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRealizarTransacaoRecebivelComSucesso() {
        Long clienteId = 1L;
        int valor = 100;
        String tipo = "r";
        String descricao = "descricao";
        LocalDateTime realizada_em = LocalDateTime.now();
        TransacaoDTO transacaoDTO = new TransacaoDTO(clienteId, valor, tipo, descricao, realizada_em);

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setSaldoInicial(100);
        cliente.setLimite(1000);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        RetornoTransacao retornoTransacao = transacaoService.realizarTransacao(clienteId, transacaoDTO);

        assertNotNull(retornoTransacao);
        assertEquals(cliente.getLimite(), retornoTransacao.getLimite());
        assertEquals(retornoTransacao.getSaldo(), 200);
    }

    @Test
    public void testRealizarTransacaoDebitoComSucesso() {
        Long clienteId = 1L;
        int valor = 100;
        String tipo = "d";
        String descricao = "descricao";
        LocalDateTime realizada_em = LocalDateTime.now();
        TransacaoDTO transacaoDTO = new TransacaoDTO(clienteId, valor, tipo, descricao, realizada_em);

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setSaldoInicial(0);
        cliente.setLimite(1000);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        RetornoTransacao retornoTransacao = transacaoService.realizarTransacao(clienteId, transacaoDTO);

        assertNotNull(retornoTransacao);
        assertEquals(cliente.getLimite(), retornoTransacao.getLimite());
        assertEquals(retornoTransacao.getSaldo(), -100);
    }

    @Test
    public void testRealizarTransacaoComClienteNaoEncontradoException() {
        Long clienteId = 6L;
        int valor = 100;
        String tipo = "r";
        String descricao = "descricao";
        LocalDateTime realizada_em = LocalDateTime.now();
        TransacaoDTO transacaoDTO = new TransacaoDTO(clienteId, valor, tipo, descricao, realizada_em);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        assertThrows(ClienteNaoEncontradoException.class, () -> {
            transacaoService.realizarTransacao(clienteId, transacaoDTO);
        });
    }

    @Test
    public void testRealizarTransacaoComTransacaoInvalidaException() {
        Long clienteId = 1L;
        int valor = 100;
        String tipo = "x";
        String descricao = "descricao";
        LocalDateTime realizada_em = LocalDateTime.now();
        TransacaoDTO transacaoDTO = new TransacaoDTO(clienteId, valor, tipo, descricao, realizada_em);

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setSaldoInicial(0);
        cliente.setLimite(1000);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        assertThrows(TransacaoInvalidaException.class, () -> {
            transacaoService.realizarTransacao(clienteId, transacaoDTO);
        });
    }

    @Test
    public void testRealizarTransacaoComLimiteExcedidoException() {
        Long clienteId = 1L;
        int valor = 1000;
        String tipo = "d";
        String descricao = "descricao";
        LocalDateTime realizada_em = LocalDateTime.now();
        TransacaoDTO transacaoDTO = new TransacaoDTO(clienteId, valor, tipo,descricao, realizada_em);

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setSaldoInicial(500);
        cliente.setLimite(100);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        assertThrows(LimiteExcedidoException.class, () -> {
            transacaoService.realizarTransacao(clienteId, transacaoDTO);
        });
    }
}
