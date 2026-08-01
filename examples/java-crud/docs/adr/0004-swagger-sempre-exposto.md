# Swagger UI sempre exposto

SpringDoc OpenAPI 3.x expõe `/swagger-ui/index.html` e `/v3/api-docs` em todos os ambientes, sem profile que desligue a UI em produção. Decisão deliberada para demo e template de aprendizado: documentação interativa sempre acessível. Quem deployar em internet pública deve avaliar se expor a spec completa é aceitável para o seu caso.
