package iyp.cookbook.account;

import java.io.Serializable;

/**
 * Created by yunus on 03/31/2017.
 */

public class Account implements Serializable {
    private String uname,pass,mail,address,phone,realname;
    public Account(String uname, String pass, String realname, String mail, String address, String phone) {
        this.uname=uname;
        this.pass=pass;
        this.mail=mail;
        this.realname=realname;
        this.address=address;
        this.phone=phone;
    }
    public String getUname(){
        return uname;
    }
    public String getRealname(){
        return realname;
    }
    public String getMail(){
        return mail;
    }
    public String getAddress(){
        return address;
    }
    public String getPass(){
        return pass;
    }
    public String getPhone(){
        return phone;
    }
}
