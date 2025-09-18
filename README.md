# AgileBoard 🚀
---

## ✨ Principais Funcionalidades

- **Autenticação e Segurança:** Registro e login com autenticação *stateless* via Tokens JWT.  
- **Gerenciamento Completo:** CRUDs para Projetos, Sprints e Tarefas.  
- **Quadro Kanban Interativo:** Arraste e solte tarefas entre colunas (A Fazer, Em Progresso, Concluído).  
- **Design Responsivo:** Interface adaptada para desktop e dispositivos móveis.  
- **API Documentada:** Documentação interativa com Swagger (OpenAPI).  
- **Banco de Dados Versionado:** Controle de versão com Flyway para garantir consistência entre ambientes.  

---

## 🛠️ Tecnologias Utilizadas

### Backend (API RESTful)
- **Linguagem:** Java 21  
- **Framework:** Spring Boot 3.5.5  
- **Segurança:** Spring Security 6, Tokens JWT (jjwt 0.13.0)  
- **Persistência de Dados:** Spring Data JPA / Hibernate  
- **Banco de Dados:** PostgreSQL  
- **Migrações:** Flyway  
- **Documentação:** Swagger (Springdoc OpenAPI 2.8.13)  
- **Build Tool:** Maven  

### Frontend (SPA)
- **Framework:** Angular 20  
- **Linguagem:** TypeScript  
- **Estilização:** SCSS  
- **UI/UX:** Componentes reutilizáveis, Roteamento e Angular CDK (Drag & Drop)  

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- [Java JDK 21](https://adoptium.net/)  
- [Maven](https://maven.apache.org/)  
- [Node.js + npm](https://nodejs.org/)  
- [Angular CLI 20+](https://angular.dev/)  
- [PostgreSQL](https://www.postgresql.org/)  

---

### 1. Backend

```bash
# Clone o repositório
git clone https://github.com/johnwesleysa/agileboard.git
cd agileboard/backend

# Configure o application.properties
# Edite o arquivo src/main/resources/application.properties
# Exemplo:
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Execute a aplicação
mvn spring-boot:run
```

O backend estará disponível em: **http://localhost:8080**

---

### 2. Frontend

```bash
# Em um novo terminal
cd agileboard/frontend

# Instale as dependências
npm install

# Execute a aplicação
ng serve
```

O frontend estará disponível em: **http://localhost:4200**

---

## 📖 Documentação da API

Com o backend rodando, a documentação interativa gerada pelo **Swagger** está acessível em:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 👨‍💻 Autor

**John Wesley Santos Alencar**

- [LinkedIn](https://www.linkedin.com/in/johnwesley57)  
- [GitHub](https://github.com/johnwesleysa)  

---
