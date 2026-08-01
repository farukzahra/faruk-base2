# ddl-auto update sem ferramenta de migration

O schema do banco é gerenciado por Hibernate `ddl-auto: update` no ambiente Docker (via `SPRING_JPA_HIBERNATE_DDL_AUTO`). Não há Flyway nem Liquibase. Isso simplifica o demo (sobe e funciona), mas não versiona migrations nem garante reprodutibilidade em produção — quem for além do exemplo deve introduzir migrations antes de deploy real.
