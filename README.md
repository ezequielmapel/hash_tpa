## Versões
  * Java 11;
  * _Spring_ 2.7.0;
## Como rodar a aplicação?
  * Baixe o código e adicione a uma pasta de nome _com.ifes.hashdict_ (referenciado no pom.xml);
  * Instale as dependências do _Maven_;
  * Rode a aplicação do _Spring_ (porta 8080);
  * Acesse http://localhost:8080
## Como funciona?
### Backend
  * 1 Hash Table
    * 1.1 Quando a aplicação é iniciada, um _service_ lê e inicializa a _hash table_ que é usada pra armazenar as palavras do dicionário.
    * 1.2 Palavaras que possuirem colisão, serão adicionadas em uma cadeia (_linked list_).
  * 2 Requisição
    * 2.1 Quando uma requisição é solicitada, é verificado quais palavras do texto, não possuem no dicionário. Por fim, retornamos as palavras não encontradas.
    * 2.2 Uma segunda requisição pode ser acionada (apenas para palavras incorretas), dessa vez, é gerado um cálculo das possíveis palavras¹, que ao final, será verificado quais destas palavaras geradas existem no dicionário, por fim, retornaremos apenas as corretas.
### Fronted
  * 1 Página - Digitação
    * 1.1 É uma página com o instuíto de lembrar uma folha.
    * 1.2 Quando digitado, após alguns segundos será acionado um evento que enviará o texto ao servidor.
  * 2 Correção
    * 2.1 Quando uma palavra está incorreta, ela sofrerá uma mudança visual (ondulação vermelha).
    * 2.2 Ao clicar com botão direto na palavra incorreta, acionará o evento de encontrar as possíveis palavras corretas.


