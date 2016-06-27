package com.example.thispc.mobilequiz;
import android.app.AlertDialog;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SelectQuestions extends AppCompatActivity {
    DataBaseHandler dbh;
    int c=0;
   public ConnectedThread connectedThread = null;
    public Chronometer mChronometer;
    BluetoothSocket bluetoothSocket1=null;
    Button o1;
    Button o2;
    Button o3;
    Button o4;
    TextView t;
    int j=1;
    public static char playnum1;
    RandomQuestionsType rqt2=null;
    QuestionDetails qd=null;
    public static String name;
    public static String duration;
    String ans=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_questions);
        duration=Cleint.Duration;
        mChronometer = (Chronometer) findViewById(R.id.chronometer1);
        mChronometer.start();
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (chronometer.getText().toString().equalsIgnoreCase(duration+":"+"00"))
                {
                    connectedThread.write(("=" +playnum1+c+">"+name).getBytes());
                    mChronometer.stop();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SelectQuestions.this);
                    builder.setMessage("Please Wait For Results");
                    builder.setTitle("Time Limit Reached");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, name + ":\t\t" + c , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
        name=Cleint.MyName;
        playnum1=Cleint.playnum;
        bluetoothSocket1=Cleint.mbluetoothSocket;
        o1=(Button)findViewById(R.id.button14);
        o2=(Button)findViewById(R.id.button15);
        o3=(Button)findViewById(R.id.button16);
        o4=(Button)findViewById(R.id.button17);
        t=(TextView)findViewById(R.id.textView3);
        dbh = new DataBaseHandler(this);
       runOnUiThread(new Runnable() {
           public void run() {
               Toast.makeText(getApplicationContext(), "pohonchgya", Toast.LENGTH_SHORT).show();

           }

       });
      connectedThread = new ConnectedThread(bluetoothSocket1);
   connectedThread.start();
   display(null);
        }

    public void display(View v)
    {
       mChronometer.start();
        if(connectedThread!=null) {
   // connectedThread.write("lodu".getBytes());
    runOnUiThread(new Runnable() {
        public void run() {
            Toast.makeText(getApplicationContext(), "connected Thread sending lodu", Toast.LENGTH_SHORT).show();

        }
    });
  //  connectedThread.write(("." + playnum1 + name).getBytes());

        }else
    {
    runOnUiThread(new Runnable() {
        public void run() {
            Toast.makeText(getApplicationContext(), "connected Thread is null", Toast.LENGTH_SHORT).show();

        }
    });
    }
       /* runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), "pohonchgya", Toast.LENGTH_SHORT).show();

            }
        });*/
        for (int i=1;i<=Cleint.a;i++)//Integer.parseInt(Character.valueOf(Cleint.qnumber).toString());i++)
        {
          /*runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "tatti", Toast.LENGTH_SHORT).show();

                }
            });*/

         final RandomQuestionsType rqt= dbh.getRandomQuestionsType(i);
          if(rqt!=null)
{


       /*   runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "pohonchgya", Toast.LENGTH_SHORT).show();

                }
            });*/
}
}


questions();
    }
public void questions() {


   if (Integer.parseInt(mChronometer.getText().toString().substring(0,mChronometer.getText().toString().indexOf(":"))) < Integer.parseInt(duration))
    {
    runOnUiThread(new Runnable() {
        public void run() {
            Toast.makeText(getApplicationContext(), "in questions", Toast.LENGTH_SHORT).show();

        }
    });

    if (j <= Cleint.a)
  {
       rqt2=dbh.getRandomQuestionsType(j);
        if(rqt2.getType().toString().equals("Aptitude"))
        {
            qd= dbh.getAptitudeQ(rqt2.getId2());
        }
        if(rqt2.getType().toString().equals("Reasoning"))
        {
            qd= dbh.getReasoningQ(rqt2.getId2());
        }
      if(rqt2.getType().toString().equals("Verbal"))
      {
          qd= dbh.getVerbalQ(rqt2.getId2());
      }
      if(rqt2.getType().toString().equals("Engg"))
      {
          qd= dbh.getEnggQ(rqt2.getId2());
      }
       t.setText(qd.getQuestion());
        o1.setText(qd.getOption1());
        o2.setText(qd.getOption2());
        o3.setText(qd.getOption3());
        o4.setText(qd.getOption4());
        ans = qd.getAnswer();
    /*  if(Cleint.reach>0)
      {
          runOnUiThread(new Runnable() {
              public void run() {
                  Toast.makeText(getApplicationContext(), "reach is greater than zero", Toast.LENGTH_SHORT).show();

              }
          });
      }*/
        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o1.getText().equals(ans)) {
                    c++;
                }
                j++;
                if(j < Cleint.a+1)
                connectedThread.write(("?" +playnum1+c+">"+name).getBytes());
                if(j == Cleint.a+1)
                    connectedThread.write(("=" +playnum1+c+">"+name).getBytes());
                    questions();
            }
        });
        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o2.getText().equals(ans)) {
                    c++;
                }
                j++;
                if(j < Cleint.a+1)
                    connectedThread.write(("?" +playnum1+c+">"+name).getBytes());
                if(j == Cleint.a+1)
                    connectedThread.write(("=" +playnum1+c+">"+name).getBytes());
                questions();
            }
        });
        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o3.getText().equals(ans)) {
                    c++;
                }
                j++;
                if(j < Cleint.a+1)
                    connectedThread.write(("?" +playnum1+c+">"+name).getBytes());
                if(j == Cleint.a+1)
                    connectedThread.write(("=" +playnum1+c+">"+name).getBytes());
                questions();
            }
        });
        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o4.getText().equals(ans)) {
                    c++;
                }
                j++;
                if(j < Cleint.a+1)
                    connectedThread.write(("?" +playnum1+c+">"+name).getBytes());
                if(j == Cleint.a+1)
                    connectedThread.write(("=" +playnum1+c+">"+name).getBytes());
                questions();
            }
        });
    }else
    {
        Toast.makeText(SelectQuestions.this, "You finished Quiz Before Time......Please Wait For Results", Toast.LENGTH_LONG).show();
        o1.setEnabled(false);
        o2.setEnabled(false);
        o3.setEnabled(false);
        o4.setEnabled(false);
    }
}}
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

            // Keep listening to the InputStream while connected
            while (true) {
                try {
                    String readMessage = "";
                    bytes = mmInStream.read(buffer);
                    readMessage = new String(buffer, 0, bytes);
                 /*  if(readMessage.contains("reached"))
                    {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "reached", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }*/


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
