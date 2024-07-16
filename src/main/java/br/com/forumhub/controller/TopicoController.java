package br.com.forumhub.controller;

import br.com.forumhub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public Page<DadosListarTopico> obterTopicos(@PageableDefault(sort = {"id"}) Pageable paginacao) {
        return topicoRepository.findByStatusTrue(paginacao).map(DadosListarTopico::new);
    }

    @PostMapping
    public ResponseEntity<DadosRespostaTopico> registrarTopico(@RequestBody @Valid DadosRegistrarTopico dadosRegistrarTopico, UriComponentsBuilder uriComponentsBuilder) {
        var topico = topicoRepository.save(new Topico(dadosRegistrarTopico));
        var dadosRespostaTopico = new DadosRespostaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensagem(), topico.getDataDeCriacao(), topico.getAutor(), topico.getCurso());
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(dadosRespostaTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosRespostaTopico> atualizarTopico(@RequestBody @Valid DadosAtualizarTopico dadosAtualizarTopico) {
        var topico = topicoRepository.getReferenceById(dadosAtualizarTopico.id());
        topico.atualizarDados(dadosAtualizarTopico);
        return ResponseEntity.ok(new DadosRespostaTopico(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getDataDeCriacao(), topico.getAutor(), topico.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        topico.desativarTopico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosRespostaTopico> obterTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        var dadosResposta = new DadosRespostaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensagem(), topico.getDataDeCriacao(), topico.getAutor(), topico.getCurso());
        return ResponseEntity.ok(dadosResposta);
    }
}
