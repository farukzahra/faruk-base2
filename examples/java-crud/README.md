# Product CRUD — Spring Boot + Docker

Exemplo para testar o repositório [mattpocock/skills](https://github.com/mattpocock/skills): CRUD de produtos com **Spring Boot 4.1** e **Java 21**, tudo via Docker.

## Stack

| Componente | Versão |
|------------|--------|
| Java | 21 (LTS) |
| Spring Boot | 4.1.0 |
| PostgreSQL | 17 (Alpine) |

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

## Validar

```bash
# Health
curl http://localhost:8090/actuator/health

# Listar (vazio)
curl http://localhost:8090/api/products

# Criar
curl -X POST http://localhost:8090/api/products \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Mouse Gamer\",\"description\":\"8000 DPI\",\"price\":129.90,\"stockQuantity\":50}"

# Listar / buscar / atualizar / excluir
curl http://localhost:8090/api/products/1
curl -X PUT http://localhost:8090/api/products/1 \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Mouse Gamer Pro\",\"description\":\"12000 DPI\",\"price\":159.90,\"stockQuantity\":40}"
curl -X DELETE http://localhost:8090/api/products/1
```

## API

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/products` | Lista produtos |
| GET | `/api/products/{id}` | Busca por ID |
| POST | `/api/products` | Cria produto |
| PUT | `/api/products/{id}` | Atualiza produto |
| DELETE | `/api/products/{id}` | Remove produto |
| GET | `/actuator/health` | Health check |
| GET | `/swagger-ui/index.html` | Swagger UI (documentação interativa) |
| GET | `/v3/api-docs` | OpenAPI JSON |

## Swagger

Documentação interativa da API:

- **Swagger UI:** http://localhost:8090/swagger-ui/index.html
- **OpenAPI JSON:** http://localhost:8090/v3/api-docs

(`/swagger-ui.html` redireciona para a UI.)

### Corpo (POST/PUT)

```json
{
  "name": "Nome do produto",
  "description": "Descrição opcional",
  "price": 99.90,
  "stockQuantity": 10
}
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

## Fluxo /ask-matt usado

Escopo claro → **implement** direto (sem multi-sessão):

```
/implement  →  CRUD + Docker + testes de integração
```

Para features maiores neste repo, use `/grill-with-docs` → `/to-spec` → `/to-tickets` → `/implement`.

## Documentação de domínio

- **Glossário:** [`CONTEXT.md`](CONTEXT.md)
- **ADRs:** [`docs/adr/`](docs/adr/)
