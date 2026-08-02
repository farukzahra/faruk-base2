# Product CRUD (exemplo Java)

API REST de cadastro de produtos — template **Spring Boot + Docker** do harness; contrato HTTP de referência para o irmão `examples/fastapi-crud`.

## Language

**Product**:
Materialização de um produto do catálogo — registro persistido no banco com os dados comerciais do item.
_Avoid_: Produto *(use Product no código e nas rotas)*

**stockQuantity**:
Quantidade de unidades disponíveis em estoque para aquele Product.
_Avoid_: estoque, quantity, qtd

**price**:
Preço unitário de venda do Product, em moeda decimal (mínimo 0,01 na validação atual).
_Avoid_: valor, preço *(em identificadores — use price)*

**ProductRequest**:
Payload de entrada para criar ou atualizar um Product (name, description, price, stockQuantity).
_Avoid_: DTO, body, formulário

**ProductResponse**:
Representação de um Product retornada pela API, incluindo id e createdAt gerados pelo sistema.
_Avoid_: entidade, model *(Product é a entidade JPA; ProductResponse é a projeção de saída)*

## Convenções deste exemplo

- Documentação (este arquivo, ADRs, README): **pt-BR**
- Código-fonte (classes, rotas, variáveis, testes, mensagens HTTP, commits): **inglês**
- Contrato HTTP compartilhado com `fastapi-crud`: `/api/products`, erros `{ "error": "..." }`, health em `/actuator/health`
- Listagem: `GET /api/products` ordenado por **`createdAt` descendente** (mais recentes primeiro)
