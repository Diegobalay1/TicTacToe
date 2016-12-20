package diego.com.diego3l.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private int jugadores;
    private int[] CASILLAS;
    private Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Iniciamos el array CASILLAS que identifica cada casilla..
        //..y la almacena en el array
        CASILLAS = new int[9];
        CASILLAS[0] = R.id.a1;
        CASILLAS[1] = R.id.a2;
        CASILLAS[2] = R.id.a3;

        CASILLAS[3] = R.id.b1;
        CASILLAS[4] = R.id.b2;
        CASILLAS[5] = R.id.b3;

        CASILLAS[6] = R.id.c1;
        CASILLAS[7] = R.id.c2;
        CASILLAS[8] = R.id.c3;

    }

    //--------Método a Jugar-------------------------
    public void aJugar(View vista){
        ImageView imagen;
            //Reseteamos el tablero a la hora de comenzar partida
        /*for (int cadaCasilla: CASILLAS){
            imagen = (ImageView)findViewById(cadaCasilla);
            imagen.setImageResource(R.drawable.casilla);
        }*/
        for (int i=0; i<CASILLAS.length; i++){
            imagen = (ImageView)findViewById(CASILLAS[i]);
            imagen.setImageResource(R.drawable.casilla);
        }

        //partida, establecemos jugadores
        jugadores = 1;
        if (vista.getId() == R.id.dosjug){
            jugadores = 2;
        }

        //establecemos Dificultad
        RadioGroup configDificultad = (RadioGroup)findViewById(R.id.configD);
        int id = configDificultad.getCheckedRadioButtonId();
        int dificultad = 0;
        if (id == R.id.normal){
            dificultad = 1;
        } else if (id == R.id.imposible){
            dificultad = 2;
        }

        partida = new Partida(dificultad);

        //Inhabilitamos botonos una vez ha empezado la partida
        ((Button)findViewById(R.id.unjug)).setEnabled(false);
        ((Button)findViewById(R.id.dosjug)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(0);

        ((TextView)findViewById(R.id.titulo)).setAlpha(0);

    }

    //Método ---toque--- encargado de detectar que casilla se pulsa
    public void toque(View mivista){
        if (partida == null){ //Si aún no hemos empezado partida, nos saca del método y no podremos dar a las casillas
            return;
        }

        int casilla = 0;
        for (int i=0; i<9; i++){
            if (CASILLAS[i] == mivista.getId()){
                casilla = i;
                break;
            }
        }


        if (!partida.comprueba_casilla(casilla)) return;
        marca(casilla);
        int resultado = partida.turno();
        if (resultado > 0) {
            termina(resultado);
            return;
        }
        do {
            casilla = partida.ia();
        }
        while (!partida.comprueba_casilla(casilla));

        marca(casilla);
        resultado = partida.turno();
        if (resultado > 0) {
            termina(resultado);
        }

    }

    //Método que determina un resultado final de la partida
    private void termina(int resultado){
        String mensaje;
        if (resultado == 1) mensaje = getString(R.string.circulos_ganan);
        else if (resultado == 2) mensaje = getString(R.string.aspas_ganan);
        else mensaje = getString(R.string.empate);

        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        partida = null; //partida inhabilitada

        //Habilitamos botonos una vez ha terminado la partida
        ((Button)findViewById(R.id.unjug)).setEnabled(true);
        ((Button)findViewById(R.id.dosjug)).setEnabled(true);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(1);

        ((TextView)findViewById(R.id.titulo)).setAlpha(1);
    }

    //Método encargado de marcar la casilla
    private void marca(int casilla){
        ImageView imagen;
        imagen = (ImageView)findViewById(CASILLAS[casilla]);
        /*if (partida.getJugador() == 1){
            imagen.setImageResource(R.drawable.circulo);
        } else {
            imagen.setImageResource(R.drawable.aspa);
        }*/
        switch (partida.getJugador()){
            case 1:
                imagen.setImageResource(R.drawable.circulo);
                break;
            case 2:
                imagen.setImageResource(R.drawable.aspa);
                break;
        }
    }



}
