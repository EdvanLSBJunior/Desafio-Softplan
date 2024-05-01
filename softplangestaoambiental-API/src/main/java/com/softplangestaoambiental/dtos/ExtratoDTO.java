package com.softplangestaoambiental.dtos;

import java.time.LocalDateTime;

public record ExtratoDTO(int valor, String tipo, String descricao, LocalDateTime realizadaEm) {

}
