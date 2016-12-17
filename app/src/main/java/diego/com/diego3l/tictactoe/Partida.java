package diego.com.diego3l.tictactoe;

import java.util.Random;

/**
 * Created by dlolh on 12/12/2016.
 */

public class Partida {
    private final int dificultad;
    private int jugador;
    private int[] casillas;

    public Partida(int dificultad){
        this.dificultad = dificultad;
        jugador = 1; //Empieza la partida jugador 1... se podría cambiar si quisieramos
        casillas = new int[9];
        for (int i=0; i<9; i++){
            casillas[i] = 0;
        }

    }

    public int getJugador(){
        return jugador;
    }

    //Método booleano para saber si tenemos la casilla ocupada o no
    public boolean comprueba_casilla(int casilla){
        if (casillas[casilla] == 0){
            casillas[casilla] = jugador;
        } else {
            return false;
        }
        return true;
    }

    //Método encargado de cambiar el turno
    public void turno(){
        jugador++;
        if (jugador > 2){
            jugador = 1;
        }
    }

    //Método encargado de jugar contra nosotros
    public int ia(){
        int casilla;
        Random casilla_azar = new Random();
        casilla = casilla_azar.nextInt(9);
        return casilla;
    }

}


    //Video dejado 50. Minuto 1