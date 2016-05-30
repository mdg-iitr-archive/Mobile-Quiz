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

import java.util.ArrayList;
import java.util.UUID;

public class Server extends AppCompatActivity {
    public static String MyName = "";
    public static String OpponentName;
    Button btn;
    EditText name;
    int c;

    boolean refreshEnabled = false;

    private BluetoothAdapter bluetoothAdapter;
    private ListView listview;
    private ArrayAdapter adapter;
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    private static final int DISCOVERABLE_BT_REQUEST_CODE = 2;
    private ArrayList<UUID> mUuids;





    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String s1=bluetoothDevice.getAddress();
                String s2="b774aa40-c758-4868-aa19-7ac6b3475d"+s1.substring(7,8).toLowerCase();
                mUuids.add(UUID.fromString(s2));
                adapter.add(bluetoothDevice.getName() + "\n" +bluetoothDevice.getAddress() );
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        mUuids = new ArrayList<UUID>();
        c=0;
        setContentView(R.layout.activity_main);

                            if (bluetoothAdapter == null) {
                                Toast.makeText(getApplicationContext(), "Device does not support Bluetooth",
                                        Toast.LENGTH_SHORT).show();
                            } else if (!refreshEnabled) {
                                refreshEnabled = true;
                                btn.setText("STOP");
                                Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                startActivityForResult(enableBluetoothIntent, ENABLE_BT_REQUEST_CODE);
                                Toast.makeText(Server.this, "Bluetooth Enabled", Toast.LENGTH_SHORT).show();
                            } else if (refreshEnabled) {
                                refreshEnabled = false;
                                adapter.clear();
                                bluetoothAdapter.disable();
                            }
                        }
                    }

