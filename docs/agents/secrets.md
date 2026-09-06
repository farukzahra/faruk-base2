# Secrets e deploy (referência)

Este repo **não** guarda credenciais. Cofre central na máquina de dev:

**`C:\repo\secrets\`**

## O que consultar

| Necessidade | Arquivo |
|-------------|---------|
| VPS (host, paths, portas) | `C:\repo\secrets\vps\inventory.md` |
| Chave SSH deploy | `C:\repo\secrets\vps\ssh\` |
| `.env` de produção por app | `C:\repo\secrets\projects\<app>.env` |
| GitHub PAT (fallback) | `C:\repo\secrets\github\pat.txt` ou `gh auth` |
| Templates CI | `C:\repo\secrets\templates\` |

## Dev local (este repo)

- `examples/vue-crud/.env` — gitignored; copiar de `.env.example`.
- Backends de exemplo usam credenciais demo em `docker-compose.yml` (`products/products`).

## Agente (Cursor)

- GitHub: usar `gh` autenticado (keyring).
- VPS/SSH: ler paths acima; **não** colar valores no chat.
- Nunca commitar `.env`, PAT ou chaves privadas.

Ver também: [`C:\repo\secrets\README.md`](file:///C:/repo/secrets/README.md)
