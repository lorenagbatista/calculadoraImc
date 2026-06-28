# Calculadora de IMC — CLI em Java

Sistema de linha de comando para cálculo e classificação de Índice de Massa Corporal (IMC), desenvolvido em Java, demonstrando todos os pilares da Orientação a Objetos.

---

## Funcionalidades

| Opção | Descrição |
|-------|-----------|
| 1 | Cadastrar Pessoa Comum (nome, idade, peso, altura) |
| 2 | Cadastrar Atleta (inclui modalidade e experiência) |
| 3 | Exibir Histórico de cálculos da sessão |
| 4 | Tabela de Classificação IMC (OMS + Atletas) |
| 5 | Demonstrar Polimorfismo (mesmo IMC, classificações distintas) |
| 6 | Sobre o sistema |
| 7 | Ajuda |
| 0 | Sair |

---

## Conceitos OOP demonstrados

| Conceito | Onde |
|----------|------|
| **Interface** | `Calculavel` — contrato com `calcularIMC()`, `classificarIMC()`, `exibirRelatorio()` |
| **Classe Abstrata** | `Pessoa` — método concreto `calcularIMC()` + método abstrato `getTipoPessoa()` |
| **Herança** | `PessoaComum extends Pessoa`, `Atleta extends Pessoa` |
| **Polimorfismo** | `Atleta` sobrescreve `classificarIMC()` com tabela ajustada para esportistas |
| **Encapsulamento** | Atributos `private` com getters/setters com validação em `Pessoa` |
| **Composição** | `IMCService` **tem-um** `HistoricoIMC`; `HistoricoIMC` **tem-vários** `RegistroIMC`; `RegistroIMC` **tem-uma** `Pessoa` |
| **Exceção personalizada** | `DadosInvalidosException` com campo e mensagem; capturada em `MenuCLI` e `InputService` |
| **Recursão** | `HistoricoIMC.exibirRecursivo(int indice)` imprime registros recursivamente |
| **Controle de fluxo** | `switch` no menu, `while` no loop principal, `if/else if` nas classificações |

---

## Pré-requisitos

- **Java 17** ou superior (testado com Java 21)
- **Maven 3.8+** — para build e execução simplificada

### Verificar instalação

```bash
java -version
mvn -version
```

---

## Compilação e Execução

### Usando Maven (recomendado)

```bash
# 1. Clone ou extraia o projeto
cd calculadora-imc

# 2. Compilar
mvn compile

# 3. Executar
mvn exec:java

# 4. Compilar, testar e gerar JAR executável (tudo de uma vez)
mvn clean package

# 5. Executar o JAR gerado
java -jar target/calculadora-imc.jar
```

### Usando apenas javac/java (sem Maven)

```bash
# 1. Criar diretório de saída
mkdir -p out

# 2. Compilar todos os arquivos .java
find src/main/java -name "*.java" | xargs javac -encoding UTF-8 -d out

# 3. Executar
java -cp out com.imc.Main
```

### Executar testes

```bash
mvn test
```

---

## Estrutura do Projeto

```
calculadora-imc/
├── pom.xml                          # Gerenciamento de dependências Maven
├── README.md
└── src/
    ├── main/java/com/imc/
    │   ├── Main.java                # Ponto de entrada
    │   ├── exception/
    │   │   └── DadosInvalidosException.java   # Exceção personalizada
    │   ├── model/
    │   │   ├── Calculavel.java      # Interface
    │   │   ├── Pessoa.java          # Classe abstrata (superclasse)
    │   │   ├── PessoaComum.java     # Subclasse concreta
    │   │   └── Atleta.java          # Subclasse com polimorfismo
    │   ├── history/
    │   │   ├── RegistroIMC.java     # Registro individual (composição)
    │   │   └── HistoricoIMC.java    # Gerencia o histórico + recursão
    │   ├── service/
    │   │   ├── IMCService.java      # Lógica de negócio
    │   │   └── InputService.java    # Leitura segura de entradas
    │   └── ui/
    │       └── MenuCLI.java         # Interface do menu CLI
    └── test/java/com/imc/
        └── IMCTest.java             # Testes unitários (JUnit 5)
```

---

## Fórmula do IMC

```
IMC = Peso (kg) ÷ Altura² (m)

Exemplo: 70 kg ÷ 1,75² = 70 ÷ 3,0625 ≈ 22,86
```

### Tabela de Classificação — Pessoa Comum (OMS)

| IMC | Classificação |
|-----|--------------|
| < 18,5 | Abaixo do peso |
| 18,5 – 24,9 | Normal |
| 25,0 – 29,9 | Sobrepeso |
| 30,0 – 34,9 | Obesidade Grau I |
| 35,0 – 39,9 | Obesidade Grau II |
| ≥ 40,0 | Obesidade Grau III (Mórbida) |

### Tabela de Classificação — Atleta (ajustada)

| IMC | Classificação |
|-----|--------------|
| < 17,0 | Abaixo do peso |
| 17,0 – 24,8 | Normal |
| 24,9 – 29,8 | Sobrepeso muscular |
| 29,9 – 34,8 | Elevado — avaliar composição |
| ≥ 34,9 | Muito elevado |

---

## Tratamento de Erros

- Campos em branco → `DadosInvalidosException`
- Peso/altura fora do intervalo → `DadosInvalidosException`
- Entrada não numérica → mensagem de erro + nova tentativa (loop)
- Todas as entradas são validadas antes de criar os objetos

---

## Dependências (pom.xml)

| Dependência | Versão | Uso |
|-------------|--------|-----|
| JUnit Jupiter | 5.10.2 | Testes unitários |
| maven-shade-plugin | 3.5.2 | JAR executável |
| exec-maven-plugin | 3.2.0 | `mvn exec:java` |
| maven-surefire-plugin | 3.2.5 | Rodar JUnit 5 |

---

## Exemplo de Sessão

```
╔══════════════════════════════════════════╗
║       CALCULADORA DE IMC — MENU          ║
║      Registros na sessão: 0              ║
╠══════════════════════════════════════════╣
║  1. Cadastrar Pessoa Comum               ║
║  2. Cadastrar Atleta                     ║
...

  Escolha uma opção: 1

─── Cadastrar Pessoa Comum ───────────────────
  Nome   : Ana Silva
  Idade  : 28
  Peso (kg)   : 65,5
  Altura (m)  : 1,68

╔══════════════════════════════════════════╗
║  Relatório IMC — Pessoa Comum            ║
╠══════════════════════════════════════════╣
║  Nome   : Ana Silva                      ║
║  IMC    : 23,21                          ║
║  Class. : Normal                         ║
╚══════════════════════════════════════════╝
```
