package murilo.barbosa.estudospring.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import murilo.barbosa.estudospring.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long id,
        Long idMedico,
        // Apelidos para o campo idPaciente
        @JsonAlias({"produto_id", "id_produto"})
        Long idPaciente,
        // Formatar em uma data específica
        // @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        Especialidade especialidade
) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(),consulta.getMedico().getId(), consulta.getPaciente().getId(),consulta.getData(),consulta.getMedico().getEspecialidade());
    }
}
