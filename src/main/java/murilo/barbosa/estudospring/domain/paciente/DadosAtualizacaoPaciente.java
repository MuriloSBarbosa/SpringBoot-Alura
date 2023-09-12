package murilo.barbosa.estudospring.domain.paciente;

import jakarta.validation.Valid;
import murilo.barbosa.estudospring.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco
) {
}
