1 - Executar todos os testes:

mvn test

2 - Executar a aplicação:

mvn spring-boot:run

3 - Sugestão de Banco de Dados: Postgres

Scripts de criação completo em DB Script.txt

4 - Sugestão de execução com curl abaixo ou collection do Postman na raiz do repositório

Passo 1: criar pessoas

curl --location --request POST 'localhost:2000/persons' --header 'Content-Type: application/json' --data-raw '{
    "name": "Fulano1",
    "cpf": "12345678902",
    "birthDate": "1978-04-04"
}   '

Passo 2: criar uma conta para cada pessoas passando o id da pessoa
No exemplo abaixo é criado uma conta para a pessoa de id 1 já com um depósito inicial de R$1.000,000 e com o limite de saque diário de R$10,00 e tipo de conta poupança (tipo 2)

curl --location --request POST 'localhost:2000/accounts/1' --header 'Content-Type: application/json' --data-raw '{"balance":"1000.00",
"dailyWithdrawalLimit":"10.00",
"active":"true",
"accountType":2
}'

Passo 3: creditar valores na conta. 
No exemplo abaixo é credito R$2.34 na conta de id 1

curl --location --request POST 'localhost:2000/transactions/1/credit/2.34'

Passo 4: debitar valores na conta
No exemplo abaixo foi debitado R$1,23 da conta id 1

curl --location --request POST 'localhost:2000/transactions/1/withdrawal/1.23'

Passo 5: tirar um extrato dda conta por período

curl --location --request GET 'localhost:2000/transaction/1/bankstatement' --header 'Content-Type: application/json' --data-raw '{
    "fromDate":"2020-08-02",
    "toDate":"2020-08-03"
}'

Passo 6: bloquear a conta
Exemplo abaixo bloqueia a conta de id 1

curl --location --request POST 'localhost:2000/accounts/block/1'






	


