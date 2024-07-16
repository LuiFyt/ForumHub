package br.com.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosListarTopico (Long id, String titulo, String mensagem, LocalDateTime dataDeCriacao, Boolean status, String autor, String curso) {
    public DadosListarTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataDeCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
