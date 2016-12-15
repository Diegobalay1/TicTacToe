package diego.com.diego3l.tictactoe;

/**
 * Created by dlolh on 12/12/2016.
 */

public class Partida {
    private final int dificultad;
    private int jugador;

    public Partida(int dificultad){
        this.dificultad = dificultad;
        jugador = 1; //Empieza la partida jugador 1... se podría cambiar si quisieramos
    }

    public int getJugador(){
        return jugador;
    }

}
