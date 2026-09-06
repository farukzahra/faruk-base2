# Agent Instructions

## Agent skills

### Issue tracker

Issues live as markdown files under `.scratch/`. See `docs/agents/issue-tracker.md`.

### Triage labels

Five canonical roles with default label strings (`needs-triage`, `needs-info`, `ready-for-agent`, `ready-for-human`, `wontfix`). See `docs/agents/triage-labels.md`.

### Domain docs

Single-context layout — one `CONTEXT.md` and `docs/adr/` at the repo root. See `docs/agents/domain.md`.

### Secrets and VPS

Central vault (not in this repo): **`C:\repo\secrets\`**. See `docs/agents/secrets.md`.

## Superpowers workflow

| Phase | Skill | Output |
|-------|-------|--------|
| Design | `brainstorming` | Approved design â†’ `docs/superpowers/specs/YYYY-MM-DD-*-design.md` |
| Plan | `writing-plans` | `docs/superpowers/plans/YYYY-MM-DD-*.md` |
| Build | stack skills + `tdd` | Code + tests |
| Verify | `verification-before-completion` | Evidence before "done" |
| Debug | `systematic-debugging` | Root cause before fix |
| Ship | `/commit-push` | `semantic-version` + `caveman-commit` + push + CI |

**Gates:** no feature code before approved spec; no "done" without verification; version bump only on `/commit-push`.

Invoke `/init` to (re)bootstrap skills and folders.
