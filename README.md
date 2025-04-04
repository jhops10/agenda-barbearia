# API de Agendamento para Barbeiros

API que permite o gerenciamento de agendamentos entre barbeiros e clientes, oferecendo funcionalidades como cadastro de barbeiros, clientes, serviços e agendamentos.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## Como Executar o Projeto

### Configuração do Banco de Dados
Antes de rodar a aplicação, configure o **application.properties**:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```


A aplicação estará disponível em `http://localhost:8080`

## Endpoints Principais

### Barbeiros
- **Criar um barbeiro:** `POST /api/barbeiros`
- **Listar todos:** `GET /api/barbeiros`
- **Buscar por ID:** `GET /api/barbeiros/{id}`
- **Atualizar:** `PUT /api/barbeiros/{id}`
- **Deletar:** `DELETE /api/barbeiros/{id}`

### Serviços
- **Criar um serviço:** `POST /api/servicos`
- **Listar todos:** `GET /api/servicos`
- **Buscar por ID:** `GET /api/servicos/{id}`
- **Atualizar:** `PUT /api/servicos/{id}`
- **Deletar:** `DELETE /api/servicos/{id}`

### Agendamentos
- **Criar um agendamento:** `POST /api/agendamentos`
- **Listar todos:** `GET /api/agendamentos`
- **Buscar por ID:** `GET /api/agendamentos/{id}`
- **Buscar por barbeiro:** `GET /api/agendamentos/barbeiro/{idBarbeiro}`
- **Buscar por cliente:** `GET /api/agendamentos/cliente/{idCliente}`
- **Deletar:** `DELETE /api/agendamentos/{id}`


