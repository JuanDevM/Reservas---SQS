Ejecución inicio proyecto
Seguir las siguientes indicaciones

Comandos LocalStack SQS:

sudo service docker start
localstack start -d
awslocal sqs create-queue --queue-name reservas-hoteles
awslocal iam create-user --user-name reservas-hoteles
awslocal iam create-access-key --user-name reservas-hoteles
Despues de la ejecución de los comandos actualizar las credenciales en las propiedades del proyecto

Para listar ver los registros en la cola:

awslocal sqs receive-message --queue-url http://localhost:4566/000000000000/reservas-hoteles
