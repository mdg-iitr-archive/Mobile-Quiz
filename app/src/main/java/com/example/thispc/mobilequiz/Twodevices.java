package com.example.thispc.mobilequiz;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

public class Twodevices extends AppCompatActivity {

    boolean refreshEnabled = false;

    private BluetoothAdapter bluetoothAdapter;
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    private ArrayAdapter adapter;
    private ListView listview;
    private static final int DISCOVERABLE_DURATION = 300;


    private final static UUID uuid = UUID.fromString("fc5ffc49-00e3-4c8b-9cf1-6b72aad1001a");
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                adapter.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twodevices);
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Your device does not support Bluetooth",
                    Toast.LENGTH_SHORT).show();
        } else if (!refreshEnabled) {
            refreshEnabled = true;
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetoothIntent, ENABLE_BT_REQUEST_CODE);
            Toast.makeText(Twodevices.this, "Bluetooth Enabled", Toast.LENGTH_SHORT).show();
        } else if (refreshEnabled) {

            refreshEnabled = false;
            adapter.clear();
            bluetoothAdapter.disable();
        }


        listview = (ListView) findViewById(R.id.listView);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listview.getItemAtPosition(position);
                String MAC = itemValue.substring(itemValue.length() - 17);
                BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(MAC);
            }
        });

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
            adapter.clear();
        }
    }

}
