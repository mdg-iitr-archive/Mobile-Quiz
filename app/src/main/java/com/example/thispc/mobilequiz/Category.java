package com.example.thispc.mobilequiz;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Category extends AppCompatActivity {
    public static ConnectedThread connectedThread = null;
    BluetoothSocket bluetoothSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        bluetoothSocket = Twodevices.mBluetoothSocket;
        connectedThread = new ConnectedThread(bluetoothSocket);
        connectedThread.start();
    }
    public void aptitude(View v)
    {
        Intent i=new Intent(Category.this,Questions.class);
        i.putExtra("type","1");
        startActivity(i);
        byte[] ByteArray = "1".getBytes();
        connectedThread.write(ByteArray);
    }
    public void resoning(View v)
    {
        Intent i=new Intent(Category.this,Questions.class);
        i.putExtra("type","2");
        startActivity(i);
        byte[] ByteArray = "2".getBytes();
        connectedThread.write(ByteArray);
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
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {

            }
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

                         Intent i=new Intent(Category.this,Questions.class);
                         i.putExtra("type","1");
                         startActivity(i);
                    }
                    if (readMessage.equals("2")) {

                        Intent i=new Intent(Category.this,Questions.class);
                        i.putExtra("type","2");
                        startActivity(i);
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
