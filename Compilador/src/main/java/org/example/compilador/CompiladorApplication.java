package org.example.compilador;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class CompiladorApplication {

	@SneakyThrows
    public static void main(String[] args) {
		SpringApplication.run(CompiladorApplication.class, args);



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
