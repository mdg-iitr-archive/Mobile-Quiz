package com.example.thispc.mobilequiz;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Questions extends AppCompatActivity {
    public ConnectedThread connectedThread = null;
    BluetoothSocket bluetoothSocket;
    String j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bluetoothSocket = Category.bluetoothSocket;
        connectedThread = new ConnectedThread(bluetoothSocket);
        connectedThread.start();
        Intent i= getIntent();
        Bundle b = i.getExtras();

        if(b!=null) {
            String j = (String) b.get("type");
        }
        if(j=="1")
        {
            aptitude();
        }
        if(j=="2")
        {
           reasoning();
        }
    }
public void aptitude()
{

}
    public void reasoning()
    {

    }

    public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private int cnt = 0;

        public ConnectedThread(BluetoothSocket socket) {

            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            // Get the BluetoothSocket input and output streams
          /*  try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {

            }*/
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {

            if (cnt == 0) {
                try {
                    byte[] ByteArray = Twodevices.MyName.getBytes();
                    connectedThread.write(ByteArray);
                    cnt++;
                } catch (Exception e) {

                }
            }
            byte[] buffer = new byte[1024];
            int bytes;
            // Keep listening to the InputStream while connected
            while (true) {
                try {

                    // Read from the InputStream
                    String readMessage = "";
                    bytes = mmInStream.read(buffer);
                    readMessage = new String(buffer, 0, bytes);


                    if (readMessage.equals("1")) {


                    }
                } catch (Exception e) {
                    //Log.e(TAG, "disconnected", e);
                    break;
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
