package murilo.barbosa.estudospring.domain.medico;

import jakarta.validation.constraints.NotNull;
import murilo.barbosa.estudospring.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
