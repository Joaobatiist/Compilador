package org.example.compilador;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LerToken {
    LeitorDeArquivosTxt ldat;

    public LerToken(String arquivo) {
        ldat = new LeitorDeArquivosTxt(arquivo);
    }

    public List<Token> gerarTokens() {
        List<Token> tokens = new ArrayList<>();
        Token token;
        // Chama proximoToken até que não haja mais tokens
        while ((token = proximoToken()) != null) {
            tokens.add(token);
        }
        return tokens;
    }

    public Token proximoToken() {
        int caractereLido;
        StringBuilder palavra = new StringBuilder();

        while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
            char c = (char) caractereLido;

            // Ignorar espaços e quebras de linha
            if (c == ' ' || c == '\n') continue;


            if (c == '\\') {
            int nextChar = ldat.lerProximoCaractere();
            if (nextChar == 'n'){
                System.out.println("\\n");
            }
        }
            // Comentários
            if (c == '/') {
                int nextChar = ldat.lerProximoCaractere();
                if (nextChar == '/') {
                    // Comentário de linha
                    while ((nextChar = ldat.lerProximoCaractere()) != -1 && nextChar != '\n') {
                    }
                    continue;
                } else if (nextChar == '*') {
                    // Comentário de bloco
                    while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
                        if (caractereLido == '*') {
                            int nextBlockChar = ldat.lerProximoCaractere();
                            if (nextBlockChar == '/') {
                                break;
                            } else {
                                ldat.retroceder(nextBlockChar);
                            }
                        }
                    }
                    continue;
                } else {
                    // Retroceder o caractere não esperado
                    ldat.retroceder(nextChar);
                }
            }

            // Strings entre aspas
            if (c == '"') {
                while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
                    c = (char) caractereLido;
                    if (c == '"') break;  // Fim da string
                    if (c == '\\') {
                        ldat.lerProximoCaractere(); // Ignorar escape
                    }
                }
                continue;  // Retorna ao início do loop
            }

            // Identificadores e palavras reservadas
            if (Character.isLetter(c) || c == '_') {
                palavra.append(c);
                while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
                    c = (char) caractereLido;
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        palavra.append(c);
                    } else {
                        // Se encontrar ponto, retroceda
                        if (c == '.') {
                            ldat.retroceder(caractereLido);
                            break;
                        }
                        ldat.retroceder(caractereLido); // Retrocede caractere não válido
                        break;
                    }
                }
                return verificarIdentificador(palavra.toString());
            }

            // Números
            if (Character.isDigit(c)) {
                palavra.append(c);
                while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
                    c = (char) caractereLido;
                    if (Character.isDigit(c)) {
                        palavra.append(c);
                    } else {
                        ldat.retroceder(caractereLido);
                        break;
                    }
                }
                return verificarNumero(palavra.toString());
            }

            // Identificando delimitadores
            switch (c) {
                case '(': return new Token(TipodeToken.PARENTESE_ABRINDO, "(");
                case ')': return new Token(TipodeToken.PARENTESE_FECHANDO, ")");
                case '{': return new Token(TipodeToken.CHAVES_ABRINDO, "{");
                case '}': return new Token(TipodeToken.CHAVES_FECHANDO, "}");
                case '[': return new Token(TipodeToken.CHAVES_ABRINDO, "[");
                case ']': return new Token(TipodeToken.CHAVES_FECHANDO, "]");
                case ';': return new Token(TipodeToken.PONTO_VIRGULA, ";");
                case ':': return new Token(TipodeToken.DOIS_PONTOS, ":");
                case ',': return new Token(TipodeToken.VIRGULA, ",");
                case '.': return new Token(TipodeToken.PONTO, ".");
            }

            // Identificando operadores aritméticos
            switch (c) {
                case '+':
                    int nextChar = ldat.lerProximoCaractere();
                    if (nextChar == '+') {
                        return new Token(TipodeToken.OPERADOR_ARITIMETICO_INCREMENTO, "++");
                    } else {
                        ldat.retroceder(nextChar);
                        return new Token(TipodeToken.OPERADOR_ARITIMETICO_SOMA, "+");
                    }
                case '*': return new Token(TipodeToken.OPERADOR_ARITIMETICO_MULTIPLICACAO, "*");
                case '-':
                    nextChar = ldat.lerProximoCaractere();
                    if (nextChar == '-') {
                        return new Token(TipodeToken.OPERADOR_ARITIMETICO_DECREMENTO, "--");
                    } else {
                        ldat.retroceder(nextChar);
                        return new Token(TipodeToken.OPERADOR_ARITMETICO_SUBTRACAO, "-");
                    }
                case '/': return new Token(TipodeToken.OPERADOR_ARITMETICO_DIVISAO, "/");
                case '%': return new Token(TipodeToken.OPERADOR_ARITIMETICO_RESTO, "%");
                case '=': return new Token(TipodeToken.IGUAL, "=");
                case '<': return new Token(TipodeToken.MENORQUE, "<");
                case '>': return new Token(TipodeToken.MAIORQUE, ">");
            }
        }
        return null; // Se não há mais tokens
    }

    private int contadorIdentificadores = 0;
    private final Map<String, Integer> identificadoresMap = new HashMap<>();

    private Token verificarIdentificador(String valor) {
        // Regex para identificadores válidos
        String regex = "^[a-zA-Z_][a-zA-Z0-9_]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valor);

        if (matcher.matches()) {
            if (isPalavraReservada(valor)) {
                return new Token(TipodeToken.PALAVRA_RESERVADA, valor);
            }

            // Verifica se o identificador já foi encontrado
            if (!identificadoresMap.containsKey(valor)) {
                // Se não foi encontrado, adiciona ao mapa e incrementa o contador
                contadorIdentificadores++;
                identificadoresMap.put(valor, contadorIdentificadores);
            }

            int idAtual = identificadoresMap.get(valor);
            System.out.print(" id " + idAtual + " {TS}");
            return new Token(TipodeToken.IDENTIFICADOR, valor);
        }

        return null;
    }

    public void imprimirIdentificadores() {
        System.out.println("\nIdentificadores na Tabela de Símbolos (ordenados por ID):");
        identificadoresMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()) // Ordena pelo ID (valor)
                .forEach(entry -> System.out.println("Identificador: " + entry.getKey() + ", ID: " + entry.getValue()));
    }

    private Token verificarNumero(String valor){
        String numberRegex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(valor);

        if (matcher.matches()){
            return new Token(TipodeToken.NUMB, valor);
        }
        return null;
    }

    private boolean isPalavraReservada(String palavra) {
        String[] palavrasReservadas = {
                "abstract", "assert", "boolean", "break", "case", "byte",
                "catch", "char", "class", "const", "continue", "default",
                "do", "double", "else", "enum", "extends", "final",
                "finally", "float", "for", "goto", "if", "implements",
                "import", "instanceof", "int", "interface", "long",
                "native", "new", "null", "package", "private", "protected",
                "public", "return", "short", "static", "strictfp",
                "super", "switch", "synchronized", "this", "throw",
                "throws", "transient", "try", "void", "volatile", "while"
        };

        for (String p : palavrasReservadas) {
            if (palavra.equals(p)) {
                return true;
            }
        }
        return false;
    }
}
