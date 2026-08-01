# faruk-base2

Template de engenharia com **Matt Pocock Skills** + exemplo **Spring Boot** (`examples/java-crud`).

## Conteúdo

- **Skills** — Matt Pocock (`npx skills add mattpocock/skills`), lock em `skills-lock.json`
- **Setup agente** — `AGENTS.md`, `docs/agents/`, `HELP-SKILLS.md`
- **Exemplo** — CRUD de produtos: Java 21, Spring Boot 4.1, PostgreSQL, Swagger, Docker

## Quick start (exemplo Java)

```bash
cd examples/java-crud
docker compose up --build -d
```

- API: http://localhost:8090/api/products
- Swagger: http://localhost:8090/swagger-ui/index.html

## Skills

Ver [`HELP-SKILLS.md`](HELP-SKILLS.md). Roteador: `/ask-matt`.

Restaurar skills após clone:

```bash
npx skills experimental_install
```

## Documentação do example

- [`examples/java-crud/CONTEXT.md`](examples/java-crud/CONTEXT.md)
- [`examples/java-crud/docs/adr/`](examples/java-crud/docs/adr/)
