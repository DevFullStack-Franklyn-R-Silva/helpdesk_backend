# Helpdesk Backend

Este repositório contém o backend do sistema de Helpdesk, implementado em **Java 17** com **Spring Boot**. A seguir, a documentação das dependências e classes já implementadas.

---

## 🚀 Tecnologias e Dependências

No arquivo `pom.xml`, foram adicionadas as seguintes dependências:

* **Spring Boot Starter Web** (`spring-boot-starter-web`)

  * Suporte a criação de APIs REST e servidor embutido Tomcat.

* **Spring Boot Starter Data JPA** (`spring-boot-starter-data-jpa`)

  * Integração com JPA/Hibernate para mapeamento objeto-relacional.

* **Spring Boot Starter Security** (`spring-boot-starter-security`)

  * Configuração de autenticação e autorização.

* **Spring Boot DevTools** (`spring-boot-devtools`)

  * Hot reload para acelerar o desenvolvimento.

* **Lombok** (`org.projectlombok:lombok`)

  * Redução de código boilerplate em entidades e DTOs.

* **Oracle JDBC Driver** (`com.oracle.database.jdbc:ojdbc8`)

  * Conexão com o banco de dados Oracle.

* **JJWT** (`io.jsonwebtoken:jjwt`)

  * Geração e validação de tokens JWT (HS256).

* **Spring Boot Starter Validation** (`spring-boot-starter-validation`)

  * Validação de dados via Bean Validation (JSR-380).
    *(Adicionada caso queira usar anotações como `@Valid`, `@NotNull`.)*

* **Spring Boot Starter Actuator** (`spring-boot-starter-actuator`)

  * Endpoints de monitoramento (`/actuator/health`, `/actuator/info`, `/actuator/metrics`).

---

## 📂 Estrutura de Pacotes e Classes

### 1. **Classe Principal**

* **`HelpdeskBackendApplication.java`**

  * Ponto de entrada da aplicação.
  * Anotada com `@SpringBootApplication`.

### 2. **Pacote `security`**

* **`JwtUtil.java`**

  * Geração e validação de tokens JWT usando `io.jsonwebtoken.security.Keys`.

* **`JwtAuthenticationFilter.java`**

  * Filtro que intercepta requisições, extrai e valida o token JWT, populando o `SecurityContext`.

* **`SecurityConfig.java`**

  * Configuração do Spring Security:

    * Políticas stateless (sem sessão).
    * Libera `/login` sem autenticação.
    * Protege `/api/tickets/**` exigindo JWT.
    * Registra o `JwtAuthenticationFilter`.
    * Usuários em memória: `admin` (ROLE\_ADMIN) e `user` (ROLE\_USER).

### 3. **Pacote `controller`**

* **`AuthController.java`**

  * Endpoint `POST /login`:

    * Recebe JSON `{ "username": ..., "password": ... }`.
    * Autentica usando `AuthenticationManager`.
    * Retorna o JWT como string.

* **`TicketController.java`**

  * Endpoints CRUD para tickets em `/api/tickets`:

    * `GET /api/tickets` (listar todos).
    * `POST /api/tickets` (criar).
    * `GET /api/tickets/{id}` (buscar por ID).
    * `PUT /api/tickets/{id}` (atualizar).
    * `DELETE /api/tickets/{id}` (deletar).

### 4. **Pacote `model`**

* **`Ticket.java`**

  * Entidade JPA com campos:

    * `id` (PK, auto-increment).
    * `titulo` (String).
    * `descricao` (String).
    * `status` (String, padrão "ABERTO").
  * Anotada com `@Entity` e usa `@Data` do Lombok.

### 5. **Pacote `repository`**

* **`TicketRepository.java`**

  * Interface `JpaRepository<Ticket, Long>` com método customizado:

    * `List<Ticket> findByStatus(String status)`.

### 6. **Pacote `service`**

* **`TicketService.java`**

  * Lógica de negócio para tickets:

    * `criarTicket`.
    * `listarTickets`.
    * `buscarPorId`.
    * `atualizarTicket`.
    * `deletarTicket`.

---

## 🔧 Configurações de Banco e Perfis

### `application.properties` (base)

```properties
spring.profiles.active=dev
```

### `application-dev.properties`

```properties
spring.datasource.url=${DB_URL:jdbc:oracle:thin:@##########}
spring.datasource.username=${DB_USER:##########}
spring.datasource.password=${DB_PASS:##########}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.hibernate.ddl-auto=update

management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
management.endpoints.web.base-path=/actuator
```

> **Nota**: Variáveis de ambiente (`DB_URL`, `DB_USER`, `DB_PASS`) podem ser definidas em `application-local.properties` não versionado.

---

## 💡 Próximos Passos

* Implementar **DTOs** (`TicketRequest`, `TicketResponse`) e **validação** de entrada.
* Criar **tratamento global de exceções** com `@ControllerAdvice`.
* Documentar a API com **Swagger/OpenAPI**.
* Configurar **CI/CD** e pipeline de deploy.

---

> **Observação**: Não inclua no repositório arquivos com credenciais sensíveis. Utilize variáveis de ambiente ou arquivos de configuração ignorad
