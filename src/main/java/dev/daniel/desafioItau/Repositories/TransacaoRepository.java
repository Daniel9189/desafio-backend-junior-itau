package dev.daniel.desafioItau.Repositories;

import dev.daniel.desafioItau.DTO.TransacaoDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class TransacaoRepository {

    List<TransacaoDTO> listaDeTransacoes = new CopyOnWriteArrayList<>();

    public List<TransacaoDTO> getListaDeTransacoes() {
        return listaDeTransacoes;
    }

    public void salvarDados(TransacaoDTO transacaoDTO) {
        listaDeTransacoes.add(transacaoDTO);
    }

    @Scheduled(fixedRate = 30000)
    public void limparTransacoesAntigas() {

        OffsetDateTime umMinutoAtras = OffsetDateTime.now().minusSeconds(60);

        listaDeTransacoes.removeIf(transacaoDTO -> transacaoDTO.getDataHora().isBefore(umMinutoAtras));
    }

    public void deletarDados() {
        listaDeTransacoes.clear();
    }
}
