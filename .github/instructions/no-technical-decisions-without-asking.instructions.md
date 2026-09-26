---
description: Nunca decidir stack ou arquitetura sozinho — sempre perguntar com opções e recomendação
applyTo: "**"
---

# Decisões técnicas: sempre perguntar

Antes de implementar, configurar ou documentar qualquer **decisão técnica**, **não escolher sozinho**. Perguntar ao usuário e **esperar confirmação** antes de codar, escrever ADR ou publicar spec/tickets.

## O que conta como decisão técnica

- Linguagem (TypeScript vs JavaScript, Java vs Python, etc.)
- Frameworks e bibliotecas (Vue vs React, Vuetify vs CSS puro, Pinia vs sem store, etc.)
- Ferramentas de build/teste (Vite, Playwright, Jest, etc.)
- Arquitetura (camadas, padrões, proxy vs CORS, ORM, migrations vs ddl-auto)
- Portas, backends default, estrutura de pastas quando há alternativas válidas
- Qualquer escolha que apareceria em **Implementation Decisions** ou **ADR**

## O que NÃO precisa perguntar (fatos)

- Ler o repo, contratos existentes, portas já documentadas, convenções já em ADR/CONTEXT
- Seguir ADRs e specs **já aprovadas** pelo usuário nesta conversa

## Como perguntar

1. **Usar o formulário clicável** (`AskQuestion`) — opções que o usuário **clica** no chat. **Não** usar listas longas em markdown (`Q1: A/B/C`, bullets para marcar).
2. **Uma decisão por vez** (como `/grilling`), **exceto** se o usuário pedir ver todas de uma vez — aí um `AskQuestion` com **várias perguntas**, cada uma com opções clicáveis.
3. Apresentar **2–4 opções concretas** + **(Recomendado)** na opção preferida, com uma linha de por quê.
4. **Parar** até o usuário responder — não implementar na mesma mensagem após a pergunta.
5. Registrar a resposta em **ADR** ou na spec antes de `/implement`.

## Atalhos proibidos

- Não inferir stack “porque o exemplo anterior usou X”.
- Não tratar “fluxo completo” ou “mesma coisa que Product” como licença para decidir UI/stack.
- `/to-spec` sintetiza o que foi discussado — se não houve grill, **não** publicar spec com decisões inventadas.
