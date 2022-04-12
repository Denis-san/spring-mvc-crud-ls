# spring-mvc-crud-ls

Um crud simples, pondo em prática conhecimentos sobre o padrão de desevolvimento MVC (Model View Controller), persistência de dados, 
configuração de um database, criação e estilização de páginas html, validação de dados in server side, validações personalizadas com JPA validation,
mapeamento de entidades para persistência com o JPA e etc, utilizando: 

- Java,

linguagem base;

- JPA (Java Persitence API),
- Hibernate,

para persitência de dados;

- PostgreSQL,

banco de dados;

- Spring Framework,
- Spring Boot,
- Spring MVC,

para construção da aplicação web;

- Thymeleaf,

como template, (view) da aplicação;

- Bootstrap,
- javascript, 
- css

para estilização das páginas da aplicação;

#
### Algumas screeshots:

<div float="left"> 
  <img src="/screenshots/home.png " alt="pagina de registro" width="150px">
  <img src="/screenshots/book_details.png " alt="pagina de registro" width="150px"> 
  <img src="/screenshots/author_details.png.png " alt="pagina de registro" width="150px">
  <img src="/screenshots/list_book.png " alt="pagina listagem registro" width="150px">
  <img src="/screenshots/register.png " alt="pagina registro" width="150px">
  <img src="/screenshots/validations.png" alt="validação dos campos" width="150px">
</div>

<a href="/screenshots/">ver mais...</a>


#
###  Algumas características:

- <h5 CRUD - Create, Read, Update, Delete</h5>
  Seguindo as características de um crud, é possível criar novos registros, listar e ler os mesmos, editar/atualizar e deletar.

- <h5>Validação dos campos, "in server side", os campos são validados no servidor antes de realizar a operção, exibindo um feedback ao usuário.</h5>
    Os campos são validados a partir das epecificações da javax.validation e hibernate.validator, porém há também algumas anotações personalizadas.

- <h5>Relações entre as tabelas do banco de dados</h5>
  Existem algumas relações entre entidades que correspondem a algumas tabelas no database, como: 
  
  - muitos **Livros** para uma **Linguagem** (bidirecional).
  - muitos **Livros** para muitos **Autores** (Bidirecional).

Isso é realizado através das anotações da JPA, como @ManyToOne, @OneToMany, @ManyToMany...

- <h5>Algumas operações não são em cascata</h5>
  Operações como deletar, não são realizadas em cascata, por exemplo, é possível deletar um livro, porém seus autores serão mantidos, pois os mesmos podem possuir relações com outras entidades.
  
#
  
Enfim, uma aplição web apenas para fins estudantis.
