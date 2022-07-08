## API Is Mutant

## Executando a aplicação localmente

###  Dependências

Para executar a aplicação localmente é necessário instalar as seguintes dependências

- GIT
- Java 11
- Maven
- Docker
- Docker Compose

Após ter instalado com sucesso as dependências acima basta basta seguir os passos abaixo

1. Clonar o repositório do projeto

```
sudo git clone https://github.com/dev-caiquemacedof/api-is-mutant
```

2. Criar um artefato maven

```
sudo mvn clean package
```

3. Subir o ambiente da aplicação através do docker-compose

```
sudo docker-compose up 
```

4. Testando a API

```
 GET: http://localhost/stats
```

```
POST: http://localhost/mutant
{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
```

5. Documentação Completa da API hospedada
```
[api-is-mutant.doc](https://documenter.getpostman.com/view/21855450/UzJMqErd)
```