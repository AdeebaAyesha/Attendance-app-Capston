package com.example.attendance;

public class Users {

    String use,pass,ids;

    public Users(String use, String pass, String ids) {
        this.use = use;
        this.pass = pass;
        this.ids = ids;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
