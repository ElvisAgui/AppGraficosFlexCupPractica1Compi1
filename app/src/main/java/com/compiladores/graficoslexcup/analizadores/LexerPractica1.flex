/*primer seccion: codigo de usuario*/
package com.compiladores.graficoslexcup.analizadores;
import java_cup.runtime.*;
import com.compiladores.graficoslexcup.report.ErrorSinLex;
import java.util.ArrayList;
import java.util.List;
%%

 /*segunda seccion: configuracion*/

%class LexerAnalysis
%unicode
%line
%column
%public
%cup
%standalone
%state COMENTARIO_BLOQUE


LETRA = [a-zA-Z]
WhiteSpace = [\r|\n|\r\n] | [ \t\f]
SIGNO_MENOS = "-"
SIGNO_MAS = "+"
SIGNO_POR = "*"
SIGNO_DIVISION = "/"
SIGNO_PARENTESISA = "\("
SIGNO_PARENTESISC ="\)"
SIGNO_LLAVEA = "\{"
SIGNO_LLAVEC = "\}"
SIGNO_CORCHETEA= "\["
SIGNO_CORCHETEC= "\]"
SIGNO_COMILLAS= "\""
SIGNO_PUNTO = "."
SIGNO_COMA = ","
SIGNO_PUNTOCOMA = ";"
SIGNO_DOPUNTO = ":"
NUMERO = [0-9]+
DECIMAL = (({NUMERO})({SIGNO_PUNTO})({NUMERO}))
INICIO_COMENTARIO_BLOQUE = "#"
FIN_COMENTARIO_BLOQUE = [\n]
DEF= ("Def" | "def")
BARRAS = "Barras"
PIE = "Pie" 
TITULO = "titulo"
EJEX = "ejex"
EJEY = "ejey"
ETIQUETAS = "etiquetas"
VALORES = "valores"
CANTIDAD = "Cantidad"
PORCENTAJE = "Porcentaje"
UNIR = "unir"
TIPO = "tipo"
TOTAL = "total"
EXTRA = "extra" 
EJECUTAR ="Ejecutar"
IDD_COMILLAS = ( \" ({LETRA}|{NUMERO}|\s)*({LETRA}|{NUMERO})({LETRA}|{NUMERO}|\s)* \")
IDD_SIMPLE = (({LETRA}|{NUMERO})*({LETRA})({LETRA}|{NUMERO})*)

/*comodin %{ para agregar codigo java*/
%{
    /*inicializar arreglo para errores lexico y registrarlos*/
    public static ArrayList<ErrorSinLex> errorsSinLexs = new ArrayList<>();

    private Symbol symbol(int type, String lexema) {
        return new Symbol(type, new Token(lexema, yyline + 1, yycolumn + 1));
    }

    /*
    public ArrayList<ErrorSinLex> getErrorsSinLexs() {
    return errorsSinLexs;
    }
    */
    
%}

/*accion al finlizar el texto*/
%eof{
   
%eof}

%%


/*tercer seccion: reglase lexicas*/

<YYINITIAL> {
{WhiteSpace} 	{/* ignoramos */}
{INICIO_COMENTARIO_BLOQUE} {yybegin(COMENTARIO_BLOQUE);}
{SIGNO_MENOS} {return symbol(sym.SIGNO_MENOS,yytext());}
{SIGNO_POR} {return symbol(sym.SIGNO_POR,yytext());}
{SIGNO_DIVISION} {return symbol(sym.SIGNO_DIVISION,yytext());}
{SIGNO_MAS} {return symbol(sym.SIGNO_MAS,yytext());}
{SIGNO_POR} {return symbol(sym.SIGNO_POR,yytext());}
{SIGNO_LLAVEA} {return symbol(sym.SIGNO_LLAVEA,yytext());}
{SIGNO_LLAVEC} {{return symbol(sym.SIGNO_LLAVEC,yytext());}}
{SIGNO_PARENTESISA} {return symbol(sym.SIGNO_PARENTESISA,yytext());}
{SIGNO_PARENTESISC} {return symbol(sym.SIGNO_PARENTESISC,yytext());}
{SIGNO_CORCHETEA} {return symbol(sym.SIGNO_CORCHETEA,yytext());}
{SIGNO_CORCHETEC} {return symbol(sym.SIGNO_CORCHETEC,yytext());}
{SIGNO_COMA} {return symbol(sym.SIGNO_COMA,yytext());}
{SIGNO_PUNTOCOMA} {return symbol(sym.SIGNO_PUNTOCOMA,yytext());}
{SIGNO_DOPUNTO} {return symbol(sym.SIGNO_DOPUNTO,yytext());}
{DEF} {return symbol(sym.DEF,yytext());}
{BARRAS} {return symbol(sym.DEF,yytext());}
{PIE} {return symbol(sym.PIE,yytext());}
{TITULO} {return symbol(sym.TITULO,yytext());}
{EJEX} {return symbol(sym.EJEX,yytext());}
{EJEY} {return symbol(sym.EJEY,yytext());}
{ETIQUETAS} {return symbol(sym.ETIQUETAS,yytext());}
{VALORES} {return symbol(sym.VALORES,yytext());}
{UNIR} {return symbol(sym.UNIR,yytext());}
{CANTIDAD} {return symbol(sym.CANTIDAD,yytext());}
{PORCENTAJE} {return symbol(sym.PORCENTAJE,yytext());}
{TIPO} {return symbol(sym.TIPO,yytext());}
{TOTAL} {return symbol(sym.TOTAL,yytext());}
{EXTRA} {return symbol(sym.EXTRA,yytext());}
{EJECUTAR} {return symbol(sym.EJECUTAR,yytext());}
{IDD_COMILLAS} {return symbol(sym.IDD_COMILLAS,yytext());}
{IDD_SIMPLE} {LexerAnalysis.errorsSinLexs.add(new ErrorSinLex(yytext(), yyline+1, yycolumn+1, "Simbolo no existe en el lenguaje")); }
{NUMERO} {return symbol(sym.SIGNO_DIVISION,yytext());}
{DECIMAL} {return symbol(sym.SIGNO_DIVISION,yytext());}
}

/*bloque de comentario ignorar*/
<COMENTARIO_BLOQUE>{
{FIN_COMENTARIO_BLOQUE} {yybegin(YYINITIAL);}
[^] {;}
}

[^]                              {LexerAnalysis.errorsSinLexs.add(new ErrorSinLex(yytext(), yyline+1, yycolumn+1, "Simbolo no existe en el lenguaje"));}



/*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*/
