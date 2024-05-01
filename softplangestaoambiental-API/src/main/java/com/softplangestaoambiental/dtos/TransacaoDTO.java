package com.softplangestaoambiental.dtos;

import java.time.LocalDateTime;

public record TransacaoDTO(Long clienteId, int valor, String tipo, String descricao, LocalDateTime realizadaEm) {

}