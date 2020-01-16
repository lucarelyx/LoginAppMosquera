package com.example.loginappmosquera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
EditText user, password;
Button btnLogIn;
DaoUser dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.password);
        btnLogIn=(Button)findViewById(R.id.btnLogin);
        btnLogIn.setOnClickListener(this);
        dao = new DaoUser(this);

        CreateUser("usuario@usuario.com","123456789");
        CreateUser("admin@usuario.com","admin123");

    }

    @Override
    public void onClick(View v) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        switch (v.getId()){
            case R.id.btnLogin:
                String u=user.getText().toString();
                String p=password.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                }else if(user.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(),"ERROR: Ingrese un email",Toast.LENGTH_SHORT).show();
            }else if (user.getText().toString().trim().matches(emailPattern)) {
                     if(dao.logIn(u,p)==1){
                        //User ux = dao.getUser(u,p);
                        Toast.makeText(this,"Datos Correctos",Toast.LENGTH_LONG).show();
                        Intent i2 = new Intent(MainActivity.this,List.class);
                        //i2.putExtra("Id",ux.getId());
                        startActivity(i2);
                    }else {
                         Toast.makeText(getApplicationContext(),"ERROR: Usuario o contraseña invalidos", Toast.LENGTH_SHORT).show();
                     }}else if(!user.getText().toString().trim().matches(emailPattern)){
                    Toast.makeText(getApplicationContext(),"ERROR: Ingrese un email válido", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void CreateUser(String us, String p){
        User u = new User();
        u.setUser(us);
        u.setPassword(p);
        dao.insertUser(u);
    }
}
