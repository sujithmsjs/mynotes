package com.suji.cram.ds;

import com.suji.cram.model.Card;

public class ReAsk {

    private static int limit;
    private int count;

    public ReAsk(int times) {
        limit = times;
        count = 1;
    }

    
    public boolean checkAnswer(Card card, String input){
        boolean correct = card.getBack().equalsIgnoreCase(input);
        if(correct){
            count++;
        }else{
            count =1;
        }
        return correct;
    }
    
    public static void main(String[] args) {
        ReAsk ra = new ReAsk(2);
        Card card = new Card("Hi", "Hey", "hehe");
        ra.checkAnswer(card, "Hey");
        ra.checkAnswer(card, "Hey");
        System.out.println(ra.proceed());
       
    }
    
    public boolean proceed(){
        boolean flag = false;
        if(count>limit){
            count = 1;
            flag = true;
            System.out.println("proceeding...");
        }else{
            System.out.println("Can't proceed.");
        }
       return flag;
    }

}
