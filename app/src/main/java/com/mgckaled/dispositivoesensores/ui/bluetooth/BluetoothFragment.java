package com.mgckaled.dispositivoesensores.ui.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mgckaled.dispositivoesensores.R;

public class BluetoothFragment extends Fragment {

    public static final int REQUEST_ENABLE_BT = 1;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        new ViewModelProvider(this).get(BluetoothViewModel.class);

        View root = inflater.inflate(R.layout.fragment_bluetooth, container, false);

        // BluetoothAdapter -> Necessário para todas as atividades do Bluetooth
        // getDefaultAdapter() -> Adaptador Bluetooth padrão
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // Verificar se o aparelho possui dispositivo (hardware)
        if (bluetoothAdapter == null) {
            Toast.makeText(getContext(),
                    "Bluetooth não disponível nesse aparelho",
                    Toast.LENGTH_SHORT).show();
        }


        Button checkandActivateBluetooth = root.findViewById(R.id.check_activate_bluetooth);
        Button showDevicesBluetooth = root.findViewById(R.id.show_devices_bluetooth);

        // onclick listener "Verificar e Ativar Bluetooth"
        checkandActivateBluetooth.setOnClickListener(v -> {
            // Verificando se o bluetooth está ativado...
            assert bluetoothAdapter != null;
            if (bluetoothAdapter.isEnabled()) {
                Toast.makeText(getContext(),
                        "Bluetooth está ligado!",
                        Toast.LENGTH_SHORT).show();

            } else { // se não, Soclicitar ativação
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        });

        // onclick listener -> "Mostrar Dispositivos Pareados"
        showDevicesBluetooth.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ListaDispBtPareadosActivity.class);
            startActivity(intent);
        });

        return root;
    }

    // exibir toast confirme resultado da interação da requisição de ativação do Bluetooth
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK){
            Toast.makeText(getContext(),
                    "Bluetooth não foi ativado. =( ",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),
                    "Bluetooth foi ativado! =) ",
                    Toast.LENGTH_SHORT).show();
        }
    }
}