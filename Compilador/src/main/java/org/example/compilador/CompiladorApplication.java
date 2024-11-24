package org.example.compilador;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.*;
import java.awt.*;
import java.util.List;



@SpringBootApplication
public class CompiladorApplication {

	@SneakyThrows
    public static void main(String[] args) {
		SpringApplication.run(CompiladorApplication.class, args);

		if (args.length > 0) {
			try {
				LerToken lex = new LerToken(args[0]);
				Token t;

				while ((t = lex.proximoToken()) != null) {
					System.out.println(t);
				}
             lex.imprimirIdentificadores();
			} catch (Exception e) {
				System.err.print("Erro ao processar tokens: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.print("Nenhum argumento foi fornecido. Por favor, forneça o caminho do arquivo como argumento.");
		}

	}
	}




