package com.example.thispc.mobilequiz;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Server extends AppCompatActivity {


    int c;

    boolean refreshEnabled = false;

    private BluetoothAdapter bluetoothAdapter;
    public static String MyName = "";
    Button btn;
    EditText name;
    private ListView listview;
    private ArrayAdapter adapter;
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    private static final int DISCOVERABLE_BT_REQUEST_CODE = 2;
    private ArrayList<UUID> mUuids;
    private static final int Finished_Activity = 3;
    private static final int DISCOVERABLE_DURATION = 300;
    public static BluetoothDevice mBluetoothDevice = null;
    boolean check=true;
    public static BluetoothSocket mBluetoothSocket = null;
    ListeningThread t = null;
    public static BluetoothSocket a[];
int a1=0;
int b;

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
        setContentView(R.layout.activity_server);
        btn = (Button) findViewById(R.id.btn_find);
        name = (EditText) findViewById(R.id.myName);
        name.setText(MyName);
        mUuids = new ArrayList<UUID>();
        a=new BluetoothSocket[2];
        mUuids.add(UUID.fromString("b7746a40-c758-4868-aa19-7ac6b3475dfc"));
        mUuids.add(UUID.fromString("2d64189d-5a2c-4511-a074-77f199fd0834"));
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyName = name.getText().toString();
                        if (MyName.trim().equals("")) {
                            name.setError("Enter Name");
                        } else {
                            /*Intent intent = new Intent(TwoDevice2P_names.this, BluetoothActivity.class);
                            startActivity(intent);*/
                            if (bluetoothAdapter == null) {
                                Toast.makeText(getApplicationContext(), "Oops! Your device does not support Bluetooth",
                                        Toast.LENGTH_SHORT).show();
                            } else if (!refreshEnabled) {
                                refreshEnabled = true;
                                btn.setText("STOP");
                                Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                startActivityForResult(enableBluetoothIntent, ENABLE_BT_REQUEST_CODE);
                                Toast.makeText(Server.this, "Bluetooth Enabled", Toast.LENGTH_SHORT).show();
                            } else if (refreshEnabled) {
                                btn.setText("Find Server");
                                refreshEnabled = false;
                                adapter.clear();
                                bluetoothAdapter.disable();
                            }
                        }
                    }
                }
        );


        listview = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter.isEnabled()) {

            adapter.clear();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ENABLE_BT_REQUEST_CODE) {
            // Bluetooth successfully enabled!
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Bluetooth enabled." + "\n" + "Scanning for peers", Toast.LENGTH_SHORT).show();

                makeDiscoverable();
                discoverDevices();

                    t = new ListeningThread();
                    t.start();



                 }else {
                Toast.makeText(getApplicationContext(), "Bluetooth is not enabled.", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == DISCOVERABLE_BT_REQUEST_CODE) {
            if (resultCode == DISCOVERABLE_DURATION) {
                Toast.makeText(getApplicationContext(), "Your device is now discoverable for " + DISCOVERABLE_DURATION + " seconds", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fail to enable discoverable mode.", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == Finished_Activity) {
            bluetoothAdapter.disable();
            adapter.clear();
            refreshEnabled = false;
        }
    }

    protected void discoverDevices() {
        if (bluetoothAdapter.startDiscovery()) {
            Toast.makeText(getApplicationContext(), "Discovering peers", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Discovery failed to start.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void makeDiscoverable() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVERABLE_DURATION);
        startActivityForResult(discoverableIntent, DISCOVERABLE_BT_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(broadcastReceiver);}

    private class ListeningThread extends Thread {
        BluetoothServerSocket bluetoothServerSocket;
        BluetoothServerSocket temp = null;
        public ListeningThread( ) {



        }

        public void run() {
            BluetoothSocket bluetoothSocket=null;
            for (int i = 0; i < mUuids.size(); i++) {
                try {
                    temp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(getString(R.string.app_name), mUuids.get(i));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                check=true;
                bluetoothServerSocket = temp;
                b=0;
                while(check) {
                    try {
                        bluetoothSocket = bluetoothServerSocket.accept();
                    } catch (IOException e) {
                    }
                    for(i=0;i<a1;i++)
                    {
                        if(bluetoothSocket.equals(a[i]))
                        b++;
                    }
                    if(b==0)
                    {
                        a[a1]=bluetoothSocket;
                        check=false;
                        a1++;
                    }

                }
                if (bluetoothSocket != null) {
                    //connected(bluetoothSocket, bluetoothSocket.getRemoteDevice());
                    runOnUiThread(new Runnable() {
                        public void run() {
                            c++;
                            Toast.makeText(getApplicationContext(), "Connection has been accepted."+c, Toast.LENGTH_SHORT).show();
                        }
                    });
                }


               /* try {
                    bluetoothServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }


    }}


}