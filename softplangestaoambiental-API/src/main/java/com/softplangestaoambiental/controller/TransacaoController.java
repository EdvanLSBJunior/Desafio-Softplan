package com.softplangestaoambiental.controller;

import com.softplangestaoambiental.services.responses.transacao.RetornoTransacao;
import com.softplangestaoambiental.dtos.TransacaoDTO;
import com.softplangestaoambiental.services.transacao.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Operation(summary = "Envia uma transacao do tipo r= recebivel ou d= debito para o cliente especificado" , method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacao realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "422", description = "Tipo de transacao invalida"),
            @ApiResponse(responseCode = "422", description = "Limite excedido para a transação de debito."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/{clienteId}/transacoes")
    public ResponseEntity<RetornoTransacao> realizarTransacao(
            @PathVariable Long clienteId,
            @RequestBody TransacaoDTO transacaoDTO
            ) {
        RetornoTransacao retornoTransacao = transacaoService.realizarTransacao(clienteId, transacaoDTO);
        return ResponseEntity.ok(retornoTransacao);
    }
}
