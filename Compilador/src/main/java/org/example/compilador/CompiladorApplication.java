package org.example.compilador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;

@SpringBootApplication
public class CompiladorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompiladorApplication.class, args);

		// Verifica se há argumentos fornecidos
		if (args.length > 0) {
			try {
				// Cria uma instância de LerToken com o primeiro argumento
				LerToken lex = new LerToken(args[0]);
				Token t;

				// Processa os tokens e imprime cada um deles
				while ((t = lex.proximoToken()) != null) {
					System.out.println(t);
				}
			} catch (Exception e) {
				System.err.println("Erro ao processar tokens: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("Nenhum argumento foi fornecido. Por favor, forneça o caminho do arquivo como argumento.");
		}
	}
}
