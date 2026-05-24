package dev.daniel.desafioItau.DTO;

import lombok.Getter;

@Getter
public class TransacaoDTO {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
