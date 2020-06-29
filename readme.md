# Sistema de Cadastro de Clientes

## Endpoints

### Criar Cliente

**URL:** POST /

**Request:**

```json
{
  "nome": "Teste",
  "cpf": "12312312312"
  "telefone": "11 12344321",
  "email": "teste@teste.com"
}
```

**Respostas possíveis:**

- 201 Created
```json
{
  "id": 1,
  "nome": "Teste",
  "cpf": "12312312312"
  "telefone": "11 12344321",
  "email": "teste@teste.com"
}
```
 
- 422 Unprocessable Entity

### Consultar Cliente

**URL:** GET /{id}

**Respostas possíveis:**

- 200 Ok
```json
{
  "id": 1,
  "nome": "Teste",
  "cpf": "12312312312"
  "telefone": "11 12344321",
  "email": "teste@teste.com"
}
```
 
- 404 Not Found

### Atualizar Dados do Cliente

**URL:** PATCH /{id}

**Request:**

Apenas o telefone e o email podem ser atualizados.

```json
{
  "telefone": "11 12344321",
  "email": "teste@teste.com"
}
```

**Respostas possíveis:**

- 200 Ok
```json
{
  "id": 1,
  "nome": "Teste",
  "cpf": "12312312312"
  "telefone": "11 12344321",
  "email": "teste@teste.com"
}
```
 
- 404 Not Found