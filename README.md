# Atividade 5 — Princípio da Inversão de Dependência (DIP)

> **Unidade Curricular:** Arquitetura de Sistemas  
> **Professor:** Lucas Santos  
> **Tema:** Refatoração para o Princípio da Inversão de Dependência (DIP)

---

## 📖 Contexto

A **TechStore** possui um serviço de recuperação de senha que, originalmente, enviava o link de redefinição exclusivamente via e-mail, com dependência direta de uma classe concreta `ServicoEmail` instanciada dentro do próprio `RecuperadorDeSenha`.

---

## ❌ O Problema: Violação do DIP

A classe de alto nível `RecuperadorDeSenha` criava internamente um objeto `ServicoEmail`, ou seja, ela **sabia exatamente como** a mensagem seria entregue. Isso gerava dois problemas sérios:

- **Acoplamento rígido:** qualquer mudança no canal de entrega (SMS, WhatsApp, etc.) exigia modificar diretamente o código do `RecuperadorDeSenha`, que é uma regra de negócio e não deveria se preocupar com detalhes de infraestrutura.
- **Dificuldade de teste:** era impossível testar o `RecuperadorDeSenha` de forma isolada, pois ele sempre tentava enviar um e-mail real ao ser instanciado.

> *"Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações."*  
> — Robert C. Martin

---

## ✅ A Solução: Inversão de Dependência

Foi criada a interface `Comunicador`, que define o **contrato** de entrega de mensagens. A partir daí, tanto `ServicoEmail` quanto `ServicoSMS` implementam esse contrato, e o `RecuperadorDeSenha` passa a depender **apenas da abstração**, recebendo o comunicador via construtor (Injeção de Dependência).

O `RecuperadorDeSenha` agora só sabe que **existe uma forma de entregar a mensagem** — ele não sabe nem precisa saber se é por e-mail, SMS ou qualquer outro canal.

| Classe/Interface | Papel |
|---|---|
| `Comunicador` | Interface (abstração/contrato) |
| `ServicoEmail` | Implementação via e-mail SMTP |
| `ServicoSMS` | Implementação via SMS |
| `RecuperadorDeSenha` | Regra de negócio — depende só da abstração |

---

## 🔄 Demonstração da Flexibilidade

O ponto central do DIP é que o **código interno do `RecuperadorDeSenha` não muda** para suportar novos canais. A troca acontece apenas na hora de instanciar o objeto, no `Main`:

```java
// Recuperação por E-mail
RecuperadorDeSenha recuperadorEmail = new RecuperadorDeSenha(new ServicoEmail());
recuperadorEmail.recuperar("gabrielli@gmail.com");

// Recuperação por SMS — mesmo RecuperadorDeSenha, canal diferente
RecuperadorDeSenha recuperadorSMS = new RecuperadorDeSenha(new ServicoSMS());
recuperadorSMS.recuperar("gabrielli@gmail.com");
```

Nenhuma linha dentro de `RecuperadorDeSenha` foi alterada para suportar o SMS. A lógica de negócio permanece intacta — apenas o detalhe de entrega é trocado por fora.

---

## 🗂️ Estrutura do Projeto

```
DependencyInversion/
└── src/
    ├── communication/
    │   ├── Comunicador.java
    │   ├── ServicoEmail.java
    │   └── ServicoSMS.java
    ├── service/
    │   └── RecuperadorDeSenha.java
    └── Main.java
```

---

## ▶️ Como Executar

1. Clone o repositório
2. Abra o projeto em uma IDE Java (ex: IntelliJ IDEA)
3. Execute a classe `Main.java`

### Saída esperada:

```
===== RECUPERAÇÃO POR EMAIL =====
ENVIANDO EMAIL SMTP: Clique no link para redefinir sua senha: http://techstore.com/reset?token=123

===== RECUPERAÇÃO POR SMS =====
ENVIANDO SMS: Clique no link para redefinir sua senha: http://techstore.com/reset?token=123
```

---

## 📚 Conceito Aplicado

O **Princípio da Inversão de Dependência (DIP)** faz parte dos princípios **SOLID** e tem dois pontos centrais: módulos de alto nível (regras de negócio) não devem depender de módulos de baixo nível (detalhes de implementação), e ambos devem depender de abstrações. A solução adotada usa **Injeção de Dependência via construtor**, desacoplando completamente a lógica de recuperação de senha do canal de entrega utilizado.
