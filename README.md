# Backend
 
## Ferramentas/Tecnologias utilizadas
- Java 11;
- Spring Boot v2.6.3
- Postgresql 13
- Maven v4.0.0

## Preparando ambiente

### Passo 1: configuração do banco
>  *Você precisará criar um banco de dados com o nome 'framegram-db'*

### Passo 2: configurar application.properties
>  *Você precisará verificar se os dados passados no application.properties estão de acordo com a sua máquina*
#### application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/framegram-db
spring.datasource.username=postgres
spring.datasource.password=postgres
```
Obs: caso algum desses dados: username, password ou port esteja diferente do seu banco, favor corrigir.

## Rodando a aplicação 

Navegue até a raiz do projeto

Execute `cd framegram-digital/framegram/`

Execute `mvn spring-boot:run`

Após a finalização dessa execução dois usuários foram criados, com o uso da ferramenta Flyway

### Usuários criados

- Usuário UM
  - login: usuario01
  - password: 123456

- Usuário DOIS
  - login: usuario02
  - password: 123456

Utilize esses dois usuários para fazer os testes na aplicação

# Frontend

## Ferramentas/Tecnologias utilizadas
- Angular 9

## Rodando a aplicação 

Execute `npm install`

Execute `npm run start`