package com.mastertech.exerciciotdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
public class ClienteController {
    private ClienteService service;

    @Autowired
    public ClienteController(ClienteService service){
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@RequestBody Cliente cliente){
        return service.criar(cliente);
    }

    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PatchMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        return service.atualizarDados(id, cliente);
    }

    @ExceptionHandler(ClienteInexistenteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void tratarClienteInexistente(){
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void tratarErroValidacao(){
    }
}
