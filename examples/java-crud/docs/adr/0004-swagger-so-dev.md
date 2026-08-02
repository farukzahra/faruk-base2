# Swagger UI só no profile dev

SpringDoc OpenAPI expõe `/swagger-ui/index.html` e `/v3/api-docs` **apenas** com `SPRING_PROFILES_ACTIVE=dev` (padrão no `docker compose up`). No profile `prod`, a UI e o JSON ficam desligados.

Decisão deliberada: demo local com documentação interativa; quem simular produção seta `SPRING_PROFILES_ACTIVE=prod` no compose ou no deploy.
