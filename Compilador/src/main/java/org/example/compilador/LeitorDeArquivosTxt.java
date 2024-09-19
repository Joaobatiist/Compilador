package org.example.compilador;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeitorDeArquivosTxt {
    private PushbackInputStream pushbackInputStream;

    public LeitorDeArquivosTxt(String arquivo) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(arquivo));
            pushbackInputStream = new PushbackInputStream(fileInputStream, 1); // O tamanho do buffer é 1
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorDeArquivosTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int lerProximoCaractere() {
        try {
            int ret = pushbackInputStream.read(); // Lê o próximo caractere
            System.out.print((char) ret); // Exibe o caractere lido
            return ret;
        } catch (IOException ex) {
            Logger.getLogger(LeitorDeArquivosTxt.class.getName()).log(Level.SEVERE, null, ex);
            return -1; // Retorna -1 em caso de erro ou fim do arquivo
        }
    }

    public void retroceder(int caractere) {
        try {
            pushbackInputStream.unread(caractere); // Retrocede o último caractere lido
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            pushbackInputStream.close(); // Fecha o fluxo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
