# Guia de Comandos — Matt Pocock Skills

Referência em português de todos os comandos (`/nome-da-skill`) instalados neste projeto.

## Como usar

1. Digite o comando no chat do Cursor, por exemplo: `/grill-with-docs quero um CRUD Spring Boot`
2. Você **não precisa** chamar cada skill manualmente — muitas encadeiam outras por baixo (veja [Encadeamento automático](#encadeamento-automático))
3. Se não souber por onde começar, use **`/ask-matt`**
4. Rode **`/setup-matt-pocock-skills`** uma vez por repositório antes do primeiro fluxo de engenharia

### Tipos de skill

| Tipo | Como dispara | O que significa |
|------|--------------|-----------------|
| **User-invoked** | Só quando você digita `/comando` | Você controla quando o processo começa |
| **Model-invoked** | Você digita **ou** o agente usa sozinho quando encaixa | Roda por baixo de outras skills ou em pedidos relacionados |

---

## Workflow principal (idea → ship)

Fluxo recomendado para construir uma feature do zero:

```
/ask-matt                    ← não sabe por onde começar? comece aqui
     ↓
/grill-with-docs             ← alinhar requisitos (com codebase)
     ↓ (opcional)
/prototype                     ← se precisar ver código/UI descartável
     ↓
/to-spec                       ← virar spec (se multi-sessão)
     ↓
/to-tickets                    ← quebrar em tickets
     ↓
/implement                     ← construir (puxa /tdd e /code-review)
```

**Sem codebase ainda?** Troque `/grill-with-docs` por `/grill-me`.

**Feature pequena, mesma sessão?** Pule `/to-spec` e `/to-tickets` — vá direto de `/grill-with-docs` para `/implement`.

**Pedido livre** (*"faça um CRUD"*) **sem** `/comando` → o agente codifica direto, **fora** deste workflow.

---

## Encadeamento automático

| Você chama | O agente puxa por baixo |
|------------|-------------------------|
| `/grill-with-docs` | `/grilling`, `/domain-modeling` |
| `/grill-me` | `/grilling` |
| `/implement` | `/tdd`, `/code-review` + commit |
| `/triage` | `/grilling`, `/domain-modeling` (se precisar) |
| `/improve-codebase-architecture` | `/grilling` (ao escolher candidato) |
| `/wayfinder` | `/grilling`, `/domain-modeling` |

---

## Comandos — Engenharia

Skills para trabalho de código no dia a dia.

### Roteador e setup

| Comando | Tipo | Descrição |
|---------|------|-----------|
| `/ask-matt` | User | **Roteador.** Descreva sua situação e ele indica qual skill ou fluxo usar. Use quando não lembrar qual comando chamar. |
| `/setup-matt-pocock-skills` | User | **Setup inicial (1× por repo).** Configura issue tracker, labels de triagem e layout de docs de domínio (`docs/agents/`). |

### Planejamento e alinhamento

| Comando | Tipo | Descrição |
|---------|------|-----------|
| `/grill-with-docs` | User | Entrevista implacável para afiar um plano ou design **com codebase**. Cria/atualiza `CONTEXT.md` e ADRs conforme decide. **Ponto de entrada principal** quando já existe código. |
| `/grill-me` | User | Mesma entrevista, mas **sem codebase** — não salva nada localmente. Para planos que ainda não moram num repo. |
| `/batch-grill-me` | User | Entrevista em lote: faz todas as perguntas da fronteira de uma vez, rodada por rodada. |
| `/to-spec` | User | Transforma a conversa atual numa spec e publica no issue tracker. Sem entrevista — só sintetiza o que já foi discutido. |
| `/to-tickets` | User | Quebra plano, spec ou conversa em tickets tracer-bullet, cada um com dependências (blockers) declaradas. |
| `/wayfinder` | User | Para esforços **enormes e nebulosos** (greenfield, feature gigante). Cria um mapa de tickets de **decisão** no tracker e resolve um por um até o caminho ficar claro. Quando clarear, segue para `/to-spec`. |

### Implementação e qualidade

| Comando | Tipo | Descrição |
|---------|------|-----------|
| `/implement` | User | Implementa trabalho descrito numa spec ou tickets. Usa `/tdd` internamente, roda `/code-review` no final e faz commit. |
| `/tdd` | Model | Desenvolvimento orientado a testes (red → green → refactor). Vertical slices, testes em seams públicas. Dispara sozinho se você mencionar TDD, red-green-refactor ou test-first. |
| `/code-review` | Model | Revisão em dois eixos: **Standards** (padrões do repo) e **Spec** (bate com o issue/PRD?). Use sozinho para revisar branch ou PR. |
| `/prototype` | Model | Protótipo descartável para responder uma pergunta de design (lógica de estado, UI). Joga fora depois — fica só a resposta. |
| `/diagnosing-bugs` | Model | Loop de diagnóstico para bugs difíceis e regressões de performance. Exige feedback loop apertado antes de teorizar. Dispara em pedidos de debug/diagnóstico. |
| `/resolving-merge-conflicts` | Model | Resolve conflitos de merge/rebase hunk a hunk, por intenção de cada lado. Nunca usa `--abort`. |

### Issues e triagem

| Comando | Tipo | Descrição |
|---------|------|-----------|
| `/triage` | User | Move issues (e PRs externos, se configurado) por papéis de triagem: categorizar, verificar, grill se precisar, gerar briefs prontos para agente. **Só para issues que você não criou** — não triage tickets do `/to-tickets`. |
| `/qa` | Model | Sessão interativa de QA: você reporta bugs na conversa, o agente abre issues no GitHub. |

### Arquitetura e domínio

| Comando | Tipo | Descrição |
|---------|------|-----------|
| `/improve-codebase-architecture` | User | Varre o codebase por oportunidades de **aprofundamento** (deep modules), gera relatório HTML visual, depois grill no candidato escolhido. Rode periodicamente para manter o código agent-friendly. |
| `/codebase-design` | Model | Vocabulário compartilhado para desenhar módulos profundos (interface pequena, muito comportamento atrás). Usado por `/tdd` e `/improve-codebase-architecture`. |
| `/domain-modeling` | Model | Afia a linguagem de domínio: desafia termos vagos, resolve palavras sobrecarregadas, registra ADRs. Mantém `CONTEXT.md` como glossário limpo. |
| `/design-an-interface` | Model | Gera várias interfaces radicalmente diferentes para um módulo (sub-agentes paralelos). "Design it twice". |
| `/ubiquitous-language` | Model | Extrai glossário estilo DDD da conversa, sinaliza ambiguidades, salva em `UBIQUITOUS_LANGUAGE.md`. |
| `/request-refactor-plan` | Model | Cria plano de refactor detalhado com commits pequenos via entrevista, arquiva como issue no GitHub. |

### Pesquisa

| Comando | Tipo | Descrição |
|---------|------|-----------|
| `/research` | Model | Investiga uma pergunta em fontes primárias confiáveis e deixa um Markdown citado no repo. Pode rodar em background enquanto você continua trabalhando. |

---

## Comandos — Produtividade

Ferramentas de workflow geral, não específicas de código.

| Comando | Tipo | Descrição |
|---------|------|-----------|
| `/handoff` | User | Compacta a conversa atual num documento de handoff. Abra **nova sessão** referenciando esse arquivo para continuar. Ponte entre janelas de contexto. |
| `/claude-handoff` | User | Handoff para um agente background fresco que retoma o trabalho imediatamente. |
| `/teach` | User | Ensina um conceito ou skill em múltiplas sessões, usando o diretório atual como workspace. |
| `/writing-great-skills` | User | Referência para escrever e editar skills bem — vocabulário e princípios. |
| `/grilling` | Model | Primitiva de entrevista implacável. Normalmente invocada por `/grill-me` e `/grill-with-docs`, não diretamente. |

---

## Comandos — Escrita e conteúdo

| Comando | Tipo | Descrição |
|---------|------|-----------|
| `/edit-article` | Model | Edita e melhora artigos: reestrutura seções, clareza, prosa mais enxuta. |
| `/writing-fragments` | User | Escrita, fase explore — minera fragmentos brutos, sem estrutura ainda. |
| `/writing-beats` | User | Escrita, fase exploit — monta jornada de beats a partir do material bruto. |
| `/writing-shape` | User | Escrita, fase exploit — molda material bruto em artigo, parágrafo por parágrafo. |
| `/to-questionnaire` | User | Transforma uma decisão que você não consegue fechar num questionário para outra pessoa preencher. |
| `/obsidian-vault` | Model | Busca, cria e organiza notas no vault Obsidian com wikilinks e index notes. |

---

## Comandos — Setup de projeto e tooling

| Comando | Tipo | Descrição |
|---------|------|-----------|
| `/setup-pre-commit` | Model | Configura Husky + lint-staged (Prettier), typecheck e testes no pre-commit. |
| `/setup-ts-deep-modules` | User | Configura dependency-cruiser para que cada package TypeScript seja um deep module. |
| `/git-guardrails-claude-code` | Model | Hooks no Claude Code para bloquear git destrutivo (push, reset --hard, etc.). |
| `/migrate-to-shoehorn` | Model | Migra testes de `as` assertions para `@total-typescript/shoehorn`. |
| `/scaffold-exercises` | Model | Cria estrutura de diretórios de exercícios (sections, problems, solutions, explainers). |
| `/wizard` | User | Gera wizard bash interativo para procedimentos manuais (setup third-party, migrações, secrets). |
| `/loop-me` | User | Grill sobre specs de workflows que você quer construir neste workspace (`workflows/*.md`). |

---

## Referência rápida — todos os 41 comandos

| Comando | Resumo em uma linha |
|---------|---------------------|
| `/ask-matt` | Qual skill usar? Roteador de fluxos. |
| `/setup-matt-pocock-skills` | Setup 1×: tracker, labels, docs. |
| `/grill-with-docs` | Entrevista + docs de domínio (com repo). |
| `/grill-me` | Entrevista sem salvar (sem repo). |
| `/batch-grill-me` | Entrevista em lote, rodada por rodada. |
| `/grilling` | Primitiva de entrevista (usada por outras). |
| `/to-spec` | Conversa → spec no tracker. |
| `/to-tickets` | Spec/plano → tickets com blockers. |
| `/implement` | Construir spec/tickets (TDD + review). |
| `/tdd` | Red-green-refactor, test-first. |
| `/code-review` | Review Standards + Spec. |
| `/prototype` | Código descartável para validar design. |
| `/diagnosing-bugs` | Diagnóstico disciplinado de bugs difíceis. |
| `/resolving-merge-conflicts` | Resolver merge/rebase hunk a hunk. |
| `/triage` | Triagem de issues externas → agent-ready. |
| `/qa` | QA conversacional → issues no GitHub. |
| `/wayfinder` | Mapa de decisões para esforços enormes. |
| `/improve-codebase-architecture` | Relatório HTML + grill de deep modules. |
| `/codebase-design` | Vocabulário de módulos profundos. |
| `/domain-modeling` | Glossário, termos, ADRs. |
| `/design-an-interface` | Várias interfaces paralelas. |
| `/ubiquitous-language` | Glossário DDD da conversa. |
| `/request-refactor-plan` | Plano de refactor → issue GitHub. |
| `/research` | Pesquisa em fontes primárias → Markdown. |
| `/handoff` | Compactar conversa → nova sessão. |
| `/claude-handoff` | Handoff → agente background. |
| `/teach` | Ensinar conceito em múltiplas sessões. |
| `/writing-great-skills` | Como escrever skills bem. |
| `/edit-article` | Editar e melhorar artigos. |
| `/writing-fragments` | Minerar fragmentos de escrita. |
| `/writing-beats` | Montar beats narrativos. |
| `/writing-shape` | Moldar artigo parágrafo a parágrafo. |
| `/to-questionnaire` | Decisão → questionário para terceiros. |
| `/obsidian-vault` | Gerenciar notas Obsidian. |
| `/setup-pre-commit` | Husky + lint-staged + hooks. |
| `/setup-ts-deep-modules` | Deep modules com dependency-cruiser. |
| `/git-guardrails-claude-code` | Bloquear git destrutivo no Claude Code. |
| `/migrate-to-shoehorn` | Migrar `as` → shoehorn nos testes. |
| `/scaffold-exercises` | Scaffold de exercícios de curso. |
| `/wizard` | Wizard bash para setup manual. |
| `/loop-me` | Grill de specs de workflows. |

---

## On-ramps (entradas alternativas)

Situações que **não** começam com uma ideia nova, mas convergem no fluxo principal:

| Situação | Comando | Depois |
|----------|---------|--------|
| Issues/bugs acumulando | `/triage` | `/implement` |
| Algo quebrado e difícil | `/diagnosing-bugs` | `/improve-codebase-architecture` (se faltar seam) |
| Projeto/feature gigante e nebuloso | `/wayfinder` | `/to-spec` → `/to-tickets` → `/implement` |
| Manutenção do codebase | `/improve-codebase-architecture` | `/grill-with-docs` (com candidato escolhido) |

---

## Configuração deste repo

Após `/setup-matt-pocock-skills`, a config fica em:

| Arquivo | Conteúdo |
|---------|----------|
| `AGENTS.md` | Resumo para o agente |
| `docs/agents/issue-tracker.md` | Onde ficam issues/specs (`.scratch/`) |
| `docs/agents/triage-labels.md` | Labels de triagem |
| `docs/agents/domain.md` | Layout de `CONTEXT.md` e ADRs |
| `.scratch/` | Issues e specs em markdown local |

---

## Dicas

- **Não lembra o comando?** → `/ask-matt`
- **Feature nova com repo existente?** → `/grill-with-docs`
- **Já tem spec/ticket pronto?** → `/implement`
- **Só quer TDD num comportamento?** → `/tdd` (ou mencione "test-first")
- **Sessão ficando longa?** → `/handoff` e continue em thread nova
- **Atualizar skills:** `npx skills update`

---

Fonte: [mattpocock/skills](https://github.com/mattpocock/skills) · Instalado com `npx skills@latest add mattpocock/skills`
