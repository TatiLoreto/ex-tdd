package com.mastertech.exerciciotdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    public Cliente criar(Cliente cliente){
        cliente.setId(0);//evita que seja feita a atualização um cliente existente
        return repository.save(cliente);
    }

    public Cliente atualizarDados(long id, Cliente clienteAtualizado){
        Cliente cliente = buscarPorId(id);

        //apenas o email e o telefone podem ser alterados
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setTelefone(clienteAtualizado.getTelefone());

        return repository.save(cliente);
    }

    public Cliente buscarPorId(long id){
        Optional<Cliente> optional = repository.findById(id);

        if(optional.isEmpty()){
            throw new ClienteInexistenteException();
        }

        return optional.get();
    }
}
