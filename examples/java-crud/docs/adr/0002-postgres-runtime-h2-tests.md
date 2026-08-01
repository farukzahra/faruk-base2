# PostgreSQL no runtime, H2 nos testes

A aplicação em Docker persiste Products em PostgreSQL 17. Os testes de integração rodam com H2 em memória (profile `test`) para manter o build rápido e sem Docker durante `mvn test`. Quem clonar como template deve saber que o dialect e alguns comportamentos SQL podem divergir entre H2 e PostgreSQL — aceitável para demo; revisar antes de produção.
