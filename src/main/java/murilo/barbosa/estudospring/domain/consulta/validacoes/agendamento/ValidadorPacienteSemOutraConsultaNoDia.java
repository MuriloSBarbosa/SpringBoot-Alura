package murilo.barbosa.estudospring.domain.consulta.validacoes.agendamento;

import murilo.barbosa.estudospring.domain.ValidacaoException;
import murilo.barbosa.estudospring.domain.consulta.ConsultaRepository;
import murilo.barbosa.estudospring.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia  implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime primeiroHorario = dados.data().withHour(7);
        LocalDateTime ultimoHorario = dados.data().withHour(18);
        Boolean pacientePossuiConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiConsultaNoDia) {
            throw new ValidacaoException("O paciente já possui consulta nesse dia");
        }
    }
}
