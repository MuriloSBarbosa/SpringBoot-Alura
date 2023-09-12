package murilo.barbosa.estudospring.controller;

import jakarta.validation.Valid;
import murilo.barbosa.estudospring.domain.usuario.DadosAutenticacao;
import murilo.barbosa.estudospring.domain.usuario.Usuario;
import murilo.barbosa.estudospring.infra.security.DadosTokenJwt;
import murilo.barbosa.estudospring.infra.security.TokenService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        // UserName.. é como se fosse um dto do spring do nosso dto
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());

        // Representa o usuário autenticado, mas retorna um Object
        Authentication authentication = authenticationManager.authenticate(token);
        String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.status(200).body(new DadosTokenJwt(tokenJWT));
    }
}
