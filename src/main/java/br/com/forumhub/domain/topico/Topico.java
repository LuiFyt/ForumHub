package br.com.forumhub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataDeCriacao;
    private Boolean status;
    private String autor;
    private String curso;

    public Topico(DadosRegistrarTopico dadosRegistrarTopico) {
        this.titulo = dadosRegistrarTopico.titulo();
        this.mensagem = dadosRegistrarTopico.mensagem();
        this.autor = dadosRegistrarTopico.autor();
        this.curso = dadosRegistrarTopico.curso();
        this.dataDeCriacao = LocalDateTime.now();
        this.status = true;
    }

    public void desativarTopico() {
        this.status = false;
    }

    public void atualizarDados(DadosAtualizarTopico dadosAtualizarTopico) {
        if (dadosAtualizarTopico.titulo() != null) {
            this.titulo = dadosAtualizarTopico.titulo();
        }
        if (dadosAtualizarTopico.mensagem() != null) {
            this.mensagem = dadosAtualizarTopico.mensagem();
        }
        if (dadosAtualizarTopico.autor() != null) {
            this.autor = dadosAtualizarTopico.autor();
        }
        if (dadosAtualizarTopico.curso() != null) {
            this.curso = dadosAtualizarTopico.curso();
        }
    }
}
