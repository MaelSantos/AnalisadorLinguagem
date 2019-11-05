package br.edu.analisador.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.Main;
import java_cup.internal_error;

public class Gerador {

	public static void main(String[] args) {

		String caminho = "/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/src/br/edu/analisador/model/Lexer.flex";
		String caminho2 = "/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/src/br/edu/analisador/model/LexerCup.flex";

		String[] caminhoSintativo = { "-parser", "Sintaxe",
				"/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/src/br/edu/analisador/model/Sintaxe.cup" };

		try {
			gerador(caminho, caminho2, caminhoSintativo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void gerador(String caminho, String caminho2, String[] caminhoSintativo) throws Exception{

		File file = new File(caminho); // gera analisador léxico
		JFlex.Main.generate(file);

		File file2 = new File(caminho2); // gera analisador sintático
		JFlex.Main.generate(file2);

		Main.main(caminhoSintativo);

		Path pathSym = Paths.get("/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/src/br/edu/analisador/model/sym.java");
		
		if(Files.exists(pathSym))
		{
			Files.delete(pathSym);
		}
		
		Path pathSin = Paths.get("/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/src/br/edu/analisador/model/Sintaxe.java");
		
		if(Files.exists(pathSin))
		{
			Files.delete(pathSin);
		}
		
		Files.move(
				Paths.get("/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/sym.java"), 
				Paths.get("/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/src/br/edu/analisador/model/sym.java"));
		
		Files.move(
				Paths.get("/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/Sintaxe.java"), 
				Paths.get("/media/mael/MAMA/Util/Projetos Java/AnalisadorLinguagem/src/br/edu/analisador/model/Sintaxe.java"));
		
	}

}
