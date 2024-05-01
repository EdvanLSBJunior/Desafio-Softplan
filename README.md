# Softplan Gestão Ambiental - Desafio Técnico

Este repositório contém a solução desenvolvida para o desafio técnico proposto pela Softplan para o time de Gestão Ambiental, baseado na Rinha de Backend do primeiro trimestre de 2024.

## Licença

Este projeto é licenciado sob os termos da [Licença MIT](https://opensource.org/licenses/MIT).

## Visão Geral

A aplicação é uma solução fullstack que consiste em uma API Java + Spring Boot com banco de dados PostgreSQL no backend e uma aplicação React + Vite utilizando a biblioteca de elementos visuais Material UI no frontend. O ambiente de desenvolvimento é configurado com Docker, facilitando a execução e a escalabilidade da aplicação.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Junit
- Mockito
- Swagger
- PostgreSQL
- React
- Vite
- Material UI
- Docker
- Docker Compose
- Nginx
- Gatling

## Executando a Aplicação

Para executar a aplicação, siga os passos abaixo:

1. Certifique-se de ter o Docker previamente instalado na sua máquina.
2. Clone este repositório em seu ambiente local.
3. Navegue até a raiz do projeto `softplangestaoambiental-API`.
4. Execute o seguinte comando no terminal para iniciar a aplicação:
```
- docker compose up
```
Este comando iniciará todos os componentes da aplicação, incluindo frontend, backend, banco de dados e load balancer.

## Acessando a Aplicação

- O frontend estará disponível em: [http://localhost:3000/](http://localhost:3000/)
- O Swagger, utilizado para documentar a API, estará disponível em: [http://localhost:9999/swagger-ui/index.html#/](http://localhost:9999/swagger-ui/index.html#/)

## Endpoints da API

A API possui os seguintes endpoints:

1. **POST** - `/clientes/1/transacoes`
   - Descrição: Endpoint para criação de transações para o cliente com ID 1.
2. **GET** - `/clientes/1/extrato`
   - Descrição: Endpoint para obtenção do extrato do cliente com ID 1.

## Teste de Carga

Um teste de carga foi realizado utilizando o Gatling para avaliar o desempenho do load balancer configurado na aplicação.

## Executando o teste de carga 
Para executar o teste de carga basta navegar ate a raiz da API (softplangestaoambiental-API) e digitar o comando para rodar o script:
```
- ./executar-teste-local.ps1
```
O caminho do relatório é exibido ao término da simulação.
Os resultados/relatórios são salvos em: `./load-test/user-files/results`
