package com.example.loginappmosquera;

public class User {

    int id;
    String user, password;

    public User() {
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public boolean isNull(){
        if(user.equals("")&&password.equals("")){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public String toString() {
        return "User{" +

                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
