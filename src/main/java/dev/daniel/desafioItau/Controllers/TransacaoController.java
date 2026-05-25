package dev.daniel.desafioItau.Controllers;

import dev.daniel.desafioItau.DTO.EstatisticaDTO;
import dev.daniel.desafioItau.Repositories.TransacaoRepository;
import dev.daniel.desafioItau.Services.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.daniel.desafioItau.DTO.TransacaoDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity adicionar(@RequestBody TransacaoDTO transacaoDTO) {

        try {
            transacaoService.validarTransacao(transacaoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity deletar() {
        transacaoService.deletarTransacao();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity verEstatisticas() {
        EstatisticaDTO estatisticas = transacaoService.calcularEstatistica();
        return ResponseEntity.status(HttpStatus.OK).body(estatisticas);
    }
}
