package Juego;

/* Clase con la que vamos a representar cada una de las cartas de la baraja */
public class Carta {
/* Constantes enteras que definen los palos y las cartas que no tienen valor
numerico */
public final static int ESPADAS = 0,
CORAZONES = 1,
DIAMANTES = 2,
PICAS = 3;
public final static int AS = 1,
JACK = 11,
QUEEN = 12,
KING = 13;

/* Las 2 propiedades de nuestra carta seran valor y palo.
Las definimos como privadas y a continuacion definimos los metodos para
obtenerlas */
private final int palo;
private final int valor;
/* Metodo constructor */
public Carta(int val, int pal) {
valor = val;
palo = pal;
}
/* Metodos que nos devuelven valor y palo como entero y como String */
public int getPalo() {
return palo;
}
public int getValor() {
return valor;
}
public String getPaloString() {
switch ( palo ) {

case ESPADAS: return "Espadas";
case CORAZONES: return "Corazones";
case DIAMANTES: return "Diamantes";
case PICAS: return "Picas";
default: return "??";
}
}
public String getValorString() {
switch ( valor ) {
case 1: return "As";
case 2: return "2";
case 3: return "3";
case 4: return "4";
case 5: return "5";
case 6: return "6";
case 7: return "7";
case 8: return "8";
case 9: return "9";
case 10: return "10";
case 11: return "J";
case 12: return "Q";
case 13: return "K";
default: return "??";
}
}
public String toString() {
return getValorString() + " de " + getPaloString();
}
}
