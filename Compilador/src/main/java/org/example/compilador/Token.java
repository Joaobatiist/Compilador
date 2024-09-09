package org.example.compilador;

public class Token {
    // Estrutura de dados
    public TipodeToken nome;
    public String lexema;

    public Token(TipodeToken nome, String lexema) {
        this.nome = nome;
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return "<" + nome+ "," +lexema+ ">";
    }
}
