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
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;


public class Connecting extends AppCompatActivity {


    boolean refreshEnabled = false;
    ConnectingThread ct = null;
    private BluetoothAdapter bluetoothAdapter;
    private ListView listview;
    private ArrayAdapter adapter;
    private static final int ENABLE_BT_REQUEST_CODE = 1;



    private final static UUID uuid = UUID.fromString("fc5ffc49-00e3-4c8b-9cf1-6b72aad1001a");
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
                String s1 = bluetoothAdapter.getAddress();
                String s2 = "b7" + s1.substring(0, 1) + "46a40-c758-4868-aa19-7ac6b3475dfc";
                ct = new ConnectingThread(bluetoothDevice, UUID.fromString(s2));
                ct.start();
            }
        });
    }
private class ConnectingThread extends Thread {
    private final BluetoothDevice bluetoothDevice;
    private final BluetoothSocket bluetoothSocket;

    public ConnectingThread(BluetoothDevice device, UUID u) {

        BluetoothSocket temp = null;
        bluetoothDevice = device;


        try {
            temp = bluetoothDevice.createRfcommSocketToServiceRecord(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bluetoothSocket = temp;
    }

    public void run() {
        bluetoothAdapter.cancelDiscovery();

        try {
            bluetoothSocket.connect();
        } catch (IOException connectException) {
            connectException.printStackTrace();
            try {
                bluetoothSocket.close();
            } catch (IOException closeException) {
                closeException.printStackTrace();
            }
        }

        if (bluetoothSocket != null && bluetoothDevice != null) {


            
        }

    }
}}