package com.example.thispc.mobilequiz;
import android.bluetooth.BluetoothSocket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SelectQuestions extends AppCompatActivity {
    DataBaseHandler dbh;
    int c=0;
   public ConnectedThread connectedThread = null;
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
    String ans=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_questions);
        name=Cleint.MyName;
        bluetoothSocket1=Cleint.mbluetoothSocket;
        o1=(Button)findViewById(R.id.button14);
        o2=(Button)findViewById(R.id.button15);
        o3=(Button)findViewById(R.id.button16);
        o4=(Button)findViewById(R.id.button17);
        t=(TextView)findViewById(R.id.textView3);
        playnum1=Cleint.playnum;
        dbh = new DataBaseHandler(this);
       runOnUiThread(new Runnable() {
           public void run() {
               Toast.makeText(getApplicationContext(), "pohonchgya", Toast.LENGTH_SHORT).show();

           }

       });
      connectedThread = new ConnectedThread(bluetoothSocket1);
   connectedThread.start();
      //  display(null);
        }

    public void display(View v)
    {

     connectedThread.write("lodu".getBytes());
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), "pohonchgya", Toast.LENGTH_SHORT).show();

            }
        });
        for (int i=1;i<=Integer.parseInt(Character.valueOf(Cleint.qnumber).toString());i++)
        {
          runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "tatti", Toast.LENGTH_SHORT).show();

                }
            });

         final RandomQuestionsType rqt= dbh.getRandomQuestionsType(i);
          if(rqt!=null)
{


          runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "pohonchgya", Toast.LENGTH_SHORT).show();

                }
            });
}
}


   questions();
    }
public void questions() {
    runOnUiThread(new Runnable() {
        public void run() {
            Toast.makeText(getApplicationContext(), "in questions", Toast.LENGTH_SHORT).show();

        }
    });

    if (j <= Integer.parseInt(Character.valueOf(Cleint.qnumber).toString()))
  {
       rqt2=dbh.getRandomQuestionsType(j);
        if(rqt2.getType().toString().equals("Aptitude"))
        {
            qd= dbh.getAptitudeQ(rqt2.getId2());
           if(qd==null)
           {
               Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
           }
        }
        if(rqt2.getType().toString().equals("Reasoning"))
        {
            qd= dbh.getReasoningQ(rqt2.getId2());
        }
       t.setText(qd.getQuestion());
        o1.setText(qd.getOption1());
        o2.setText(qd.getOption2());
        o3.setText(qd.getOption3());
        o4.setText(qd.getOption4());
        ans = qd.getAnswer();
        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (o1.getText().equals(ans)) {
                    c++;
                }
                j++;
                connectedThread.write(("?" +playnum1+c).getBytes());
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
                connectedThread.write(("?" +playnum1+c).getBytes());
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
                connectedThread.write(("?" +playnum1+c).getBytes());
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
                connectedThread.write(("?" +playnum1+c).getBytes());
                questions();
            }
        });
    }else
    {
        Toast.makeText(SelectQuestions.this, "Quiz is over", Toast.LENGTH_LONG).show();
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
            // Keep listening to the InputStream while connected
            while (true) {
                try {
                    String readMessage = "";
                    bytes = mmInStream.read(buffer);
                    readMessage = new String(buffer, 0, bytes);



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
