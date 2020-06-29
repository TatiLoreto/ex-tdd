package com.mastertech.exerciciotdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.mastertech.exerciciotdd.TestFixtures.getCliente;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ITClienteController {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deveCriarUmCliente() throws Throwable {
        String payload = mapper.writeValueAsString(getCliente());

        mockMvc.perform(post("/")
                    .content(payload)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L));
    }

    @Test
    public void deveRetornar422AoCriarClienteComDadosInvalidos() throws Throwable {
        String payload = mapper.writeValueAsString(new Cliente());

        mockMvc.perform(post("/")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void deveBuscarUmCliente() throws Throwable {
        mockMvc.perform(get("/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1L));;
    }

    @Test
    public void deveRetornar404AoBuscarUmClienteInexistente() throws Throwable {
        mockMvc.perform(get("/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveAtualizarUmCliente() throws Throwable {
        Cliente cliente = getCliente();
        cliente.setEmail("novoemail@teste.com");
        cliente.setTelefone("21 1111 2222");

        String payload = mapper.writeValueAsString(cliente);

        mockMvc.perform(patch("/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(cliente.getId()))
                .andExpect(jsonPath("nome").value(cliente.getNome()))
                .andExpect(jsonPath("cpf").value(cliente.getCpf()))
                .andExpect(jsonPath("email").value(cliente.getEmail()))
                .andExpect(jsonPath("telefone").value(cliente.getTelefone()));
    }

    @Test
    public void deveRetornar404AoAtualizarUmClienteInexistente() throws Throwable {
        Cliente cliente = getCliente();
        cliente.setEmail("novoemail@teste.com");
        cliente.setTelefone("21 1111 2222");

        String payload = mapper.writeValueAsString(cliente);

        mockMvc.perform(patch("/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isNotFound());
    }
}
