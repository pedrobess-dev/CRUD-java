# CRUD em Java com MySQL

Este é um sistema simples de cadastro desenvolvido em Java, utilizando o banco de dados MySQL. O sistema permite realizar operações de criação, leitura, atualização e exclusão de registros, além de possuir uma interface gráfica construída com Java Swing.

## Funcionalidades

- **Adicionar Registro**: Permite inserir novos registros no banco de dados.
- **Listar Registros**: Exibe todos os registros armazenados.
- **Atualizar Registro**: Permite modificar os dados de um registro existente.
- **Remover Registro**: Permite deletar um registro do banco de dados.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal do projeto.
- **MySQL**: Sistema de gerenciamento de banco de dados relacional utilizado para armazenar os dados.
- **Java Swing**: Biblioteca gráfica para a construção da interface do usuário.

## Pré-requisitos

- Java Development Kit (JDK) instalado.
- Servidor MySQL configurado e em execução.
- Biblioteca JDBC do MySQL adicionada ao projeto.

## Configuração do Banco de Dados

1. Crie um banco de dados no MySQL:
   ```sql
   CREATE DATABASE nome_do_banco;
   ```
2. Crie uma tabela para armazenar os registros:
   ```sql
   CREATE TABLE registros (
       id INT AUTO_INCREMENT PRIMARY KEY,
       nome VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL,
       telefone VARCHAR(15)
   );
   ```

## Configuração do Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/pedrobess-dev/CRUD-java.git
   ```
2. Abra o projeto em sua IDE Java preferida.
3. Adicione a biblioteca JDBC do MySQL ao build path do projeto.
4. Configure as credenciais de acesso ao banco de dados no código-fonte, geralmente no arquivo de configuração ou na classe de conexão:
   ```java
   String url = "jdbc:mysql://localhost:3306/nome_do_banco";
   String usuario = "seu_usuario";
   String senha = "sua_senha";
   ```

## Executando o Projeto

1. Certifique-se de que o servidor MySQL está em execução.
2. Compile e execute a aplicação a partir da sua IDE.
3. A interface gráfica será exibida, permitindo interagir com o sistema de cadastro.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para mais detalhes.

## Autor

Pedro Bess - [pedrobess-dev](https://github.com/pedrobess-dev)
