package com.example.thispc.mobilequiz;

import android.app.AlertDialog;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Questions extends AppCompatActivity {
    public ConnectedThread connectedThread = null;
    BluetoothSocket bluetoothSocket;
    Chronometer mChronometer;
    Button o1;
    Button o2;
    Button o3;
    Button o4;
    int i=1;
    String j;
    int value=0;
    int c=0;
    String ans;
    String duration;
    TextView tv;
    String opposcore=null;
  String OpponentName=(Category.OpponentName).toUpperCase();
  String yourname=(Twodevices.MyName).toUpperCase();
    DataBaseHandler dbh;
    QuestionDetails qd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        duration=Category.Duration;
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,yourname+":\t\t"+c+"\n"+OpponentName+":\t\t"+opposcore, Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();


            }
        });
      bluetoothSocket = Category.bluetoothSocket;

        dbh = new DataBaseHandler(this);
        o1=(Button)findViewById(R.id.button7);
        o2=(Button)findViewById(R.id.button15);
        o3=(Button)findViewById(R.id.button16);
        o4=(Button)findViewById(R.id.button17);
        tv=(TextView)findViewById(R.id.TextView);

      j= getIntent().getExtras().getString("type");
        if(j.equals("1"))
        {
            mChronometer.start();
            aptitude();
        }
        if(j.equals("2"))
        {
            mChronometer.start();
           reasoning();
        }
      connectedThread = new ConnectedThread(bluetoothSocket);
      connectedThread.start();
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (chronometer.getText().toString().equalsIgnoreCase(duration+":"+"00"))
                {
                    mChronometer.stop();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Questions.this);
                   builder.setMessage("Please Wait For Results");
                           builder.setTitle("Time Limit Reached");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();

                   byte[] ByteArray = ("."+c).getBytes();
                   connectedThread.write(ByteArray);
                    if (value > 0) {
                        if (value > c) {
                            Toast.makeText(Questions.this, "You Loose", Toast.LENGTH_LONG).show();
                        }
                        if (value < c) {
                            Toast.makeText(Questions.this, "You Win", Toast.LENGTH_LONG).show();
                        }
                        if (value > c) {
                            Toast.makeText(Questions.this, "Draw", Toast.LENGTH_LONG).show();
                        }
                    }


            }}
        });
    }

public void aptitude() {

    if (Integer.parseInt(mChronometer.getText().toString().substring(0,mChronometer.getText().toString().indexOf(":")+1)) < Integer.parseInt(duration))
    {
        if (i <= 2) {
            qd = dbh.getAptitudeQ(i);
            tv.setText(qd.getQuestion());
            o1.setText(qd.getOption1());
            o2.setText(qd.getOption2());
            o3.setText(qd.getOption3());
            o4.setText(qd.getOption4());
            ans = qd.getAnswer();
        } else {
        byte[] ByteArray =("."+c).getBytes();
      connectedThread.write(ByteArray);
            mChronometer.stop();
            Toast.makeText(Questions.this, "please wait for results", Toast.LENGTH_LONG).show();

            if (value > 0) {
                if (value > c) {
                    Toast.makeText(Questions.this, "You Loose", Toast.LENGTH_LONG).show();
                }
                if (value < c) {
                    Toast.makeText(Questions.this, "You Win", Toast.LENGTH_LONG).show();
                }
                if (value == c) {
                    Toast.makeText(Questions.this, "Draw", Toast.LENGTH_LONG).show();
                }
            }
        }

        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o1.getText().equals(ans)) {
                    c++;
                }
                i++;
                aptitude();
             connectedThread.write(("/"+c).getBytes());
            }
        });
        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o2.getText().equals(ans)) {
                    c++;
                }
                i++;
                aptitude();
              connectedThread.write(("/" + c).getBytes());
            }
        });
        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o3.getText().equals(ans)) {
                    c++;
                }
                i++;
                aptitude();
               connectedThread.write(("/" + c).getBytes());
            }
        });
        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o4.getText().equals(ans)) {
                    c++;
                }
                i++;
                aptitude();
             connectedThread.write(("/" + c).getBytes());
            }
        });
    }else
    {
        Toast.makeText(Questions.this, "Quiz is over", Toast.LENGTH_LONG).show();
    }
}
    public void reasoning() {
        if (Integer.parseInt(mChronometer.getText().toString().substring(0,mChronometer.getText().toString().indexOf(":")+1)) < Integer.parseInt(duration))
        {
        if (i <= 2) {
            qd = dbh.getReasoningQ(i);
            tv.setText(qd.getQuestion());
            o1.setText(qd.getOption1());
            o2.setText(qd.getOption2());
            o3.setText(qd.getOption3());
            o4.setText(qd.getOption4());
            ans = qd.getAnswer();
        } else {
            byte[] ByteArray = ("."+c).getBytes();
       connectedThread.write(ByteArray);
            if (value > 0) {
                if (value > c) {
                    Toast.makeText(Questions.this, "You Loose", Toast.LENGTH_LONG).show();
                }
                if (value < c) {
                    Toast.makeText(Questions.this, "You Win", Toast.LENGTH_LONG).show();
                }
                if (value > c) {
                    Toast.makeText(Questions.this, "Draw", Toast.LENGTH_LONG).show();
                }
            }
        }
        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o1.getText().equals(ans)) {
                    c++;
                }
                i++;
                reasoning();
              connectedThread.write(("/" + c).getBytes());
            }
        });
        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o2.getText().equals(ans)) {
                    c++;
                }
                i++;
                reasoning();
              connectedThread.write(("/" + c).getBytes());
            }
        });
        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o3.getText().equals(ans)) {
                    c++;
                }
                i++;
                reasoning();
                connectedThread.write(("/" + c).getBytes());
            }
        });
        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o4.getText().equals(ans)) {
                    c++;
                }
                i++;
                reasoning();
              connectedThread.write(("/" + c).getBytes());
            }
        });
    }else
        {
            Toast.makeText(Questions.this, "Quiz is over", Toast.LENGTH_LONG).show();
        }
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

            byte[] buffer = new byte[1024];
            int bytes;
            while (true) {
                try {
                    String readMessage = "";
                    bytes = mmInStream.read(buffer);
                    readMessage = new String(buffer, 0, bytes);
                    if(readMessage.contains("."))
                    {
                        value =Integer.valueOf(readMessage.substring(1));
                        if(i>2||mChronometer.getText()==(duration+":"+"00"))
                        {
                            if(value>c)
                            {
                                Toast.makeText( Questions.this,"You Loose",Toast.LENGTH_LONG).show();
                            }
                            if(value<c)
                            {
                                Toast.makeText( Questions.this,"You Win",Toast.LENGTH_LONG).show();
                            }
                            if(value==c)
                            {
                                Toast.makeText( Questions.this,"Draw",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                           if(readMessage.contains("/"))
                            {
                                  opposcore=readMessage.substring(1);
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
