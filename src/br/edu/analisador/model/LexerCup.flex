package br.edu.analisador.model;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espaco=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/* espacos em branco */
{espaco} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Citacoes */
( "\"" ) {return new Symbol(sym.Citacoes, yychar, yyline, yytext());}

/* Tipos de dados */
( byte | char | long | float | double ) {return new Symbol(sym.Tipo_Dado, yychar, yyline, yytext());}

/* Tipo de dato Int (Para o main) */
( "int" ) {return new Symbol(sym.Int, yychar, yyline, yytext());}

/* Tipo de dado String */
( String ) {return new Symbol(sym.Caractere, yychar, yyline, yytext());}

/* Palavra reservada If */
( if ) {return new Symbol(sym.If, yychar, yyline, yytext());}

/* Palavra reservada Else */
( else ) {return new Symbol(sym.Else, yychar, yyline, yytext());}

/* Palavra reservada Do */
( do ) {return new Symbol(sym.Do, yychar, yyline, yytext());}

/* Palavra reservada While */
( while ) {return new Symbol(sym.While, yychar, yyline, yytext());}

/* Palavra reservada For */
( for ) {return new Symbol(sym.For, yychar, yyline, yytext());}

/* Operador Igual */
( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}

/* Operador Soma */
( "+" ) {return new Symbol(sym.Soma, yychar, yyline, yytext());}

/* Operador Subtracao */
( "-" ) {return new Symbol(sym.Subtracao, yychar, yyline, yytext());}

/* Operador Multiplicacao */
( "*" ) {return new Symbol(sym.Multiplicacao, yychar, yyline, yytext());}

/* Operador Divisao */
( "/" ) {return new Symbol(sym.Divisao, yychar, yyline, yytext());}

/* Operadores logicos */
( "&&" | "||" | "!" | "&" | "|" ) {return new Symbol(sym.Op_Logico, yychar, yyline, yytext());}

/*Operadores Relacionais */
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {return new Symbol(sym.Op_Relacional, yychar, yyline, yytext());}

/* Operadores Atribuicao */
( "+=" | "-="  | "*=" | "/=" | "%=" | "=" ) {return new Symbol(sym.Op_Atribuicao, yychar, yyline, yytext());}

/* Operadores Incremento e decremento */
( "++" | "--" ) {return new Symbol(sym.Op_Incremento, yychar, yyline, yytext());}

/*Operadores Booleanos*/
( true | false ) {return new Symbol(sym.Op_Booleano, yychar, yyline, yytext());}

/* Parentesis de Abrir */
( "(" ) {return new Symbol(sym.Parenteses_Abrir, yychar, yyline, yytext());}

/* Parentesis de Fechar */
( ")" ) {return new Symbol(sym.Parenteses_Fechar, yychar, yyline, yytext());}

/* Chave de Abrir */
( "{" ) {return new Symbol(sym.Chave_Abrir, yychar, yyline, yytext());}

/* Clave de Fechar */
( "}" ) {return new Symbol(sym.Chave_Fechar, yychar, yyline, yytext());}

/* Corchete de apertura */
( "[" ) {return new Symbol(sym.Colchete_Abrir, yychar, yyline, yytext());}

/* Corchete de cierre */
( "]" ) {return new Symbol(sym.Colchete_Fechar, yychar, yyline, yytext());}

/* Marcador de inicio de algoritmo */
( "main" ) {return new Symbol(sym.Main, yychar, yyline, yytext());}

/* Fim de comando */
( ";" ) {return new Symbol(sym.Fim_Comando, yychar, yyline, yytext());}

/* Identificador */
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}

/* Numero */
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}

/* Error de analisis */
 . {return new Symbol(sym.Erro, yychar, yyline, yytext());}
