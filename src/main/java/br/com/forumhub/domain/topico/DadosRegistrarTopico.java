package br.com.forumhub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosRegistrarTopico (
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
