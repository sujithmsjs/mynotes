package com.suji.exp;


public class Account {

    public boolean checkPassword(String username, String passwrod) throws CheckPasswordException{
        
        boolean flag = true;
        
        if(username.equals("Sujith")&&passwrod.equals("1234")) {
            flag = true;
        } else{
            flag = false;
            throw new CheckPasswordException();
        }
        return flag;
    }

}
