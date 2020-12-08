package com.mgckaled.dispositivoesensores.ui.sensores;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mgckaled.dispositivoesensores.R;
import com.mgckaled.dispositivoesensores.ui.sensores.act.ListaSensoresActivity;

public class SensoresFragment extends Fragment {

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        new ViewModelProvider(this).get(SensoresViewModel.class);

        View root = inflater.inflate(R.layout.fragment_sensores, container, false);

        // Textos da ListViw em uma array de Strings
        String[] options = {"Listar Sensores", "Usar Sensores"};

        // Obtém o componente de lista que irá mostrar a lista no fragmento
        ListView optionsList = (ListView) root.findViewById(R.id.listaOpcoesSensores);

       /* Adapter que retorna uma view para cada objeto dentro de uma
       coleção de dados de objetos  */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), // contexto
                android.R.layout.simple_list_item_1, // layout listview padrão fornecido pelo SDK
                options); // Array de String
        optionsList.setAdapter(adapter);

        // onclick listener passando o método criado 'enterOptions' como arguemnto
        optionsList.setOnItemClickListener((adapterView, view, i, l) -> enterOptions(i));

        return root;
    }

    // Método que irá iniciar uma Activivity conforme o index da Array de Strings (Listview)
    private void enterOptions(int option){

        Intent intent;
        switch (option){
            case 0:
                intent = new Intent(getContext(), ListaSensoresActivity.class);
                startActivity(intent);
                break;
           /* case 1:
                intent = new Intent(this, SensoresFiltroActivity.class);
                startActivity(intent);
                break;*/
        }
    }
}