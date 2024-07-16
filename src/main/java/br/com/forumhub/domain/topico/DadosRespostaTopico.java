package br.com.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosRespostaTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataDeCriacao,
        String autor,
        String curso
) {
}
