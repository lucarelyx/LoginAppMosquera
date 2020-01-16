package com.example.loginappmosquera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Data extends AppCompatActivity implements View.OnClickListener {
    EditText name, lastname, adress;
    Button btnUpdate1;
    String id;
    Profile p;
    DaoProfile dao;
    Intent x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);
        name = (EditText) findViewById(R.id.name);
        lastname = (EditText) findViewById(R.id.lastName);
        adress = (EditText) findViewById(R.id.adress);
        btnUpdate1 = (Button) findViewById(R.id.btnUpdate1);
        btnUpdate1.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getString("id");
        dao = new DaoProfile(this);
        p = dao.getProfile(id);
        name.setText(p.getName());
        lastname.setText(p.getLastname());
        adress.setText(p.getAdress());
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnUpdate1){
            Intent iu = new Intent(Data.this,Update.class);
            iu.putExtra("id",p.getId());
            startActivity(iu);

        }
    }
}
