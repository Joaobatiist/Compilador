package org.example.compilador;

public class LerToken {
    LeitorDeArquivosTxt ldat;

    public LerToken(String arquivo) {
        ldat = new LeitorDeArquivosTxt(arquivo);
    }

    public Token proximoToken() {
        int caractereLido = -1;
        while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
            char c = (char) caractereLido;
            if (c == ' ' || c == '\n') continue;

            if (c == 'a') {
                StringBuilder palavra = new StringBuilder();
                palavra.append(c);


                while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
                    c = (char) caractereLido;
                    if (Character.isWhitespace(c)) {
                        break;
                    }
                    palavra.append(c);
                }
                // identificadores

                //DELIMITADORES
             if (c == '(') {
                return new Token(TipodeToken.PARENTESE_ABRINDO, "(");
            }
             if (c == ')') {
                return new Token(TipodeToken.PARENTESE_FECHANDO, ")");
            }
             if (c == '{') {
                return new Token(TipodeToken.CHAVES_ABRINDO, "{");
            }
             if (c == '}') {
                return new Token(TipodeToken.CHAVES_FECHANDO, "}");
            }
             if (c == '[') {
                return new Token(TipodeToken.CHAVES_ABRINDO, "[");
            }
             if (c == ']') {
                return new Token(TipodeToken.CHAVES_FECHANDO, "]");
            }
              if (c == ';') {
                 return new Token(TipodeToken.PONTO_VIRGULA, ";");
             }
                // Palvra reservada
                if (palavra.toString().equals("abstract")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "abstract");
                }
                if (palavra.toString().equals("assert")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "assert");
                }
                if (palavra.toString().equals("boolean")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "boolean");
                }
                if (palavra.toString().equals("break")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "break");
                }
                if (palavra.toString().equals("case")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "case");
                }
                if (palavra.toString().equals("byte")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "byte");
                }
                if (palavra.toString().equals("catch")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "catch");
                }
                if (palavra.toString().equals("char")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "char");
                }
                if (palavra.toString().equals("class")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "class");
                }
                if (palavra.toString().equals("const")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "const");
                }
                if (palavra.toString().equals("continue")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "continue");
                }
                if (palavra.toString().equals("default")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "default");
                }
                if (palavra.toString().equals("do")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "do");
                }
                if (palavra.toString().equals("double")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "double");
                }
                if (palavra.toString().equals("else")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "else");
                }
                if (palavra.toString().equals("enum")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "enum");
                }
                if (palavra.toString().equals("extends")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "extends");
                }
                if (palavra.toString().equals("final")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "final");
                }
                if (palavra.toString().equals("finally")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "finally");
                }
                if (palavra.toString().equals("float")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "float");
                }
                if (palavra.toString().equals("for")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "for");
                }
                if (palavra.toString().equals("goto")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "goto");
                }
                if (palavra.toString().equals("if")) {
                       return new Token(TipodeToken.PALAVRA_RESERVADA, "if");
                }
                if (palavra.toString().equals("implements")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "implements");
                }
                if (palavra.toString().equals("import")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "import");
                }
                if (palavra.toString().equals("instanceof")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "instanceof");
                }
                if (palavra.toString().equals("int")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "int");
                }
                if (palavra.toString().equals("interface")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "interface");
                }
                if (palavra.toString().equals("long")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "long");
                }
                if (palavra.toString().equals("native")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "native");
                }
                if (palavra.toString().equals("new")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "new");
                }
                if (palavra.toString().equals("null")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "null");
                }
                if (palavra.toString().equals("package")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "package");
                }
                if (palavra.toString().equals("private")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "private");
                }
                if (palavra.toString().equals("protected")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "protected");
                }
                if (palavra.toString().equals("public")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "public");
                }
                if (palavra.toString().equals("return")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "return");
                }
                if (palavra.toString().equals("short")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "short");
                }
                if (palavra.toString().equals("static")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "static");
                }
                if (palavra.toString().equals("strictfp")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "strict");
                }
                if (palavra.toString().equals("super")) {
                    return new Token(TipodeToken.PALAVRA_RESERVADA, "super");
                }
                if (palavra.toString().equals("switch")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"switch");
                }
                if (palavra.toString().equals("synchronized")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"synchronized");
                }
                if (palavra.toString().equals("this")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"this");
                }
                if (palavra.toString().equals("throw")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"throw");
                }
                if (palavra.toString().equals("throws")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"throws");
                }
                if (palavra.toString().equals("transient")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"transient");
                }
                if (palavra.toString().equals("try")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"try");
                }
                if (palavra.toString().equals("void")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"void");
                }
                if (palavra.toString().equals("volatile")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"volatile");
                }
                if (palavra.toString().equals("while")){
                    return new Token(TipodeToken.PALAVRA_RESERVADA,"while");
                }

                //Operadores aritmeticos
               if (c == '*') {
                    return new Token(TipodeToken.OPERADOR_ARITIMETICO_MULTIPLICACAO, "*");
                }
               if (c == '+') {
                    return new Token(TipodeToken.OPERADOR_ARITIMETICO_SOMA, "+");
                }
               if (c == '-') {
                    return new Token(TipodeToken.OPERADOR_ARITMETICO_SUBTRACAO, "-");
                }
               if (c == '/') {
                   return new Token(TipodeToken.OPERADOR_ARITMETICO_DIVISAO, "/");
               }
               if(c == '%'){
                   return new Token(TipodeToken.OPERADOR_ARITMETICO_RESTO, "%");
               }
               if (palavra.toString().equals("++")){
                   return new Token(TipodeToken.OPERADOR_ARITMETICO_INCREMENTO, "++");
               }
                if (palavra.toString().equals("--")){
                    return new Token(TipodeToken.OPERADOR_ARITMETICO_DECREMENTO, "--");
                }




                //OPERADORES RELACIONAIS, ATRIBUIÇÃO
                if (c == '='){
                    return new Token(TipodeToken.IGUAL, "=");
                }
                if (palavra.toString().equals("+=")){
                    return new Token(TipodeToken.SOMA_IGUAL, "+=");
                }
                if (palavra.toString().equals("-=")){
                    return new Token(TipodeToken.SOMA_IGUAL, "-=");
                }
                if (c == '<'){
                    return new Token(TipodeToken.MENORQUE, "<");
                }
                if (c == '>'){
                    return new Token(TipodeToken.MAIORQUE, ">");
                }
                if (palavra.toString().equals("<=")){
                    return new Token(TipodeToken.MENORQUE_IGUAL, "<=");
                }
                if (palavra.toString().equals(">=")){
                    return new Token(TipodeToken.MAIORQUE_IGUAL, ">=");
                }


                //Operadores Lógicos

                //OPERADORES BIT A BIT

                //OPERADOR TERNARIO

                if (c == ':') {
                    return new Token(TipodeToken.DOIS_PONTOS, ":");
                }
                if (c == '.') {
                    return new Token(TipodeToken.PONTO, ".");
                }
                if (c == ',') {
                    return new Token(TipodeToken.VIRGULA, ",");
                }
                }
                if (c == '<') {

                }
            }

        return null;
        }


    }



