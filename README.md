# 📘 README – SaaS de Gerenciamento de Barbearias  
**Tecnologias:** Spring Boot + Angular  
**Modelo:** Multi-Tenant SaaS (cada barbearia isolada, com suporte a filiais)

## 🧠 Visão Geral do Sistema
Este projeto é um **SaaS completo para gerenciamento de barbearias**, baseado nas tecnologias:

- **Backend:** Spring Boot 3+, Spring Security, JPA/Hibernate, PostgreSQL, Flyway, Redis  
- **Frontend:** Angular 17+ + Angular Material  
- **Segurança:** Keycloak (OIDC/JWT)  
- **Infra:** Docker, Docker Compose, CI/CD, WebSockets para tempo real  

A aplicação utiliza **arquitetura multi-tenant**, onde cada barbearia tem seus dados isolados. Uma barbearia pode ter **filiais**, mas informações como clientes, barbeiros, agenda e fidelidade **não são compartilhadas entre filiais**, mantendo a consistência e privacidade.

## 🧱 Arquitetura do Projeto
### 🔹 Backend
- API REST com camadas (Controllers, Services, Repositories)
- Multi-tenant por tenant_id
- Segurança via JWT
- Cache Redis
- Logging estruturado

### 🔹 Frontend
- Angular modularizado
- Guards por roles
- Layout responsivo
- Painel SuperAdmin e Painel Barbearia

### 🔹 Banco de Dados
- PostgreSQL
- Flyway para migrações
- Isolamento por tenant_id

---

## 🧩 Módulos e Funcionalidades

### 📌 1. Autenticação & Controle de Acesso
- Login, roles, 2FA
- Multi-tenant
- Gestão de usuários
- Reset de senha

### 📌 2. Barbearias & Filiais
- Cadastro
- Configurações
- Horário de funcionamento
- Limites por plano

### 📌 3. Barbeiros
- Dados pessoais
- Serviços disponíveis
- Comissões
- Agenda individual

### 📌 4. Clientes
- Cadastro
- Histórico por barbearia
- Fidelidade por barbearia

### 📌 5. Serviços & Preços
- Duração
- Preço
- Desconto por pacote (0-100%)

### 📌 6. Agenda & Agendamentos
- Visualização diária/semanal/mensal
- Drag & drop
- Notificações automáticas
- WebSockets
- Cancelamento e reagendamento

### 📌 7. Atendimento
- Check-in
- Check-out
- Serviços adicionais
- Geração de comissão

### 📌 8. Caixa (PDV)
- Registro de vendas
- Pagamento: PIX, cartão, dinheiro
- Relatório diário
- Impressão de recibos

### 📌 9. Fidelidade
- Conversão de pontos
- Resgate
- Histórico

### 📌 11. Relatórios
- Faturamento
- Ticket médio
- Produtividade do barbeiro
- Relatórios financeiros
- Movimentação de estoque

### 📌 12. Painel SuperAdmin
- Gestão de barbearias
- Planos e limites
- Estatísticas MRR, churn, retenção
- Auditoria

### 📌 13. Sistema de Assinaturas
- Pagamento recorrente
- Planos por nível
- Limites de uso

### 📌 14. Integrações Futuras
- WhatsApp API
- Gateway de notificação
- API pública
- Pagamentos integrados

---

## 🏗️ Estrutura do Projeto
```
backend/
  src/
    main/
      java/com/saas/barbearia/
        config/
        tenant/
        security/
        modules/
      resources/

frontend/
  src/
    app/
      core/
      shared/
      modules/
docker/
README.md
```

---

## 🚀 Roadmap
### Fase 1 — Base
- Autenticação
- Multi-tenant
- Angular + layout base

### Fase 2 — Módulos principais
- Barbearias
- Barbeiros
- Clientes
- Agenda

### Fase 3 — Financeiro
- PDV
- Caixa
- Relatórios

### Fase 4 — SuperAdmin

### Fase 5 — Integrações

---

## 📄 Licença
MIT ou comercial para SaaS.
