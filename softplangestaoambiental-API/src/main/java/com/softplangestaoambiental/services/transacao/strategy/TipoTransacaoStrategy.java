package com.softplangestaoambiental.services.transacao.strategy;

import com.softplangestaoambiental.domain.cliente.Cliente;
import com.softplangestaoambiental.domain.transacao.enums.TipoTransacao;

public interface TipoTransacaoStrategy {
    TipoTransacao determinarTipoTransacao(Cliente cliente, int valor);

    public void executarTransacao(Cliente cliente, int valor);
}
