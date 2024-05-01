package com.softplangestaoambiental.services.transacao.strategy;

import com.softplangestaoambiental.domain.cliente.Cliente;
import com.softplangestaoambiental.domain.transacao.enums.TipoTransacao;
import com.softplangestaoambiental.exceptions.LimiteExcedidoException;

public class DebitoTransacaoStrategy implements TipoTransacaoStrategy {
    @Override
    public TipoTransacao determinarTipoTransacao(Cliente cliente, int valor) {
        return TipoTransacao.DEBITO;
    }

    @Override
    public void executarTransacao(Cliente cliente, int valor) {
        int novoSaldo = cliente.getSaldoInicial() - valor;
        int limiteNegativo = cliente.getLimite() * -1;

        if (novoSaldo < limiteNegativo) {
            throw new LimiteExcedidoException();
        }
        cliente.setSaldoInicial(novoSaldo);
    }
}
