package com.example.thispc.mobilequiz;

import android.app.AlertDialog;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Category extends AppCompatActivity {
    public static ConnectedThread connectedThread = null;
   public static BluetoothSocket bluetoothSocket=null;
    public static String OpponentName="";
    public static String Duration;
 // String yourname=Twodevices.MyName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
     // bluetoothSocket = Twodevices.mBluetoothSocket;
       // connectedThread = new ConnectedThread(bluetoothSocket);
       // connectedThread.start();
     //  byte[] ByteArray = ("?"+yourname).getBytes();
      // connectedThread.write(ByteArray);
    }
    public void aptitude(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
     final EditText edittext = new EditText(Category.this);
        alert.setMessage("Duration Should Be In Minutes");
        alert.setTitle("Enter Duration");
        edittext.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);

    alert.setView(edittext);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            Duration = edittext.getText().toString();
                if(Duration!=null)
                {
                    Intent i=new Intent(Category.this,Questions.class);
                i.putExtra("type","1");
                startActivity(i);
                byte[] ByteArray = "1".getBytes();
                    connectedThread.write(ByteArray);
            }
                if(Duration==null)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Duration", Toast.LENGTH_SHORT).show();

                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();

    }
    public void reasoning(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(Category.this);
        edittext.setWidth(30);
        alert.setMessage("Duration Should Be In Minutes");
        alert.setTitle("Enter Duration");
        edittext.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        alert.setView(edittext);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Duration = edittext.getText().toString();
                if(Duration!=null)
                {
                    Intent i=new Intent(Category.this,Questions.class);
                    i.putExtra("type","2");
                    startActivity(i);
                    byte[] ByteArray = "2".getBytes();
                    connectedThread.write(ByteArray);
                }
                if(Duration==null)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Duration", Toast.LENGTH_SHORT).show();

                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
        
    }
        public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private int cnt = 0;

        public ConnectedThread(BluetoothSocket socket) {

            mmSocket = socket;
          bluetoothSocket=socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            // Get the BluetoothSocket input and output streams
       /*    try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {

            }*/
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
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
                    if(readMessage.contains("?"))
                    {
                        OpponentName=readMessage.substring(1);
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
