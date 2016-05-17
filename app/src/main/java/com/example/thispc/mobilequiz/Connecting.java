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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.UUID;

public class Connecting extends AppCompatActivity {
    Button btn;
    EditText name;

    boolean refreshEnabled = false;

    private BluetoothAdapter bluetoothAdapter;
    private ListView listview;
    private ArrayAdapter adapter;
    private static final int ENABLE_BT_REQUEST_CODE = 1;




    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);


                adapter.add(bluetoothDevice.getName() + "\n" +bluetoothDevice.getAddress() );
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecting);


        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Your device does not support Bluetooth",
                    Toast.LENGTH_SHORT).show();
        } else if (!refreshEnabled) {
            refreshEnabled = true;
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetoothIntent, ENABLE_BT_REQUEST_CODE);
            Toast.makeText(Connecting.this, "Bluetooth Enabled", Toast.LENGTH_SHORT).show();
        } else if (refreshEnabled) {
            btn.setText("Find Opponent");
            refreshEnabled = false;
            adapter.clear();
            bluetoothAdapter.disable();
        }
    }
}

