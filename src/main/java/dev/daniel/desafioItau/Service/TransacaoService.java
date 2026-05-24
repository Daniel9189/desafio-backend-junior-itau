package dev.daniel.desafioItau.Service;

import dev.daniel.desafioItau.DTO.TransacaoDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Service
public class TransacaoService {

    public void validarTransacao(TransacaoDTO transacaoDTO) {


        if (transacaoDTO.getValor().compareTo(BigDecimal.ZERO) < 0)  {
            throw new IllegalArgumentException("Erro: transação inválida! O valor deve ser maior ou igual a 0.");
        }
        if (transacaoDTO.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Erro: transação inválida! Não é possível registrar data/hora futura.");
        }
        if (Objects.isNull(transacaoDTO.getValor()) || Objects.isNull(transacaoDTO.getDataHora())) {
            throw new IllegalArgumentException("Erro: transação inválida! Todos os campos devem ser preenchidos.");
        }
    }
}
