# Liquibase para schema versionado

O schema da tabela `products` é gerenciado por **Liquibase** (`src/main/resources/db/changelog/`). Hibernate usa `ddl-auto: validate` — a app não cria nem altera tabelas em runtime.

O mesmo changelog roda no **PostgreSQL** (Docker) e no **H2 em memória** (profile `test`), mantendo um único source of truth. O changeset inicial usa precondição `tableExists`: se a tabela já existir (volume antigo de `ddl-auto`), o Liquibase marca o changeset como executado em vez de falhar.

Quem for além do exemplo adiciona novos changesets em vez de editar SQL à mão no banco.
