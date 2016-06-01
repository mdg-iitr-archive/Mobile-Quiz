package com.example.thispc.mobilequiz;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Questions extends AppCompatActivity {
    public ConnectedThread connectedThread = null;
    BluetoothSocket bluetoothSocket;
    Button o1;
    Button o2;
    Button o3;
    Button o4;
    int i=1;
    String j;
    int value=0;
    int c=0;
    String ans;
    TextView tv;
    DataBaseHandler dbh;
    QuestionDetails qd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bluetoothSocket = Category.bluetoothSocket;

        dbh = new DataBaseHandler(this);
        o1=(Button)findViewById(R.id.button7);
        o2=(Button)findViewById(R.id.button9);
        o3=(Button)findViewById(R.id.button10);
        o4=(Button)findViewById(R.id.button11);
        tv=(TextView)findViewById(R.id.TextView);

       j= getIntent().getExtras().getString("type");
        if(j.equals("1"))
        {
            aptitude();
        }
        if(j.equals("2"))
        {
           reasoning();
        }
        connectedThread = new ConnectedThread(bluetoothSocket);
        connectedThread.start();
    }
public void aptitude()
{

    if(i<=2)
    {
qd=dbh.getAptitudeQ(i);
    tv.setText(qd.getQuestion());
    o1.setText(qd.getOption1());
    o2.setText(qd.getOption2());
    o3.setText(qd.getOption3());
    o4.setText(qd.getOption4());
        ans=qd.getAnswer();
    }else
    {
        byte[] ByteArray =(c+"").getBytes();
        connectedThread.write(ByteArray);
        if(value>0)
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

    o1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           byte[] ByteArray = "fool".getBytes();
            connectedThread.write(ByteArray);
if(o1.getText().equals(ans))
{
    c++;
}
            i++;
            aptitude();
        }
    });
    o2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(o2.getText().equals(ans))
            {
                c++;
            }
            i++;
            aptitude();

        }
    });
    o3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(o3.getText().equals(ans))
            {
                c++;
            }
            i++;
            aptitude();

        }
    });
    o4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(o4.getText().equals(ans))
            {
                c++;
            }
            i++;
            aptitude();

        }
    });

}
    public void reasoning()
    {
        if(i<=2) {
            qd = dbh.getReasoningQ(i);
            tv.setText(qd.getQuestion());
            o1.setText(qd.getOption1());
            o2.setText(qd.getOption2());
            o3.setText(qd.getOption3());
            o4.setText(qd.getOption4());
            ans=qd.getAnswer();
        }else
        {
            byte[] ByteArray =(c+".").getBytes();
            connectedThread.write(ByteArray);
            if(value>0)
            {
                if(value>c)
                {
                    Toast.makeText( Questions.this,"You Loose",Toast.LENGTH_LONG).show();
                }
                if(value<c)
                {
                    Toast.makeText( Questions.this,"You Win",Toast.LENGTH_LONG).show();
                }
                if(value>c)
                {
                    Toast.makeText( Questions.this,"Draw",Toast.LENGTH_LONG).show();
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

            }
        });
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

                    // Read from the InputStream
                    String readMessage = "";
                    bytes = mmInStream.read(buffer);
                    readMessage = new String(buffer, 0, bytes);

                    if(readMessage.equals("fool"))
                    {
                        Toast.makeText( Questions.this,"foool",Toast.LENGTH_LONG).show();}
                    if(readMessage.contains("."))
                    {
                        value =Integer.valueOf(readMessage.charAt(0));
                        if(i>2)
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
