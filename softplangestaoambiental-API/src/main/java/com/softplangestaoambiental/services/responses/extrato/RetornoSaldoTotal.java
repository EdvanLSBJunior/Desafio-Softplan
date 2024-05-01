package com.softplangestaoambiental.services.responses.extrato;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RetornoSaldoTotal {

    private int total;
    private LocalDateTime data_extrato;
    private int limite;
}
