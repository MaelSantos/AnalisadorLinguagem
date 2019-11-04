package br.edu.analisador.model;
import static br.edu.analisador.model.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espaco=[ ,\t,\r]+
%{
    public String lexeme;
%}
%%

/* Espaços em branco */
{espaco} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Salto de linha */
( "\n" ) {return Linha;}

/* citacoes */
( "\"" ) {lexeme=yytext(); return Citacoes;}

/* Tipos de dados */
( byte | int | char | long | float | double ) {lexeme=yytext(); return Tipo_Dado;}

/* Tipo de dados String */
( String ) {lexeme=yytext(); return Caractere;}

/*Palavra reservada if*/
if {lexeme=yytext(); return If;}

/*Palavra reservada else*/
else {lexeme=yytext(); return Else;}

/*Palavra reservada Do*/
( do ) {lexeme=yytext(); return Do;}

/*Palavra reservada While*/
while {lexeme=yytext(); return While;}

/* Palavra reservada For */
( for ) {lexeme=yytext(); return For;}

/* Operador Igual */
( "=" ) {lexeme=yytext(); return Igual;}

/* Operador Soma */
( "+" ) {lexeme=yytext(); return Soma;}

/* Operador Subtracao */
( "-" ) {lexeme=yytext(); return Subtracao;}

/* Operador Multiplicacao */
( "*" ) {lexeme=yytext(); return Multiplicacao;}

/* Operador Divisao */
( "/" ) {lexeme=yytext(); return Divisao;}

/* Operadores logicos */
( "&&" | "||" | "!" | "&" | "|" ) {lexeme=yytext(); return Op_Logico;}

/*Operadores Relacionais */
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {lexeme = yytext(); return Op_Relacional;}

/* Operadores Atribuicao */
( "+=" | "-="  | "*=" | "/=" | "%=" ) {lexeme = yytext(); return Op_Atribuicao;}

/* Operadores Incremento e decremento */
( "++" | "--" ) {lexeme = yytext(); return Op_Incremento;}

/*Operadores Booleanos*/
(true | false)      {lexeme = yytext(); return Op_Booleano;}

/*Parenteses de abertura*/
"(" {lexeme=yytext(); return Parenteses_Abrir;}

/*Parenteses de fechamento*/
")" {lexeme=yytext(); return Parenteses_Fechar;}

/*Chave de abertura*/
"{" {lexeme=yytext(); return Chave_Abrir;}

/*Chave de fechamento*/
"}" {lexeme=yytext(); return Chave_Fechar;}

/*Colchete de abertura*/
( "[" ) {lexeme = yytext(); return Colchete_Abrir;}

/*Colchete de Fechamento*/
( "]" ) {lexeme = yytext(); return Colchete_Fechar;}

/* metodo main*/
"main" {lexeme=yytext(); return Main;}

/*Ponto de termino de frase*/
";" {lexeme=yytext(); return Fim_Comando;}

/* Identificador*/
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}

/* Numero*/
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}

/* Error de analise*/
 . {return Erro;}
 
 
 