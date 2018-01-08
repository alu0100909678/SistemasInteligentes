package Juego;

/* Clase principal que llevara el control del juego */
public class BlackJack {
// Metodo main
public static void main (String[] args) {
int saldo;
int[] resultados;
int[] saldos;
int numero,i;
Mano manoJugador[];
boolean primera=true;
boolean bancarrota[];
// Presentacion
TextIO.putln();
TextIO.putln("Bienvenido al juego de Blackjack.");
TextIO.putln();
TextIO.putln(" AUTORES");
TextIO.putln();
TextIO.putln("Ignacio Ribas Ramos");
TextIO.putln("Miguel Flecha Lozano");
TextIO.putln();
// Preguntamos por número de jugadores y por la cantidad con la que empiezan
TextIO.put("Numero de jugadores:");
numero = TextIO.getlnInt();
TextIO.put("Con que cantidad empezara cada jugador (euros)?");
saldo = TextIO.getlnInt();
// Inicializamos los arrays que vamos a utilizar
saldos=new int[numero];
manoJugador=new Mano[numero];
resultados=new int[numero+1];
bancarrota=new boolean[numero];

for(i=0;i<numero;i++){
saldos[i]=saldo;
bancarrota[i]=false;
}
// Comienza el bucle...
while (true) {
// Le preguntamos a cada jugador cuanto dinero desea apostar
for(int j=0;j<numero;j++){
// Creamos la mano
manoJugador[j]= new Mano();
if(saldos[j]<=0) bancarrota[j]=true;
TextIO.putln();
TextIO.putln("JUGADOR NUMERO " + (j+1) +". Tiene " + saldos[j] + " euros.");

if(!bancarrota[j]){
do {
TextIO.putln("Cuantos euros quiere apostar? (0 para salir)");

manoJugador[j].apuesta=TextIO.getlnInt();
if (manoJugador[j].apuesta < 0 || manoJugador[j].apuesta > saldos[j])

TextIO.putln("Su apuesta debe estar entre 0 y " + saldos[j] + '.');

// En caso de que el jugador no desee jugar más utilizamos el mismo array

// de booleanos que usamos cuando un jugador se queda sin dinero, para

// que la siguiente ronda no se le pregunte
if (manoJugador[j].apuesta == 0){
TextIO.putln();
TextIO.putln("ADIOS jugador "+(j+1));
bancarrota[j]=true;
}
} while (manoJugador[j].apuesta < 0 ||

manoJugador[j].apuesta > saldos[j]);

}
}

// En caso de que no se hayan arruinado todos los jugadores entramos al metodo jugar

if(!queda_alguno(bancarrota,numero)) resultados =

jugar(numero,manoJugador,bancarrota);

else{
TextIO.putln();
TextIO.putln("Todos los jugadores se han quedado sin dinero o no hay mas jugadores");

TextIO.putln("Adioooos");
System.exit(-1);
}
// Al salir del metodo jugar comprobamos las puntuaciones ya actualizamos los saldos
for(int j=0;j<numero;j++){
if((resultados[0]==2)||(resultados[0]==1)) saldos[j] = saldos[j] -

manoJugador[j].apuesta;
else{
switch (resultados[j+1]){

case -1: saldos[j] = saldos[j] -

manoJugador[j].apuesta;break;

case 1: saldos[j] = saldos[j] +

manoJugador[j].apuesta;break;

case 2: saldos[j] = saldos[j] +

2*manoJugador[j].apuesta;break;

default: saldos[j] = saldos[j]-manoJugador[j].apuesta;
}
}
}
}
}
// Metodo jugar que lleva el desarrollo principal de la partida
static int[] jugar(int jugadores,Mano[] manoJugador,boolean[] bancarrota) {
Baraja baraja;
Mano manoBanca;
boolean fin=false;
int i,j;

// En resultados almacenamos lo que ha hecho cada jugador.
// 0 en un principio, -1 si se pasa y 1 si gana.
// La posicion 0 sera la de la banca y la posicion i la del jugador i
// Pondremos un 2 en caso de blackjack.
int resultados[];
resultados=new int[jugadores+1];
for (int m=0;m<=jugadores;m++){
resultados[m]=0;
}
baraja = new Baraja();

// Barajamos y repartimos
baraja.barajar();
// La banca roba sus cartas
manoBanca = new Mano();
manoBanca.cogerCarta( baraja.robar() );
manoBanca.cogerCarta( baraja.robar() );
TextIO.putln();
TextIO.putln();
for(i=0;i<jugadores;i++){
// Los jugadores van robando
manoJugador[i].cogerCarta( baraja.robar() );
manoJugador[i].cogerCarta( baraja.robar() );
// En caso de que el usuario consiga Blackjack (21 a la primera) gana el doble de la apuesta
// y se acaba la ronda
if ((manoJugador[i].getBlackjackValor() == 21)&&(!bancarrota[i])) {

TextIO.putln("La banca tiene " + "anoBanca.obtenerCarta(0) " + " y " + manoBanca.obtenerCarta(1) + ".");
TextIO.putln("El jugador "+(i+1)+" tiene " + manoJugador[i].obtenerCarta(0) + " y " + manoJugador[i].obtenerCarta(1) + ".");
TextIO.putln();
TextIO.putln("El jugador "+(i+1)+" tiene Blackjack y gana");
resultados[i+1]=2;
fin=true;
}
}
// Si la banca tiene BJ gana y se acaba la partida
if (manoBanca.getBlackjackValor() == 21) {
TextIO.putln("La banca tiene " + manoBanca.obtenerCarta(0) + " y " + manoBanca.obtenerCarta(1) + ".");

TextIO.putln();
TextIO.putln("La banca tiene Blackjack y gana.");
resultados[0]=2;
fin=true;
}
if (fin) return resultados;
// Si ninguno de los 2 tiene BJ seguimos con el juego
// Comienza la iteracion para cada uno de los jugadores
for(j=0;j<jugadores;j++){
// Mostramos las cartas de los jugadores y una de las cartas de la banca
TextIO.putln("--------- JUGADOR "+(j+1)+" ---------");
TextIO.putln();
fin=false;
while (!bancarrota[j]) {
TextIO.putln();
TextIO.putln("Jugador "+(j+1)+".Sus cartas son:");
for ( i = 0; i < manoJugador[j].contar(); i++ )
TextIO.putln(" " + manoJugador[j].obtenerCarta(i));
TextIO.putln("Y suman un total de " +
manoJugador[j].getBlackjackValor()+" puntos.");

TextIO.putln();
TextIO.putln("La banca muestra " +

manoBanca.obtenerCarta(0));
TextIO.putln();
// Carta o se planta?

TextIO.put("Carta (C) o se Planta (P)? ");
char accion;
do {
accion = Character.toUpperCase( TextIO.getlnChar() );
if (accion != 'C'&& accion != 'P')
TextIO.put("Por favor responda C o P: ");
} while (accion != 'C'&& accion != 'P');
// Si se planta salimos del bucle
if ( accion == 'P') {
TextIO.putln();
TextIO.putln("El jugador " +(j+1)+" se planta.");
TextIO.putln();
break;
}
// Si no se planta seguimos con una nueva carta
else {
Carta newCarta = baraja.robar();
manoJugador[j].cogerCarta(newCarta);
TextIO.putln();
TextIO.putln("Usted roba carta.");
TextIO.putln("Su carta es " + newCarta);
TextIO.putln("Y usted tiene " +
manoJugador[j].getBlackjackValor()+" puntos");

// Si se pasa de 21 puntos pierde y pone su resultado a -1
if (manoJugador[j].getBlackjackValor() > 21) {

TextIO.putln();
TextIO.putln("El jugador "+(j+1)+" se ha pasado de 21. Ha perdido");

TextIO.putln();
resultados[j+1]=-1;
fin=true;

}
}
if (fin) break;

}
}
// Ahora le toca jugar a la banca
TextIO.putln();
TextIO.putln("Las cartas de la banca son ");
TextIO.putln(" " + manoBanca.obtenerCarta(0));
TextIO.putln(" " + manoBanca.obtenerCarta(1));

while(true){
float beneficio=0;
float beneficio_hip=0;
float beneficio_aux=0;
float probabilidad=0;
// Primero comprobamos el beneficio que obtendriamos en la situacion actual

for(i=0;i<jugadores;i++){
if(manoBanca.getBlackjackValor()>=manoJugador[i].getBlackjackValor())
beneficio=beneficio+manoJugador[i].apuesta;

else if(resultados[i+1]!=-1) beneficio=beneficio-
manoJugador[i].apuesta;

}
// Sabiendo las cartas que hay en la mesa calcularemos la esperanza de sacar cada una de las

// posibles cartas y en funcion de eso el beneficio hipotetico que obtendriamos.

for (j=1;j<14;j++){
Carta aux=new Carta(j,0);
manoBanca.cogerCarta(aux);
probabilidad=baraja.vistas[j-1]/baraja.restantes();
for(i=0;i<jugadores;i++){

if(manoBanca.getBlackjackValor()>=manoJugador[i].getBlackjackValor())
beneficio_aux=beneficio_aux+manoJugador[i].apuesta;
else if(resultados[i+1]!=-1)
beneficio_aux=beneficio_aux-manoJugador[i].apuesta;

}
beneficio_hip=beneficio_hip+beneficio_aux*probabilidad;
beneficio_aux=0;
manoBanca.dejarCarta(manoBanca.contar()-1);
}
// Si el beneficio hipotetico es mayor que el actual robamos carta
if(beneficio_hip>beneficio){
Carta newCarta = baraja.robar();

TextIO.putln("La banca roba " + newCarta);
manoBanca.cogerCarta(newCarta);
if (manoBanca.getBlackjackValor() > 21) {
resultados[0]=-1;
break;
}
}else{
break;
}
}
// Repetimos esto hasta que nos pasemos o decidamos no coger nueva
//carta
TextIO.putln("El total de la banca es de " + manoBanca.getBlackjackValor() + " puntos");
// Vamos a comparar los puntos de cada uno para ver quien ha ganado
//a quien y lo reflejamos
// en el array de resultados
for(i=0;i<jugadores;i++){
if((resultados[i+1]==-1)||(bancarrota[i])) continue;
if(resultados[0]==-1){
resultados[i+1]=1;
continue;
}

if(manoJugador[i].getBlackjackValor()>manoBanca.getBlackjackValor())
resultados[i+1]=1;

else resultados[i+1]=-1;
}
TextIO.putln();
TextIO.putln();
// Imprimimos por pantalla quien ha ganado a quien
for(i=1;i<=jugadores;i++){
if(!bancarrota[i-1]){
if(resultados[i]>resultados[0]) TextIO.putln("Jugador "+i+" gana a la banca.");

else TextIO.putln("La banca gana al jugador "+i+".");
}
}

return resultados;
}
// Este metodo comprueba si todos los jugadores se han quedado sin
//dinero
static boolean queda_alguno(boolean bancarrota[],int numero){
boolean bancarrota_total=true;
for(int p=0;(p<numero)&&bancarrota_total;p++){
if(!bancarrota[p]){
bancarrota_total=false;
break;
}
}
return bancarrota_total;
}

}
