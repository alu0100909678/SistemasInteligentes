package Juego;

/* Con esta clase vamos a representar las cartas que tiene un jugador en cierta
jugada y el dinero
que ha apostado en esa jugada */
import java.util.Vector;
public class Mano{
	// Se trata de un vector y no un array porque el numero de cartas en la mano
	//es variable
	private Vector mano;
	int apuesta;
	// Constructor
	public Mano() {
		mano = new Vector();
	}
	// Suelta todas las cartas
	public void soltar() {
		mano.removeAllElements();
	}
	// Añade una carta a su mano
	public void cogerCarta(Carta c) {
		if (c != null)
			mano.addElement(c);
	}
	// Suelta una de sus cartas
	public void dejarCarta(Carta c) {
		mano.removeElement(c);
	}
	// Suelta la carta en la posicion marcada por pos
	public void dejarCarta(int pos) {
		if (pos >= 0 && pos < mano.size())
			mano.removeElementAt(pos);
	}
	// Cuenta las cartas que tiene en la mano

	public int contar() {
		return mano.size();
	}
	// Nos dice la carta que tiene en la posicion pos
	public Carta obtenerCarta(int pos) {
		if (pos >= 0 && pos < mano.size())
			return (Carta)mano.elementAt(pos);
		else
			return null;
	}
	// Cuenta los puntos que suman las cartas de nuestra mano
	public int getBlackjackValor() {
		int val;
		boolean as;
		int cartas;
		val = 0;
		as = false;
		cartas = contar();
		for ( int i = 0; i < cartas; i++ ) {
			Carta carta;
			int cartaVal;
			carta = obtenerCarta(i);
			cartaVal = carta.getValor();
			if (cartaVal > 10) {
				cartaVal = 10;
			}
			if (cartaVal == 1) {
				as = true;
			}
			val = val + cartaVal;
		}
		/* El as en principio vale 1, pero si al cambiar su valor por 11
conseguimos un resultado
igual a 21 o menor lo cambiaremos*/
		if ( as == true && val + 10 <= 21 )
			val = val + 10;
		return val;
	} }
