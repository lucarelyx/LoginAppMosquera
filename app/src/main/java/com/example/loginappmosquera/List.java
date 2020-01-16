package com.example.loginappmosquera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends AppCompatActivity implements View.OnClickListener {
ImageButton addBtn;
ListView listo;
DaoProfile dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listo = ( ListView)findViewById(R.id.list);
        dao = new DaoProfile(this);
        ArrayList<Profile> l=dao.selectProfiles();
        final ArrayList<String> list = new ArrayList<String>();

        for (Profile p: l) {
            list.add(p.getName());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        listo.setAdapter(a);
        addBtn = (ImageButton)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
        listo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList =(String) (listo.getItemAtPosition(position));

                Intent i3 = new Intent(List.this, Data.class);
                i3.putExtra("id",selectedFromList);
                Toast.makeText(List.this,selectedFromList,Toast.LENGTH_LONG).show();
                startActivity(i3);


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBtn:

                Intent ia = new Intent(List.this,Reg.class);
                startActivity(ia);
                break;
        }
    }
}
