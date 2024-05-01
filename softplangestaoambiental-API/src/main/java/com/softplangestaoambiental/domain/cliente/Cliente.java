package com.softplangestaoambiental.domain.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softplangestaoambiental.domain.transacao.Transacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "cliente")
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int limite;
    private int saldoInicial;
    @JsonIgnore
    @OneToMany(mappedBy = "clienteId", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;
}


