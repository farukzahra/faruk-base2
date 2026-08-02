# Product CRUD — Spring Boot + Docker

Exemplo para testar o repositório [mattpocock/skills](https://github.com/mattpocock/skills): CRUD de produtos com **Spring Boot 4.1** e **Java 21**, tudo via Docker.

## Stack

| Componente | Versão |
|------------|--------|
| Java | 21 (LTS) |
| Spring Boot | 4.1.0 |
| PostgreSQL | 17 (Alpine) |
| Schema | Liquibase |

## Subir

```bash
cd examples/java-crud

# Só testes (Docker, sem subir app)
docker compose --profile test run --rm test

# App + banco (build roda testes antes de empacotar)
docker compose up --build -d
```

Aguarde ~1–2 min na primeira build (Maven baixa dependências).

A API fica em **http://localhost:8090** (porta 8090 no host → 8080 no container). Se 8090 estiver ocupada, altere em `docker-compose.yml`.

Profile padrão: **`dev`** (Swagger ligado). Para simular produção sem Swagger:

```bash
SPRING_PROFILES_ACTIVE=prod docker compose up --build -d
```

## Acesso ao banco (editor SQL)

Com `docker compose up -d` rodando, conecte seu editor (DBeaver, DataGrip, pgAdmin, etc.):

| Campo | Valor |
|-------|--------|
| Host | `localhost` |
| Porta | `5433` |
| Database | `products` |
| Usuário | `products` |
| Senha | `products` |

**JDBC:** `jdbc:postgresql://localhost:5433/products`

**psql via Docker:**

```bash
docker compose exec db psql -U products -d products
```

A porta **5433** evita conflito com PostgreSQL nativo (5432) e com o `fastapi-crud` (5434).

## Validar

```bash
# Health
curl http://localhost:8090/actuator/health

# Listar (vazio)
curl http://localhost:8090/api/products

# Criar
curl -X POST http://localhost:8090/api/products \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Gaming Mouse\",\"description\":\"8000 DPI\",\"price\":129.90,\"stockQuantity\":50}"

# Listar / buscar / atualizar / excluir
curl http://localhost:8090/api/products/1
curl -X PUT http://localhost:8090/api/products/1 \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Gaming Mouse Pro\",\"description\":\"12000 DPI\",\"price\":159.90,\"stockQuantity\":40}"
curl -X DELETE http://localhost:8090/api/products/1
```

## API

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/products` | Lista produtos (ordenado por `createdAt` DESC) |
| GET | `/api/products/{id}` | Busca por ID |
| POST | `/api/products` | Cria produto |
| PUT | `/api/products/{id}` | Atualiza produto |
| DELETE | `/api/products/{id}` | Remove produto |
| GET | `/actuator/health` | Health check |
| GET | `/swagger-ui/index.html` | Swagger UI (**profile dev**) |
| GET | `/v3/api-docs` | OpenAPI JSON (**profile dev**) |

## Swagger

Documentação interativa (somente com profile `dev`, padrão no compose):

- **Swagger UI:** http://localhost:8090/swagger-ui/index.html
- **OpenAPI JSON:** http://localhost:8090/v3/api-docs

(`/swagger-ui.html` redireciona para a UI.)

### Corpo (POST/PUT)

```json
{
  "name": "Product name",
  "description": "Optional description",
  "price": 99.90,
  "stockQuantity": 10
}
```

### Erros

```json
{ "error": "Product not found" }
```

## Parar

```bash
docker compose down
# com volume:
docker compose down -v
```

## Testes

**No Docker (recomendado):**

```bash
docker compose --profile test run --rm test
```

O build da imagem `app` também executa `mvn test` antes do `package`.

**Local** (requer Java 21+ e Maven no host):

```bash
mvn test
```

Cobertura: ciclo CRUD, 404 (GET/PUT/DELETE), validação inválida, ordenação por `createdAt` DESC, health.

## Fluxo /ask-fmz usado

```
/ask-fmz → /grill-with-docs → /implement
```

## Documentação de domínio

- **Glossário:** [`CONTEXT.md`](CONTEXT.md)
- **ADRs:** [`docs/adr/`](docs/adr/)
