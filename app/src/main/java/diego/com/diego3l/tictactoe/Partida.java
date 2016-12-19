package diego.com.diego3l.tictactoe;

import java.util.Random;

/**
 * Created by dlolh on 12/12/2016.
 */

public class Partida {
    private final int dificultad;
    private int jugador;
    private int[] casillas;
    private final int[][] COMBINACIONES = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};

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

    //Método encargado de cambiar el turno y detectar la logica del juego
    //Nos devuelve 0 para seguir jugando, 3 empate, y 1 o 2 según ganador
    public int turno(){
        boolean empate = true;
        boolean ult_movimiento = true;

        for (int i = 0; i < COMBINACIONES.length; i++) {
            for (int pos : COMBINACIONES[i]) {
                //System.out.println("Valor en posicion: " + i + " " + casillas[pos]);
                System.out.println("Valor en posicion: " + pos + " " + casillas[pos]);
                if (casillas[pos] != jugador) ult_movimiento = false;
                if (casillas[pos] == 0){
                    empate = false;
                }
            }//cierre for anidado

            System.out.println("---------------------------------------------------------");
            if (ult_movimiento)return jugador;
            ult_movimiento = true;

        }//cierre for principal

        if (empate){
            return 3;
        }

        jugador++;

        if (jugador > 2){
            jugador = 1;
        }

        return 0;
    }

    //Método encargado de jugar contra nosotros
    public int ia(){
        int casilla;
        Random casilla_azar = new Random();
        casilla = casilla_azar.nextInt(9);
        return casilla;
    }

}


    //Video dejado 52. Minuto 6