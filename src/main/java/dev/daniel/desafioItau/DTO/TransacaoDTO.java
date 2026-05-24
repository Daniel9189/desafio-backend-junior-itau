package dev.daniel.desafioItau.DTO;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
public class TransacaoDTO {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
