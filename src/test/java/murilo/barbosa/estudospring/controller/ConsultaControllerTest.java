package murilo.barbosa.estudospring.controller;

import murilo.barbosa.estudospring.domain.consulta.ConsultaService;
import murilo.barbosa.estudospring.domain.consulta.DadosAgendamentoConsulta;
import murilo.barbosa.estudospring.domain.consulta.DadosDetalhamentoConsulta;
import murilo.barbosa.estudospring.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
// Testar somente a controller
@AutoConfigureMockMvc
// Injetar o jacksonTester
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockBean
    // Mocar serviço
    private ConsultaService consultaService;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão invalidas")
    // Não precisa autenticar
    @WithMockUser
    void agendar_cenario1() throws Exception {

        MockHttpServletResponse response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão validas")
    // Não precisa autenticar
    @WithMockUser
    void agendar_cenario2() throws Exception {

        LocalDateTime data = LocalDateTime.now().plusHours(1);
        Especialidade especialidade = Especialidade.CARDIOLOGIA;

        DadosAgendamentoConsulta dadosAgendamento = new DadosAgendamentoConsulta(2l, 5l, data, especialidade);
        DadosDetalhamentoConsulta dadosDetalhamento = new DadosDetalhamentoConsulta(null,2l, 5l, data, especialidade);

        when(consultaService.agendar(any())).thenReturn(dadosDetalhamento);

        MockHttpServletResponse response = mvc
                .perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJson.write(dadosAgendamento).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosDetalhamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}