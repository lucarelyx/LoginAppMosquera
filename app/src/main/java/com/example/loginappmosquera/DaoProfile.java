package com.example.loginappmosquera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DaoProfile {
    Context c;
    Profile p;
    ArrayList<Profile> list;
    SQLiteDatabase sql;
    String db="DBProfile";
    String query="create table if not exists profile(id integer primary key autoincrement, name text, lastname text, adress text)";
    //String query2="delete from profile";


    public DaoProfile(Context c){
        this.c = c;
        sql = c.openOrCreateDatabase(db, c.MODE_PRIVATE, null);
        sql.execSQL(query);
        //sql.execSQL(query2);
        p = new Profile();
    }

    public boolean insertProfile(Profile p){
        if(search(p.getName())==0){
            ContentValues cv = new ContentValues();
            cv.put("name",p.getName());
            cv.put("lastname",p.getLastname());
            cv.put("adress",p.getAdress());
            return (sql.insert("profile",null,cv)>0);
        }else{
            return false;
        }
    }

    public int search(String p){
        int x=0;
        list = selectProfiles();
        for (Profile us:list) {
            if (us.getName().equals(p)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Profile> selectProfiles(){
        ArrayList<Profile> list = new ArrayList<Profile>();
        list.clear();
        Cursor cr=sql.rawQuery("select * from profile", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                Profile p = new Profile();
                p.setId(cr.getInt(0));
                p.setName(cr.getString(1));
                p.setLastname(cr.getString(2));
                p.setAdress(cr.getString(3));
                list.add(p);
            }while(cr.moveToNext());
        }
        return list;
    }


    public Profile getProfile(String n){
        list = selectProfiles();
        for (Profile us:list){
            if(us.getName().equals(n)){
                return us;
            }
        }return null;
    }
    public Profile getProfilebyId(int id){
        list = selectProfiles();
        for (Profile us:list){
            if(us.getId()==id){
                return us;
            }
        }return null;
    }

    public boolean updateProfile(Profile p) {
        ContentValues cv = new ContentValues();
        cv.put("name",p.getName());
        cv.put("lastname",p.getLastname());
        cv.put("adress",p.getAdress());
        return (sql.update("profile",cv,"id="+p.getId(),null)>0);
    }
}

