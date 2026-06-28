Calculadora de IMC — em Java

Sistema de calculadora para o cálculo de IMC, desenvolvido em Java, com o objetivo de colocar em prática os principais conceitos de Orientação a Objetos.

# Conceitos OOP demonstrados

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

# Pré-requisitos

- **Java 17** ou superior (testado com Java 21)
- **Maven 3.8+** — para build e execução simplificada

### Verificar instalação

```bash
java -version
mvn -version
```

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
