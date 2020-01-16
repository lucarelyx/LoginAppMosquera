package com.example.loginappmosquera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reg extends AppCompatActivity implements View.OnClickListener{
EditText name, lastname, adress;
Button btnCreate;
DaoProfile dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        name = (EditText)findViewById(R.id.createname);
        lastname = (EditText)findViewById(R.id.createlastName);
        adress = (EditText)findViewById(R.id.createadress);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        dao = new DaoProfile(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnCreate){
            Profile p = new Profile();
            p.setName(name.getText().toString());
            p.setLastname(lastname.getText().toString());
            p.setAdress(adress.getText().toString());
            if(!p.isNull()){
                Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
            }else if(dao.insertProfile(p)){
                Toast.makeText(this,"Registro creado",Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(Reg.this,List.class);
                startActivity(i2);
            }else{
                Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
            }
        }
    }
}
