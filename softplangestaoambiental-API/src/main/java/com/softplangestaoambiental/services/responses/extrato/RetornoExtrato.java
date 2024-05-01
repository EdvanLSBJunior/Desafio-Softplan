package com.softplangestaoambiental.services.responses.extrato;

import com.softplangestaoambiental.dtos.ExtratoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoExtrato {

    private RetornoSaldoTotal saldo;
    private List<ExtratoDTO> ultimas_transacoes;
}
