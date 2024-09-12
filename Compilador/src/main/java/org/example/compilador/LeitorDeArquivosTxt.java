package org.example.compilador;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeitorDeArquivosTxt {
    InputStream is;

    public LeitorDeArquivosTxt(String arquivo) {
        try {
            is = new FileInputStream(arquivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorDeArquivosTxt.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public int lerProximoCaractere(){

        try {
           int ret = is.read();
            System.out.println((char) ret);
            return  ret;
        } catch (IOException ex) {
            Logger.getLogger(LeitorDeArquivosTxt.class.getName()).log(Level.SEVERE,null,ex);
            return  -1;
        }


    }
}
