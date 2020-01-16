package com.example.loginappmosquera;

public class Profile {
    int id;
    String name, lastname, adress;

    public Profile() {
    }

    public Profile(String name, String lastname, String adress, String image) {
        this.name = name;
        this.lastname = lastname;
        this.adress = adress;

    }

    public boolean isNull(){
        if(name.equals("")&&lastname.equals("")&&adress.equals("")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Profile{" +

                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", adress='" + adress + '\'' +

                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


}
