package com.softplangestaoambiental.services.responses.transacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RetornoTransacao {
    private int limite;
    private int saldo;
}
