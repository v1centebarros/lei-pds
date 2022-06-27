# Exercício 3

O central de Taxis Bonk pretende criar um sistema de comunicação entre os seus vários taxis e a central. Pretendemos simular este cenário criando as seguintes
entidades:

- Central, caracterizada por um id
- Taxi, caracterizado por um id
  As entidades Central devem produzir mensagens que são recebidas por entidades Taxi. Uma mensagem é produzida por uma única Central e recebida por um único Taxi.

O objetivo é reduzir o número de dependências caóticas entre objetos Central e objetos Taxi e redirecionar as comunicações diretas entre eles de modo a que eles sejam forçados a colaborar via um outro objeto, comum a todos os outros.

## Solução

A solução implica usar o padrão **Mediator**. Na nossa resolução, a classe Mediator tem o papel de, como o nome indica, mediar a comunicação entre os objetos Central e os
objetos Taxi. Central e Taxi correspondem a objetos ConcreteColleague no diagrama UML dos diapositivos teóricos da disciplina, enquanto que a classe Mediator
corresponde a um ConcreteMediator.

## Fontes

* Slides da Aula
