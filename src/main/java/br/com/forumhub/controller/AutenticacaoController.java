package br.com.forumhub.controller;

import br.com.forumhub.domain.usuario.DadosAutenticacaoUsuario;
import br.com.forumhub.domain.usuario.Usuario;
import br.com.forumhub.infra.DadosTokenJWT;
import br.com.forumhub.infra.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DadosAutenticacaoUsuario dadosAutenticacaoUsuario) {
        var tokenDeAutenticacao = new UsernamePasswordAuthenticationToken(dadosAutenticacaoUsuario.login(), dadosAutenticacaoUsuario.senha());
        var autenticacao =  authenticationManager.authenticate(tokenDeAutenticacao);
        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
