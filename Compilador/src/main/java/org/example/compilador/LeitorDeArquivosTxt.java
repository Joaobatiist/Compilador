package org.example.compilador;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
public class LeitorDeArquivosTxt {
    InputStream is;

    public LeitorDeArquivosTxt(String arquivo) {
        try {
            is = new FileInputStream(arquivo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public int lerProximoCaractere(){

        try {
           int ret = is.read();
            System.out.println((char) ret);
            return  ret;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}
