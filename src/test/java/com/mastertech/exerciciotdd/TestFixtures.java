package com.mastertech.exerciciotdd;

public class TestFixtures {
    public static Cliente getCliente(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("João Silva");
        cliente.setCpf("94856734423");
        cliente.setTelefone("11 91234 4321");
        cliente.setEmail("cliente@aol.com.br");

        return cliente;
    }
}
