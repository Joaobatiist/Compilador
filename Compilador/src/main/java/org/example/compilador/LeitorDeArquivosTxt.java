package org.example.compilador;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeitorDeArquivosTxt {
    InputStream is;

    public LeitorDeArquivosTxt(String arquivo) {
        try {
            is = new FileInputStream(new File(arquivo));
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
