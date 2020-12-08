package com.mgckaled.dispositivoesensores.ui.sensores.act;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mgckaled.dispositivoesensores.R;

import java.util.List;

public class ListaSensoresActivity extends AppCompatActivity {

    //Lista de sensores disponíveis
    private List<Sensor> sensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sensores);

        //Gerenciador de sensores do Android
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Obtém a lista de sensores disponíveis
        sensores = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //Lista com todos os nomes dos sensores disponíveis
        String[] nomesSensores = new String[sensores.size()];

        // Coletar o nome de cada sensor e gerar uma array com cada um dos nomes
        for (int i = 0; i < sensores.size(); i++){
            nomesSensores[i] = sensores.get(i).getName();
        }

        //Obtém o componente de lista que vai mostrar os sensores disponíveis
        ListView listaSensores = findViewById(R.id.sensors_list);

        /* Adapter que retorna uma view para cada objeto dentro de uma
        coleção de dados de objetos  */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                nomesSensores); // array
        listaSensores.setAdapter(adapter);

        // onclick Listener -> mostrar toast com o nome ao pressionar o nome do sensor
        listaSensores.setOnItemClickListener((adapterView, view, i, l) -> {
                    Sensor s = sensores.get(i);
                    Toast.makeText(getApplicationContext(),
                            s.getName(), Toast.LENGTH_SHORT).show();
                }
        );
    }
}