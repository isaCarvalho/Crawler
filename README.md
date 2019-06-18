# Crawler
Web crawler em Kotlin

Este crawler busca uma url que o usuário fornecerá e armazena as referências que a página buscada possui na base de dados.
A partir da base de dados, o usuário pode buscar, por uma palavra-chave, quais urls referenciadas possuem aquela palavra-chave.
Este crawler utiliza um ranking baseado na quantidade de vezes que uma determinada url é referenciada. O resultado da busca virá de acordo com qual url possui maiores posições no ranking.

## Pré-requisitos

1. Kotlin 1.3.0
2. Mysql 5.0
3. Driver de conexão JDBC

