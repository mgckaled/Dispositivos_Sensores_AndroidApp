package com.mgckaled.dispositivoesensores.ui.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mgckaled.dispositivoesensores.R;

import java.util.ArrayList;
import java.util.List;

public class ListaDispBtPareadosActivity extends AppCompatActivity {

    public List<BluetoothDevice> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disp_bt_pareados);

        ListView lvMatchedDevices = findViewById(R.id.devices_matched_list);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        assert bluetoothAdapter != null;
        if (bluetoothAdapter.isEnabled()) {
            devices = new ArrayList<>(bluetoothAdapter.getBondedDevices());

            List<String> infoMatchedDevices = new ArrayList<>();

            if (devices.size() > 0) {
                for (BluetoothDevice device : devices) {
                    boolean matched = device.getBondState() == BluetoothDevice.BOND_BONDED;
                    infoMatchedDevices.add(device.getName()
                            + " - " + device.getAddress()
                            + (matched ? " - PAREADO" : ""));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        infoMatchedDevices);
                lvMatchedDevices.setAdapter(adapter);

                lvMatchedDevices.setOnItemClickListener((adapterView, view, i, l) -> {
                            BluetoothDevice device = devices.get(i);
                            Toast.makeText(getApplicationContext(),
                                    device.getName() + " - " + device.getAddress(),
                                    Toast.LENGTH_SHORT).show();
                        }
                );
            }

        } else {
            Toast.makeText(this,
                    "Ative seu Bluetooth primeiro!", Toast.LENGTH_SHORT).show();
        }
    }
}