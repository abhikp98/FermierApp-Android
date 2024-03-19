package com.app.fermierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IpActivity extends AppCompatActivity {
    Button connect;
    EditText ipaddress;
    SharedPreferences sh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ipaddress = findViewById(R.id.editTextTextPersonName);
        connect = findViewById(R.id.button);
        ipaddress.setText(sh.getString("ip", ""));

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ipaddress.getText().toString().isEmpty()){
                    ipaddress.setError("Enter IP first");
                    return;
                }
                SharedPreferences.Editor editor = sh.edit();
                String url = "http://"+ipaddress.getText().toString()+":8000";
                editor.putString("url", url);
                editor.putString("ip", ipaddress.getText().toString());
                editor.commit();
                finish();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}