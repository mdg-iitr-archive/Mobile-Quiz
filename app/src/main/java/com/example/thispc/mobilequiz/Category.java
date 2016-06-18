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
    public static String opposcore;
 String yourname=Twodevices.MyName;
    public static int value =-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
     bluetoothSocket = Twodevices.mBluetoothSocket;
      connectedThread = new ConnectedThread(bluetoothSocket);
       connectedThread.start();
      byte[] ByteArray = ("?"+yourname).getBytes();
      connectedThread.write(ByteArray);
    }
    public void aptitude(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
     final EditText edittext = new EditText(Category.this);
        alert.setMessage("Duration Should Be In Minutes");
        alert.setTitle("Enter Duration");
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER);

         alert.setView(edittext);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            Duration = edittext.getText().toString();
                if(Duration!=null)
                {
                    if(Duration.length()==1)
                    {
                        Duration="0"+Duration;
                    }
                    Intent i=new Intent(Category.this,Questions.class);
                     i.putExtra("type", "1");
                     startActivity(i);
                    connectedThread.write(("1" + "<" + Duration).getBytes());



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
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setView(edittext);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Duration = edittext.getText().toString();

                if (Duration != null) {
                    if (Duration.length() == 1) {
                        Duration = "0" + Duration;
                    }
                    Intent i = new Intent(Category.this, Questions.class);
                    i.putExtra("type", "2");
                    startActivity(i);
                    connectedThread.write(("2" + "<" + Duration).getBytes());

                }
                if (Duration == null) {
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
         try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {

            }
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
                    if (String.valueOf(readMessage.charAt(0)).equals("1")) {
                         Duration=readMessage.substring(readMessage.indexOf("<")+1);
                         Intent i=new Intent(Category.this,Questions.class);
                         i.putExtra("type","1");
                         startActivity(i);
                    }
                    if (String.valueOf(readMessage.charAt(0)).equals("2")) {

                        Duration=readMessage.substring(readMessage.indexOf("<")+1);
                        Intent i=new Intent(Category.this,Questions.class);
                        i.putExtra("type","2");
                        startActivity(i);
                    }
                    if(readMessage.contains("?"))
                    {
                        OpponentName=readMessage.substring(1);
                    }
                    if(readMessage.contains("/"))
                    {
                        opposcore=readMessage.substring(1);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(Category.this, "opposcore" + opposcore, Toast.LENGTH_LONG).show();

                            }

                        });
                    }
                    if(readMessage.contains("."))
                    {
                        value =Integer.valueOf(readMessage.substring(1));
                        if(Questions.i>2||Questions.mChronometer.getText()==(Duration+":"+"00"))
                        {
                            if(value>Questions.c)
                            {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(Category.this, "You Loose", Toast.LENGTH_LONG).show();

                                    }

                                });
                            }
                            if(value<Questions.c)
                            {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(Category.this, "You Win", Toast.LENGTH_LONG).show();

                                    }

                                });
                            }
                            if(value==Questions.c)
                            {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(Category.this, "Draw", Toast.LENGTH_LONG).show();

                                    }

                                });
                            }
                        }
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
