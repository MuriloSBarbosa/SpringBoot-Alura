package murilo.barbosa.estudospring.domain.consulta.validacoes.agendamento;

import murilo.barbosa.estudospring.domain.ValidacaoException;
import murilo.barbosa.estudospring.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {
    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();

        Boolean isDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        Boolean depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if(isDomingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário do funcionamento da clínica!");
        }
    }
}
