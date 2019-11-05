package br.edu.analisador.controle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import br.edu.analisador.model.Tokens;
import java_cup.runtime.Symbol;
import br.edu.analisador.model.Lexer;
import br.edu.analisador.model.LexerCup;
import br.edu.analisador.model.Sintaxe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ControleAnalisador {

    @FXML
    private TextArea tfdEntrada;

    @FXML
    private Button btnGerarAnalises;

    @FXML
    private Button btnLimparLexico;

    @FXML
    private Button btnLimparSintatico;

    @FXML
    private TextArea tfdLexico;

    @FXML
    private TextArea tfdSintatico;

    @FXML
    void actionButton(ActionEvent event) {

    	Object obj = event.getSource();
    	
    	if(obj == btnGerarAnalises) {
    		if (!tfdEntrada.getText().isEmpty()) {
    			File arquivo = new File("arquivo.txt");
    			PrintWriter escrever;
    			try {
    				escrever = new PrintWriter(arquivo);
    				escrever.print(tfdEntrada.getText());
    				escrever.close();
    			} catch (FileNotFoundException e) {
    				e.printStackTrace();
    			}

    			try {
    				analizeLexica();
    				analiseSintatica();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}

    	}
    	else if(obj == btnLimparLexico)
    	{
    		tfdLexico.setText("");
    	}
    	else if(obj == btnLimparSintatico)
    	{
    		tfdSintatico.setText("");
    	}
    	
    }

    private void analizeLexica() throws IOException {
		int cont = 1;

		String expr = (String) tfdEntrada.getText();
		Lexer lexer = new Lexer(new StringReader(expr));
		String resultado = "LINHA " + cont + "\t\t\t\t\t\tSIMBOLO\n";
		while (true) {
			Tokens token = lexer.yylex();
			if (token == null) {
				resultado += "<Fim>";
				tfdLexico.setText(resultado);
				return;
			}
			switch (token) {
			case Linha:
				cont++;
				resultado += "LINHA " + cont + "\n";
				break;
			case Citacoes:
				resultado += "  <Citações>\t\t\t\t\t" + lexer.lexeme + "\n";
				break;
			case Caractere:
				resultado += "  <Tipo de dado>\t\t\t" + lexer.lexeme + "\n";
				break;
			case Tipo_Dado:
				resultado += "  <Tipo de dado>\t\t\t\t" + lexer.lexeme + "\n";
				break;
			case If:
				resultado += "  <Reservada if>\t\t\t\t" + lexer.lexeme + "\n";
				break;
			case Else:
				resultado += "  <Reservada else\t\t\t\t" + lexer.lexeme + "\n";
				break;
			case Do:
				resultado += "  <Reservada do>\t\t\t\t" + lexer.lexeme + "\n";
				break;
			case While:
				resultado += "  <Reservada while>\t\t\t" + lexer.lexeme + "\n";
				break;
			case For:
				resultado += "  <Reservada while>\t\t\t" + lexer.lexeme + "\n";
				break;
			case Igual:
				resultado += "  <Operador igual>\t\t\t\t" + lexer.lexeme + "\n";
				break;
			case Soma:
				resultado += "  <Operador soma>\t\t\t\t" + lexer.lexeme + "\n";
				break;
			case Subtracao:
				resultado += "  <Operador subtração>\t\t\t" + lexer.lexeme + "\n";
				break;
			case Multiplicacao:
				resultado += "  <Operador multiplicação>\t\t" + lexer.lexeme + "\n";
				break;
			case Divisao:
				resultado += "  <Operador divisão>\t\t\t" + lexer.lexeme + "\n";
				break;
			case Op_Logico:
				resultado += "  <Operador logico>\t\t" + lexer.lexeme + "\n";
				break;
			case Op_Incremento:
				resultado += "  <Operador incremento>\t\t" + lexer.lexeme + "\n";
				break;
			case Op_Relacional:
				resultado += "  <Operador relacional>\t\t\t" + lexer.lexeme + "\n";
				break;
			case Op_Atribuicao:
				resultado += "  <Operador atribuição>\t\t" + lexer.lexeme + "\n";
				break;
			case Op_Booleano:
				resultado += "  <Operador booleano>\t\t" + lexer.lexeme + "\n";
				break;
			case Parenteses_Abrir:
				resultado += "  <Parentesis de abertura>\t\t" + lexer.lexeme + "\n";
				break;
			case Parenteses_Fechar:
				resultado += "  <Parentesis de fechamento>\t" + lexer.lexeme + "\n";
				break;
			case Chave_Abrir:
				resultado += "  <Chave de abertura>\t\t\t" + lexer.lexeme + "\n";
				break;
			case Chave_Fechar:
				resultado += "  <Chave de fechamento>\t\t" + lexer.lexeme + "\n";
				break;
			case Colchete_Abrir:
				resultado += "  <Colchete de abertura>\t\t" + lexer.lexeme + "\n";
				break;
			case Colchete_Fechar:
				resultado += "  <Colchete de fechamento>\t\t" + lexer.lexeme + "\n";
				break;
			case Main:
				resultado += "  <Reservada main>\t\t\t" + lexer.lexeme + "\n";
				break;
			case Fim_Comando:
				resultado += "  <Fim de Comando>\t\t\t" + lexer.lexeme + "\n";
				break;
			case Identificador:
				resultado += "  <Identificador>\t\t\t\t" + lexer.lexeme + "\n";
				break;
			case Numero:
				resultado += "  <Numero>\t\t\t\t\t" + lexer.lexeme + "\n";
				break;
			case Erro:
				resultado += "  <Simbolo nao definido>\n";
				break;
			default:
				resultado += "  < " + lexer.lexeme + " >\n";
				break;
			}
		}

	}

	public void analiseSintatica() {
		String st = tfdEntrada.getText();
		@SuppressWarnings("deprecation")
		Sintaxe sintaxe = new Sintaxe(new LexerCup(new StringReader(st)));

		try {
			sintaxe.parse();
			tfdSintatico.setText("Analize Realizada com Sucesso");

		} catch (Exception e) {
			Symbol sym = sintaxe.getS();
			tfdSintatico.setText("Error de Sintaxe na linha: " + (sym.right + 1) + "\nColuna: " + (sym.left + 1)
					+ "\nErro Texto: " + (sym.value));
			


		}
	}

    
}
