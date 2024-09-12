package org.example.compilador;

public class LerToken {
    LeitorDeArquivosTxt ldat;
    public LerToken(String arquivo) {
        ldat = new LeitorDeArquivosTxt(arquivo);
    }
    public Token  proximoToken() {
  int caractereLido = -1;
 while ((caractereLido = ldat.lerProximoCaractere()) != -1){
     char c = (char) caractereLido;
     if(c== ' ' || c== '\n') continue;
 }
        return null;
    }


}
