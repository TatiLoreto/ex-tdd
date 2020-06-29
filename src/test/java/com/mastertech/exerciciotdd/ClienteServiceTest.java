package com.mastertech.exerciciotdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mastertech.exerciciotdd.TestFixtures.getCliente;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {
    ClienteRepository repository;
    ClienteService service;

    @BeforeEach
    public void preparar(){
        repository = mock(ClienteRepository.class);
        service = new ClienteService(repository);
    }

    @Test
    public void deveCriarUmCliente(){
        Cliente cliente = getCliente();

        when(repository.save(cliente)).thenReturn(cliente);

        Cliente novoCliente = service.criar(cliente);

        assertEquals(cliente.getNome(), novoCliente.getNome());
        assertEquals(cliente.getNome(), novoCliente.getNome());
        assertEquals(cliente.getNome(), novoCliente.getNome());
        assertEquals(cliente.getNome(), novoCliente.getNome());
    }

    @Test
    public void deveAtualizarUmCliente(){
        Long id = 1L;
        Cliente payload = new Cliente();
        payload.setTelefone("11 91234 4321");
        payload.setEmail("cliente@aol.com.br");

        Cliente clienteGravado = getCliente();

        Optional<Cliente> optional = Optional.of(clienteGravado);

        when(repository.findById(id)).thenReturn(optional);
        when(repository.save(clienteGravado)).then(invocationOnMock ->
            invocationOnMock.getArgument(0)
        );

        Cliente clienteAtualizado = service.atualizarDados(id, payload);

        //a função verify verifica se de fato houve uma chamada no método passado como parâmetro
        verify(repository).findById(id);
        verify(repository).save(clienteGravado);

        assertEquals(payload.getEmail(), clienteAtualizado.getEmail());
        assertEquals(payload.getTelefone(), clienteAtualizado.getTelefone());
    }

    @Test
    public void deveBuscarUmCliente(){
        Long id = 1L;
        Optional<Cliente> optional = Optional.of(getCliente());

        when(repository.findById(id)).thenReturn(optional);

        Cliente clienteEncontrado = service.buscarPorId(id);

        assertEquals(id, clienteEncontrado.getId());
    }
}
