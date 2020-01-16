package com.example.loginappmosquera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity implements View.OnClickListener {
    EditText name, lastname, adress;
    Button btnUpdate, btnImg;
    int id = 0;
    Profile p;
    DaoProfile dao;
    Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        name = (EditText) findViewById(R.id.updatename);
        lastname = (EditText) findViewById(R.id.updatelastName);
        adress = (EditText) findViewById(R.id.updateadress);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
        dao = new DaoProfile(this);
        p = dao.getProfilebyId(id);
        name.setText(p.getName());
        lastname.setText(p.getLastname());
        adress.setText(p.getAdress());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:
                Profile p = new Profile();
                p.setName(name.getText().toString());
                p.setLastname(lastname.getText().toString());
                p.setAdress(adress.getText().toString());
                if (!p.isNull()) {
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                } else if (dao.updateProfile(p)) {
                    Toast.makeText(this, "Registro modificado", Toast.LENGTH_LONG).show();
                    Intent i3 = new Intent(Update.this, Data.class);
                    startActivity(i3);
                } else {
                    Toast.makeText(this, "Usuario ya registrado", Toast.LENGTH_LONG).show();
                }
        }
    }
}