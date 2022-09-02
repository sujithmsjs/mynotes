package com.suji.exp;


public class CheckPasswordException  extends Exception{
    
    public CheckPasswordException() {
        super("Wrong Passwrod! Check your password and try again.");
    }
}