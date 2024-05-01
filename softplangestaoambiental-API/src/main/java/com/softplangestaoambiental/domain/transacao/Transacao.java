package com.softplangestaoambiental.domain.transacao;

import com.softplangestaoambiental.domain.cliente.Cliente;
import com.softplangestaoambiental.domain.transacao.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "transacoes")
@Table(name = "transacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clienteId;
    @Column(name = "valor", nullable = false)
    private int valor;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 10)
    private TipoTransacao tipo;
    @Column(name = "descricao", length = 10)
    private String descricao;
    private LocalDateTime realizadaEm;
}