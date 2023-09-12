package murilo.barbosa.estudospring.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import murilo.barbosa.estudospring.domain.consulta.ConsultaService;
import murilo.barbosa.estudospring.domain.consulta.DadosAgendamentoConsulta;
import murilo.barbosa.estudospring.domain.consulta.DadosCancelamentoConsulta;
import murilo.barbosa.estudospring.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        return ResponseEntity.status(200).body(consultaService.agendar(dados));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        consultaService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
