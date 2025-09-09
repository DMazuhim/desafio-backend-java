# API de Gestão de Clientes

API REST para gestão de clientes, desenvolvida em Java como parte de um desafio técnico para uma vaga de desenvolvedor backend.

## Funcionalidades Implementadas
- ✅ Autenticação e Autorização com Spring Security (usuários `admin` e `padrão`).
- ✅ CRUD completo de Clientes.
- ✅ Integração com a API ViaCEP para preenchimento automático de endereço a partir do CEP.
- ✅ Validação de dados de entrada (CPF único e válido, e-mail único, nome com tamanho mínimo, etc.).
- ✅ Tratamento de erros customizado para respostas de API claras e profissionais.

## Tecnologias Utilizadas
- **Backend:** Java 8, Spring Boot 2.7.18, Spring Security, Spring Data JPA
- **Banco de Dados:** PostgreSQL (rodando em Docker)
- **Build:** Maven
- **Outras Ferramentas:** Lombok, Hibernate Validator

## Como Rodar o Projeto Localmente

**Pré-requisitos:**
- Java 8 JDK
- Maven 3.6+
- Docker Desktop

**1. Iniciar o Banco de Dados:**
```bash
docker run --name desafio-postgres -e POSTGRES_USER=seu_usuario -e POSTGRES_PASSWORD=sua_senha -e POSTGRES_DB=clientes_db -p 5432:5432 -d postgres
```

**2. Configuração:**
- Renomeie o arquivo `application.properties.example` para `application.properties`.
- Altere as propriedades `spring.datasource.username` e `spring.datasource.password` para as mesmas credenciais usadas no comando Docker.

**3. Execução:**
```bash
# Compilar o projeto
mvn clean install

# Rodar a aplicação
java -jar target/gestao-clientes-0.0.1-SNAPSHOT.jar
```
A API estará disponível em `http://localhost:8080`.

## Endpoints da API

- `POST /api/clientes`: Cria um novo cliente (requer role `ADMIN`).
- `GET /api/clientes`: Lista todos os clientes (requer role `ADMIN` ou `USER`).
- `GET /api/clientes/{id}`: Busca um cliente por ID (requer role `ADMIN` ou `USER`).
- ... (outros endpoints)

**Credenciais Padrão:**
- **Admin:** `admin` / `1234mudar#`
- **Padrão:** `padrão` / `123seMudar`