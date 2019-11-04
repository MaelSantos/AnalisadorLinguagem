package br.edu.analisador.app;

import java.io.File;

public class Gerador {

	public static void main(String[] args) {
		
		String caminho = "/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/src/br/edu/analisador/model/Lexer.flex";
		geradorLexico(caminho);
	}
	
	public static void geradorLexico(String caminho) {
		File file = new File(caminho);
		JFlex.Main.generate(file);
	}
	
}
