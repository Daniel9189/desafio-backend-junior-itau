package dev.daniel.desafioItau.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.daniel.desafioItau.DTO.TransacaoDTO;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @PostMapping
    public ResponseEntity adicionar(@RequestBody TransacaoDTO transacaoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
