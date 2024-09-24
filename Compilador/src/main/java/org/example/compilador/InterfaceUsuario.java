package org.example.compilador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfaceUsuario {
    private LerToken ler;

    public InterfaceUsuario(String arquivo) {
        this.ler = new LerToken(arquivo);
        mostrarInterface();
    }

    private void mostrarInterface() {
        JFrame frame = new JFrame("Compilador Léxico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JTextArea conteudoArea = new JTextArea();
        JTextArea tokensArea = new JTextArea();
        conteudoArea.setEditable(false);
        tokensArea.setEditable(false);

        // Lê o conteúdo do arquivo
        StringBuilder conteudo = new StringBuilder();
        int caractere;
        while ((caractere = ler.ldat.lerProximoCaractere()) != -1) {
            conteudo.append((char) caractere);
        }
        conteudoArea.setText(conteudo.toString());

        // Gera os tokens
        List<Token> tokens = ler.gerarTokens(); // Gera os tokens
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append("tipo: ").append(token.getNome())
                    .append(", Lexema: ").append(token.getLexema()).append("\n");
        }
        tokensArea.setText(sb.toString());

        frame.add(new JScrollPane(conteudoArea), BorderLayout.CENTER);
        frame.add(new JScrollPane(tokensArea), BorderLayout.EAST);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Erro: Por favor, forneça o nome do arquivo como argumento.");
            return;
        }

        String arquivo = args[0];
        new InterfaceUsuario(arquivo);
    }
}
