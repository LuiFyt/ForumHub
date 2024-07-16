package br.com.forumhub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTopico(@NotNull Long id, String titulo, String mensagem, String autor, String curso) {
}
