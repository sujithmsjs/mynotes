package com.suji.cram.ds;




import com.suji.cram.model.Card;
import com.suji.cram.model.CardDao;
import com.suji.cram.model.GroupDao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;


public class TestIterator{

    private LinkedList<Card> cardsList;
    private int pageNo = 0;
    private static final int LIMIT = 10;
    private String group;

    public TestIterator(String group) {
        this.group = group;
    }
    
    public static void main(String[] args) throws SQLException {
        TestIterator tg = new TestIterator("Avoid Very");
        while(tg.hasNext()){
            System.out.println(tg.next());
        }
        
        tg.reset();
        System.out.println("Secound");
        while(tg.hasNext()){
            System.out.println(tg.next());
        }
        
    }

    private void loadCards() throws SQLException {
        System.out.println("\nPage: "+pageNo);
        cardsList = CardDao.getWords(LIMIT, LIMIT * pageNo, group);
        pageNo++;
    }


    public boolean hasNext() throws SQLException {
        
            if (cardsList == null || cardsList.isEmpty()) {
                loadCards();
            }
        return !cardsList.isEmpty();
    }


    public Card next() {
       return cardsList.poll();
    }

    public void reset() {
        pageNo = 0;
    }


  

}
