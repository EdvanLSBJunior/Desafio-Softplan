package com.softplangestaoambiental.controller;

import com.softplangestaoambiental.services.extrato.ExtratoService;
import com.softplangestaoambiental.services.responses.extrato.RetornoExtrato;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Extrato")
@CrossOrigin("*")
public class ExtratoController {

    @Autowired
    private ExtratoService extratoService;

    @Operation(summary = "Solicita o extrato com o historico de transacoes do cliente" , method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extrato do cliente retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{clienteId}/extrato")
    public ResponseEntity<RetornoExtrato> obterExtratoCliente(@PathVariable Long clienteId) {
        RetornoExtrato retornoExtrato = extratoService.obterExtratoCliente(clienteId);
        return ResponseEntity.ok(retornoExtrato);
    }
}
