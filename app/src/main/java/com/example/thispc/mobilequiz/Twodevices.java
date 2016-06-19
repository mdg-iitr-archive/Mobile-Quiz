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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class Twodevices extends AppCompatActivity {


  public static String MyName = "";
    public static String OpponentName;
    Button btn;
    EditText name;
    public  static String Duration;
    public static int value=-1;
    int a=0;
    DataBaseHandler dbh;
    private ArrayList<UUID> mUuids;
    boolean refreshEnabled = false;
    ConnectedThread connectedThread=null;
    private BluetoothAdapter bluetoothAdapter;
    private ListView listview;
    private ArrayAdapter adapter;
    public static char qnumber='a';
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    private static final int DISCOVERABLE_BT_REQUEST_CODE = 2;
    private static final int Finished_Activity = 3;
    private static final int DISCOVERABLE_DURATION = 300;
    public static BluetoothDevice mBluetoothDevice = null;
    public static BluetoothSocket mBluetoothSocket = null;
    public static BluetoothSocket mbluetoothSocket = null;
    public static String opposcore;

    ListeningThread t = null;
    ConnectingThread ct = null;
    private  static final UUID uuid = UUID.fromString("fc5ffc49-00e3-4c8b-9cf1-6b72aad1001a");
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
        dbh=new DataBaseHandler(this);
        setContentView(R.layout.activity_twodevices);
        btn = (Button) findViewById(R.id.btn_find);
        name = (EditText) findViewById(R.id.myName);
        name.setText(MyName);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyName = name.getText().toString();
                        if (MyName.trim().equals("")) {
                            name.setError("Enter Name");
                        } else {
                            if (bluetoothAdapter == null) {
                                Toast.makeText(getApplicationContext(), "Oops! Your device does not support Bluetooth",
                                        Toast.LENGTH_SHORT).show();
                            } else if (!refreshEnabled) {
                                refreshEnabled = true;
                                btn.setText("STOP");
                                Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                startActivityForResult(enableBluetoothIntent, ENABLE_BT_REQUEST_CODE);
                                Toast.makeText(Twodevices.this, "Bluetooth Enabled", Toast.LENGTH_SHORT).show();
                            } else if (refreshEnabled) {
                                btn.setText("Find Opponent");
                                refreshEnabled = false;
                                adapter.clear();
                                bluetoothAdapter.disable();
                            }
                        }
                    }
                }
        );

        listview = (ListView) findViewById(R.id.listView);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listview.getItemAtPosition(position);
                String MAC = itemValue.substring(itemValue.length() - 17);
                BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(MAC);



                try {
                    ct = new ConnectingThread(bluetoothDevice);
                    ct.start();

                } catch (Exception e) {
                }





            }
        });

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter.isEnabled())
        {
            bluetoothAdapter.disable();
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

            } else {
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
            btn.setText("Find Opponent");
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
        this.unregisterReceiver(broadcastReceiver);
    }

    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {

        mBluetoothDevice = device;
        mBluetoothSocket = socket;
        connectedThread = new ConnectedThread(socket);
        connectedThread.start();

    }

    private class ListeningThread extends Thread {
        private final BluetoothServerSocket bluetoothServerSocket;

        public ListeningThread() {
            BluetoothServerSocket temp = null;
            try {
                temp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(getString(R.string.app_name), uuid);

            } catch (IOException e) {
                e.printStackTrace();
            }
            bluetoothServerSocket = temp;
        }

        public void run() {
            BluetoothSocket bluetoothSocket;
            while (true) {
                try {
                    bluetoothSocket = bluetoothServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                if (bluetoothSocket != null) {

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "A connection has been accepted.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    connected(bluetoothSocket, bluetoothSocket.getRemoteDevice());

                    try {
                        bluetoothServerSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        public void cancel() {
            try {
                bluetoothServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ConnectingThread extends Thread {
        private final BluetoothDevice bluetoothDevice;
        private final BluetoothSocket bluetoothSocket;

        public ConnectingThread(BluetoothDevice device) {

            BluetoothSocket temp = null;
            bluetoothDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                temp = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
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

            if (bluetoothSocket!= null && bluetoothDevice != null) {
                connected(bluetoothSocket, bluetoothDevice);
            }

        }

        // Cancel an open connection and terminate the thread
        public void cancel() {
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private int cnt = 0;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            mBluetoothSocket=socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {

            }
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "in connected thread" , Toast.LENGTH_SHORT).show();
                }
            });
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;
            byte[] ByteArray = ("?"+MyName).getBytes();
            connectedThread.write(ByteArray);
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "in run", Toast.LENGTH_SHORT).show();
                }
            });
            String score="0";
            // Keep listening to the InputStream while connected
            while (true) {
                try {

                    // Read from the InputStream
                    String readMessage = "";
                    bytes = mmInStream.read(buffer);
                    readMessage = new String(buffer, 0, bytes);
                    if(readMessage.contains("+")) {
                        Duration = readMessage.substring(1, readMessage.indexOf("/"));
                        OpponentName=readMessage.substring(readMessage.indexOf("/")+1);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "duration" + Duration, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    if(readMessage.contains(";"))
                    {
                        mbluetoothSocket=mBluetoothSocket;
                        RandomQuestionsType rqt=new RandomQuestionsType(Integer.parseInt(readMessage.substring(1,readMessage.indexOf('['))),Integer.parseInt(readMessage.substring(readMessage.indexOf('[')+1,readMessage.indexOf(']'))),readMessage.substring(readMessage.indexOf(']')+1));
                        dbh.adRandomQuestionsType(rqt);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                a++;
                                Toast.makeText(getApplicationContext(), "in ;" + a +dbh.getRandomQuestionsType(a).getType(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    if(readMessage.contains("..."))
                    {
                      qnumber=(readMessage.charAt(3));
                        runOnUiThread(new Runnable() {
                            public void run() {

                               Toast.makeText(getApplicationContext(), "in ............."+qnumber, Toast.LENGTH_SHORT).show();
                            }
                        });
                        Intent ic=new Intent(Twodevices.this,Questions.class);
                        startActivity(ic);
                    }
                    if(readMessage.contains("/")) {
                        if (readMessage.contains(".")) {
                            opposcore = readMessage.substring(2);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(Twodevices.this, "opposcore" + opposcore, Toast.LENGTH_LONG).show();

                                }

                            });
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(Twodevices.this, "last question of opponent", Toast.LENGTH_LONG).show();

                                }

                            });
                            value = Integer.valueOf(String.valueOf(readMessage.charAt(1)));
                            if (Questions.i > qnumber || Questions.mChronometer.getText() == (Duration + ":" + "00")) {
                                if (value > Questions.c) {
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(Twodevices.this, "You Loose", Toast.LENGTH_LONG).show();

                                        }

                                    });
                                }
                                if (value < Questions.c) {
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(Twodevices.this, "You Win", Toast.LENGTH_LONG).show();

                                        }

                                    });
                                }
                                if (value == Questions.c) {
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(Twodevices.this, "Draw", Toast.LENGTH_LONG).show();

                                        }

                                    });
                                }

                            }
                        } else {
                            opposcore = readMessage.substring(1);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(Twodevices.this, "opposcore" + opposcore, Toast.LENGTH_LONG).show();

                                }

                            });
                        }
                    }
                } catch (Exception e) {


                }
            }
        }

        public void write(byte[] buffer) {

            try {
                mmOutStream.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {

            }
        }
    }


}


