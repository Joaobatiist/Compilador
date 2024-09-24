package org.example.compilador;

public class Token {
    // Estrutura de dados
    public TipodeToken tipo;
    public String lexema;

    public Token(TipodeToken tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
    }

    public TipodeToken getNome() {
        return tipo;
    }

    public String getLexema() {
        return lexema;
    }

    @Override
    public String toString() {
        return " Token < " + "tipo=" + tipo + ", lexema='" + lexema + '\'' + '>';
    }
}
