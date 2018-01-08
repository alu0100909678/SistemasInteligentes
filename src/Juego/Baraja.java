package Juego;

/* Clase que representara nuestra baraja de 52 cartas*/
public class Baraja {
	// La baraja sera un array de Cartas
	private static Carta[] baraja;
	// Definimos un array de cartas vistas. En cada posicion del array tendremos
	//el numero de cartas, cuyo
	// valor es dicha posicion, que hay descubiertas sobre la mesa o tiene la
	//banca
	public int[] vistas;

	// Numero de cartas robadas
	private int robadas;
	// Metodo constructor. Recorremos todos los valores posibles y todos los
	//palos posibles
	public Baraja() {
		baraja = new Carta[52];
		vistas = new int[13];
		int creadas = 0;
		for ( int palo = 0; palo <= 3; palo++ ) {
			for ( int valor = 1; valor <= 13; valor++ ) {
				baraja[creadas] = new Carta(valor,palo);
				creadas++;
			}
		}
		robadas = 0;
	}
	// Ordenamos la baraja en orden aleatorio
	public void barajar() {
		for ( int i = 51; i > 0; i-- ) {
			int rand = (int)(Math.random()*(i+1));
			Carta temp = baraja[i];
			baraja[i] = baraja[rand];
			baraja[rand] = temp;
		}
		robadas = 0;
	}
	// Numero de cartas que nos quedan en la baraja
	public int restantes() {
		return 52 - robadas;
	}
	// Cuando robamos una carta cogemos la primera del mazo y actualizamos el
	//array de cartas vistas
	public Carta robar() {
		if (robadas == 52)
			barajar();
		robadas++;
		vistas[((baraja[robadas-1]).getValor())-1]++;
		return baraja[robadas - 1];
	}}
