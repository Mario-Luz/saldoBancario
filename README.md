# Documentação do Repositório “Saldo Bancário”

Este repositório contém uma aplicação Java chamada “Saldo Bancário”, que é uma aplicação bancária que permite criar, atualizar e deletar um cliente e atualizar seu saldo. A aplicação é construída usando Spring Boot e usa PostgreSQL como banco de dados. Além disso, a aplicação é containerizada usando Docker.

## Estrutura do Repositório

Aqui estão os principais arquivos e diretórios presentes no repositório:

- **README.md**: Este arquivo fornece uma visão geral do projeto, incluindo uma descrição detalhada, instruções de instalação e documentação das principais classes e métodos na aplicação.
- **src/**: Este diretório contém o código fonte da aplicação. Inclui as principais classes Java, como SaldoBancarioController.java, SaldoBancarioService.java, SaldoBancarioModel.java e SaldoBancarioRepository.java.
- **target/**: Este diretório contém a saída da compilação Maven. Inclui classes compiladas, relatórios de teste e a aplicação final empacotada.
- **docker-compose.yml**: Este arquivo é usado para definir e executar os serviços da aplicação usando Docker Compose. Ele especifica o serviço PostgreSQL do qual a aplicação depende.
- **application.properties**: Este arquivo está localizado no diretório target/classes/ e contém propriedades de configuração para a aplicação. Ele inclui configurações para a conexão com o banco de dados, JPA e Hibernate.

## Relatórios de Teste

O repositório também inclui arquivos de teste e relatórios no diretório target/. Os relatórios de teste indicam que houve um erro ao carregar o contexto da aplicação durante a execução do teste, possivelmente devido a um problema de conexão com o banco de dados.

## Alterações Recentes

As alterações feitas no repositório incluem a adição de novos arquivos de configurações do IntelliJ IDEA e a atualização do arquivo README.md com documentação detalhada sobre o projeto. 🚀
 
 
 # Saldo Bancario

## Descrição

Esta aplicação permite criar, atualizar, excluir um cliente e atualizar o seu saldo.

## Instalação

Para instalar e rodar a aplicação, execute os seguintes comandos:

bash
docker compose up -d
mvn clean install


# SaldoBancarioController.java

Este arquivo define o controlador REST para o saldo bancário.

## Classe

### SaldoBancarioController

Esta classe é anotada com `@RestController`, indicando que é um controlador REST. Ela contém métodos para manipular requisições HTTP.

#### Métodos

- `exibirTodasAsContas()`: Este método é mapeado para a requisição GET em "/contas/". Ele retorna todas as contas bancárias.

- `buscarContaPorId(String id)`: Este método é mapeado para a requisição GET em "/contas/{id}". Ele retorna uma conta bancária específica com base no ID fornecido.

- `cadastrarNovaConta(SaldoBancarioDto contaBancariaDto)`: Este método é mapeado para a requisição POST em "/contas/". Ele recebe um DTO de conta bancária e cria uma nova conta bancária.

- `fazerDeposito(String id, double valorFornecido)`: Este método é mapeado para a requisição PATCH em "/contas/". Ele recebe um ID de conta e um valor, e faz um depósito na conta correspondente.

- `fazerSaque(String id, double valorFornecido)`: Este método é mapeado para a requisição PATCH em "/contas/{id}/saque". Ele recebe um ID de conta e um valor, e faz um saque da conta correspondente.

## Anotações

- `@RestController`: Esta anotação é usada para indicar que a classe é um controlador REST.

- `@RequestMapping("/contas")`: Esta anotação é usada para mapear as requisições HTTP para este controlador para o caminho "/contas".

- `@Autowired`: Esta anotação é usada para injetar automaticamente o `SaldoBancarioService` neste controlador.

- `@GetMapping`, `@PostMapping`, `@PatchMapping`: Estas anotações são usadas para mapear as requisições HTTP GET, POST e PATCH, respectivamente, para os métodos correspondentes.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# SaldoBancarioService.java

Este arquivo é responsável por fornecer os serviços relacionados ao saldo bancário.

## Classes e Métodos

### SaldoBancarioService

Esta é a classe principal que fornece os serviços para o saldo bancário.

#### Métodos

- `exibirTodas()`: Este método retorna uma lista de todas as contas bancárias disponíveis. Ele recupera todas as contas do repositório e as converte em DTOs antes de retornar.

- `buscarPorId(String id)`: Este método busca uma conta bancária específica pelo seu ID. Se a conta for encontrada, ela é convertida em um DTO e retornada. Se a conta não for encontrada, retorna um Optional vazio.

- `cadastrar(SaldoBancarioDto saldoBancarioDto)`: Este método recebe um DTO de saldo bancário, converte-o em um modelo e o salva no repositório. O modelo de saldo bancário é então retornado.

- `depositar(String id, double valorFornecido)`: Este método recebe um ID de conta e um valor a ser depositado. Ele busca a conta pelo ID, atualiza o saldo e o valor fornecido, e salva a conta atualizada no repositório.

## Dependências

- `SaldoBancarioRepository`: Este é o repositório que é usado para buscar e salvar as contas bancárias.

- `SaldoBancarioModel` e `SaldoBancarioDto`: Estes são os modelos e DTOs usados para representar as contas bancárias.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# SaldoBancarioModel.java

Este arquivo define o modelo de dados para o saldo bancário.

## Classe

### SaldoBancarioModel

Esta classe representa o modelo de dados para o saldo bancário.

#### Propriedades

- `id`: Esta é a chave primária da tabela. É gerada automaticamente e é do tipo UUID.

- `numeroConta`: Esta propriedade representa o número da conta. É uma string de comprimento máximo 20 e não pode ser nula.

- `agencia`: Esta propriedade representa a agência bancária. É uma string de comprimento máximo 6 e não pode ser nula.

- `nome`: Esta propriedade representa o nome do titular da conta. É uma string de comprimento máximo 200 e não pode ser nula.

- `saldo`: Esta propriedade representa o saldo atual da conta. É um valor double e não pode ser nulo.

- `valorFornecido`: Esta propriedade representa o valor fornecido para a conta. É um valor double e não pode ser nulo.

- `tipoServico`: Esta propriedade representa o tipo de serviço da conta. É uma string de comprimento máximo 200 e não pode ser nula.

## Anotações

- `@Entity`: Esta anotação indica que a classe é uma entidade JPA.

- `@Table`: Esta anotação é usada para definir o nome da tabela como "cliente".

- `@Id`: Esta anotação indica que o campo é a chave primária da tabela.

- `@GeneratedValue`: Esta anotação é usada para especificar a estratégia de geração de valor para a chave primária.

- `@Column`: Esta anotação é usada para definir as propriedades da coluna, como comprimento, nullable, etc.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# SaldoBancarioRepository.java

Este arquivo define a interface do repositório para o saldo bancário.

## Interface

### SaldoBancarioRepository

Esta interface estende `CrudRepository` do Spring Data JPA, que fornece métodos CRUD genéricos para a entidade `SaldoBancarioModel`. A chave primária da entidade é do tipo `UUID`.

#### Métodos

Como esta interface estende `CrudRepository`, ela herda uma série de métodos para trabalhar com a persistência de dados, incluindo:

- `save(S)`: Salva uma entidade.
- `findById(ID)`: Recupera uma entidade pelo seu ID.
- `existsById(ID)`: Retorna se uma entidade com tal ID existe.
- `findAll()`: Retorna todas as entidades.
- `deleteById(ID)`: Deleta a entidade com o ID fornecido.

## Anotações

- `@Repository`: Esta anotação é usada para indicar que a interface é um repositório Spring. Isso permite que o Spring Data JPA crie uma implementação do repositório.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
