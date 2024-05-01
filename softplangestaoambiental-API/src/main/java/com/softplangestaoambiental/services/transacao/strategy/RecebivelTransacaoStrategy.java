package com.softplangestaoambiental.services.transacao.strategy;

import com.softplangestaoambiental.domain.cliente.Cliente;
import com.softplangestaoambiental.domain.transacao.enums.TipoTransacao;

public class RecebivelTransacaoStrategy implements TipoTransacaoStrategy {
    @Override
    public TipoTransacao determinarTipoTransacao(Cliente cliente, int valor) {
        return TipoTransacao.RECEBIVEL;
    }

    @Override
    public void executarTransacao(Cliente cliente, int valor) {
        int novoSaldo = cliente.getSaldoInicial() + valor;
        cliente.setSaldoInicial(novoSaldo);
    }
}
