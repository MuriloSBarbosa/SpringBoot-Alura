package murilo.barbosa.estudospring.domain.consulta.validacoes.agendamento;

import murilo.barbosa.estudospring.domain.ValidacaoException;
import murilo.barbosa.estudospring.domain.consulta.ConsultaRepository;
import murilo.barbosa.estudospring.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        Boolean medicoPossuiConsultaHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());

        if(medicoPossuiConsultaHorario){
            throw new ValidacaoException("O médico já possui uma consulta nesse mesmo horário");
        }
    }
}
