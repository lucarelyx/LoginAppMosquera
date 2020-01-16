package com.example.loginappmosquera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DaoUser {
    Context c;
    User u;
    ArrayList<User> list;
    SQLiteDatabase sql;
    String db="DBUser";
    String query="create table if not exists user(id integer primary key autoincrement, user text, password text)";

    public DaoUser(Context c){
        this.c = c;
        sql = c.openOrCreateDatabase(db, c.MODE_PRIVATE, null);
        sql.execSQL(query);
        u = new User();
    }

    public boolean insertUser(User u){
        if(search(u.getUser())==0){
            ContentValues cv = new ContentValues();
            cv.put("user",u.getUser());
            cv.put("password",u.getPassword());
            return (sql.insert("user",null,cv)>0);
        }else{
            return false;
        }
    }

    public int search(String u){
        int x=0;
        list = selectUsers();
        for (User us:list) {
            if (us.getUser().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<User> selectUsers(){
        ArrayList<User> list = new ArrayList<User>();
        list.clear();
        Cursor cr=sql.rawQuery("select * from user", null);
        if(cr!=null&&cr.moveToFirst()){
           do{
                User u = new User();
                u.setId(cr.getInt(0));
               u.setUser(cr.getString(1));
               u.setPassword(cr.getString(2));
               list.add(u);
           }while(cr.moveToNext());
        }
        return list;
    }

    public int logIn(String u, String p){
        int a = 0;
        Cursor cr=sql.rawQuery("select * from user", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                if (cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while(cr.moveToNext());
        }
        return a;
    }

    public User getUser(String u, String p){
        list = selectUsers();
        for (User us:list){
            if(us.getUser().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        }return null;
    }
    public User getUserbyId(int id){
        list = selectUsers();
        for (User us:list){
            if(us.getId()==id){
                return us;
            }
        }return null;
    }
}
