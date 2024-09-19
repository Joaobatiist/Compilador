package org.example.compilador;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LerToken {
    LeitorDeArquivosTxt ldat;

    public LerToken(String arquivo) {
        ldat = new LeitorDeArquivosTxt(arquivo);
    }

    public Token proximoToken() {
        int caractereLido;
        StringBuilder palavra = new StringBuilder();

        while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
            char c = (char) caractereLido;

            // Ignorar espaços em branco
            if (Character.isWhitespace(c)) continue;

            // Identificando identificadores
            if (Character.isLetter(c) || c == '_') {
                palavra.append(c);
                // Continuar a acumular caracteres enquanto forem válidos
                while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
                    c = (char) caractereLido;
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        palavra.append(c);
                    } else {
                        ldat.retroceder(caractereLido); // método para retroceder um caractere
                        break; // sair do loop
                    }
                }
                return verificarIdentificador(palavra.toString());
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
                case '*': return new Token(TipodeToken.OPERADOR_ARITIMETICO_MULTIPLICACAO, "*");
                case '+': return new Token(TipodeToken.OPERADOR_ARITIMETICO_SOMA, "+");
                case '-': return new Token(TipodeToken.OPERADOR_ARITMETICO_SUBTRACAO, "-");
                case '/': return new Token(TipodeToken.OPERADOR_ARITMETICO_DIVISAO, "/");
                case '%': return new Token(TipodeToken.OPERADOR_ARITIMETICO_RESTO, "%");
                case '=': return new Token(TipodeToken.IGUAL, "=");
                case '<': return new Token(TipodeToken.MENORQUE, "<");
                case '>': return new Token(TipodeToken.MAIORQUE, ">");
            }

            // Adicione mais operadores e lógicas conforme necessário
        }
        return null; // Se não há mais tokens
    }

    private Token verificarIdentificador(String valor) {
        // Regex para identificadores válidos
        String regex = "^[a-zA-Z_][a-zA-Z0-9_]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valor);

        if (matcher.matches()) {
            // Verifica se é uma palavra reservada
            if (isPalavraReservada(valor)) {
                return new Token(TipodeToken.PALAVRA_RESERVADA, valor);
            }
            return new Token(TipodeToken.IDENTIFICADOR, valor);
        }
        return null; // Ou trate de outra forma se necessário
    }

    private boolean isPalavraReservada(String palavra) {
        // Verifica se a palavra é uma palavra reservada
        // Coloque aqui a lógica para verificar as palavras reservadas
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
