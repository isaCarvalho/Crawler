# Crawler
Web crawler em Kotlin

Este crawler busca uma url que o usuário fornecerá e armazena as referências que a página buscada possui na base de dados.
A partir da base de dados, o usuário pode buscar, por uma palavra-chave, quais urls referenciadas possuem aquela palavra-chave.
Este crawler utiliza um ranking baseado na quantidade de vezes que uma determinada url é referenciada. O resultado da busca virá de acordo com qual url possui maiores posições no ranking.

## Compatibilidade

Este crawler é compatível com os Sistemas Gerenciados de Banco de Dados PostgreSQL e MySQL.

## Conexão

Os drivers de conexão com ambos os SBGDs estão na pasta 'lib' e os scripts para os repectivos SGBDs estão na pasta 'db'. IMPORTANTE: Caso seja preferível usar o MySQL, deve-se utilizar os blocos de comandos comentados na classe Conexão.

## Pré-requisitos

1. Kotlin 1.3.0
2. PostgreSQL 11 ou Mysql 5.0


