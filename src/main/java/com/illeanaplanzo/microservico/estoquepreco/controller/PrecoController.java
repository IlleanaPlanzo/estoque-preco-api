package com.illeanaplanzo.microservico.estoquepreco.controller;

import com.illeanaplanzo.microservico.estoquepreco.constantes.RabbitMqConstantes;
import com.illeanaplanzo.microservico.estoquepreco.dto.PrecoDto;
import com.illeanaplanzo.microservico.estoquepreco.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "preco")
public class PrecoController {

    public static final Logger log = LoggerFactory.getLogger(PrecoController.class);

    @Autowired
    private RabbitMqService rabbitMqService;

    @PutMapping
    private ResponseEntity alteraPreco(@RequestBody PrecoDto precoDto){
        log.info("enviando mensagem de alteracao de preco, codigo do produto {}", precoDto.codigoProduto);

        this.rabbitMqService.enviaMensagem(RabbitMqConstantes.FILA_PRECO, precoDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
