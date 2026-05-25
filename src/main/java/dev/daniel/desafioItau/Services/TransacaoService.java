package dev.daniel.desafioItau.Services;

import dev.daniel.desafioItau.DTO.EstatisticaDTO;
import dev.daniel.desafioItau.DTO.TransacaoDTO;
import dev.daniel.desafioItau.Repositories.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public void validarTransacao(TransacaoDTO transacaoDTO) {

        if (Objects.isNull(transacaoDTO.getValor()) || Objects.isNull(transacaoDTO.getDataHora())) {
            throw new IllegalArgumentException("Erro: transação inválida! Todos os campos devem ser preenchidos.");
        }
        if (transacaoDTO.getValor().compareTo(BigDecimal.ZERO) < 0)  {
            throw new IllegalArgumentException("Erro: transação inválida! O valor deve ser maior ou igual a 0.");
        }
        if (transacaoDTO.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Erro: transação inválida! Não é possível registrar data/hora futura.");
        }
        transacaoRepository.salvarDados(transacaoDTO);
    }

    public void deletarTransacao() {
        transacaoRepository.deletarDados();
    }

    public EstatisticaDTO calcularEstatistica() {

        OffsetDateTime umMinutoAtras = OffsetDateTime.now().minusSeconds(60);

        DoubleSummaryStatistics stats = transacaoRepository.getListaDeTransacoes().stream()
                .filter(transacaoDTO -> transacaoDTO.getDataHora().isAfter(umMinutoAtras))
                .mapToDouble(transacaoDTO -> transacaoDTO.getValor().doubleValue())
                .summaryStatistics();


        if (stats.getCount() == 0) {
            return new EstatisticaDTO(0, 0.0, 0.0, 0.0, 0.0);
        }
        return new EstatisticaDTO(
                stats.getCount(),
                stats.getAverage(),
                stats.getMax(),
                stats.getMin(),
                stats.getSum()
        );
    }
}
