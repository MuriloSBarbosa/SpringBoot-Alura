package murilo.barbosa.estudospring.domain.consulta.validacoes.agendamento;

import murilo.barbosa.estudospring.domain.ValidacaoException;
import murilo.barbosa.estudospring.domain.consulta.DadosAgendamentoConsulta;
import murilo.barbosa.estudospring.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        Boolean medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if(!medicoAtivo){
            throw new ValidacaoException("A consulta só pode ser realizada com um médico ativo");
        }
    }
}
