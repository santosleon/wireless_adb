package com.wireless_abd;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShellExecuter exe = new ShellExecuter();
                String outp = "";
                if(!isConnected) {
                    exe.Executer("su root setprop service.adb.tcp.port 5555");
                    outp = "Connection started";
                    isConnected = true;
                } else {
                    exe.Executer("su root setprop service.adb.tcp.port -1");
                    outp = "Connection finished";
                    isConnected = false;
                }
                exe.Executer("su root stop adbd");
                exe.Executer("su root start adbd");
                btn.setText(outp);
                btn.setTextColor((isConnected) ? 0xFF008060 : 0xFFFF6A00);
            }
        });
    }
}

class ShellExecuter {

    public ShellExecuter() {

    }

    public String Executer(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String response = output.toString();
        return response;

    }
}