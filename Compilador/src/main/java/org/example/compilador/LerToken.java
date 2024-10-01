package org.example.compilador;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            if (c == ' ' || c == '\n' ) continue;
            if (c == '/') {
                int nextChar = ldat.lerProximoCaractere();
                if (nextChar == '/') {

                    while ((nextChar = ldat.lerProximoCaractere()) != -1 && nextChar != '\n') {

                    }
                    continue;
                } else if (nextChar == '*') {

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

                    ldat.retroceder(nextChar);
                }
            }

            if (c == '"') {
                while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
                    c = (char) caractereLido;
                    if (c == '"') break;
                    if (c == '\\') {
                        ldat.lerProximoCaractere();
                    }
                }
                continue; // Retorna ao início do loop
            }



            if (Character.isLetter(c) || c == '_') {
                palavra.append(c);
                while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
                    c = (char) caractereLido;
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        palavra.append(c);
                    } else {
                        ldat.retroceder(caractereLido); // Método para retroceder um caractere
                        break; // Sair do loop
                    }
                }
                return verificarIdentificador(palavra.toString());

            }
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

            // Adicione mais operadores e lógicas conforme necessário
        }
        return null;
    }
    private int contadorIdentificadores = 0;
    private Map<String, Integer> identificadores = new HashMap<>();

    private Token verificarIdentificador(String valor) {

        String regex = "^[a-zA-Z_][a-zA-Z0-9_]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valor);

        if (matcher.matches()) {
            if (isPalavraReservada(valor)) {
                return new Token(TipodeToken.PALAVRA_RESERVADA, valor);
            }


            if (!identificadores.containsKey(valor)) {
                contadorIdentificadores++;
                identificadores.put(valor, contadorIdentificadores);
            }

            int id = identificadores.get(valor);
            System.out.print(" id " + id);
            return new Token(TipodeToken.IDENTIFICADOR, valor);
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
                "throws", "transient", "try", "void", "volatile", "while", "String"
        };


        for (String p : palavrasReservadas) {
            if (palavra.equals(p)) {
                return true;
            }
        }
        return false;
    }
    private Token verificarNumero(String valor){
        String numberRegex = "^[0-9]*$";
        Pattern paternn = Pattern.compile(numberRegex);
        Matcher matcher2 = paternn.matcher(valor);

        if (matcher2.matches()){
            return new Token(TipodeToken.NUMB, valor);
        }

        return null;

    }


}
