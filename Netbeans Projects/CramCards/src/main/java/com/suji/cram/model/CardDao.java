package com.suji.cram.model;


import com.suji.cram.db.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CardDao {
    

    public static String[] getAllGroups(){
        //select distinct  cards.group from cards
        String[] list = new String[]{"Default","Vocabulary","Acronym","Knowledge"};
        
        return list;
    }
    
    public static int addAllCards(List<Card> cardsList, String group){
        int count = 0;
        for (Card card : cardsList) {
        
            try {
                if(addCard(card.getFront(),card.getBack(),card.getHint(),group)){
                    count++;
                }
            } catch (Exception e) {
                System.out.println("Index:"+ count+"; Results "+e.getMessage());
            }
        }
        return count;
    }

    

    
    public static boolean addCard(String front, String back, String hint, String group) throws SQLException {
        boolean isSaved = false;

        String sqlQuery = "insert into cards(front,back,hint,cards.group) values(?,?,?,?)";
        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);
        ps.setString(1, front);
        ps.setString(2, back);
        ps.setString(3, hint);
        ps.setString(4, group);

        isSaved = ps.executeUpdate() > 0;

        return isSaved;
    }
    
    public static LinkedList<Card> getWords(int limit, int offset,String group) throws SQLException{
        String sqlQuery = "select * from cram.cards where cards.group=? order by score limit ? offset ?";
        LinkedList<Card> list = new LinkedList<>();
        
        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);
        ps.setString(1, group);
        ps.setInt(2, limit);
        ps.setInt(3, offset);

        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Card word = new Card(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            list.add(word);
        }
        
        return list;
    }
/*
    public static List<Card> getWords(int limit, int offset) throws SQLException {

        String sqlQuery = "select * from cards order by score limit ? offset ?";
        List<Card> list = new ArrayList<>();
        
            Connection con = DBUtil.getConn();
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Card word = new Card(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getInt(6));

                //System.out.println(word);
                list.add(word);
            }

        return list;

    }
*/
    public static boolean addCard(Card card) throws SQLException {
        return addCard(card.getFront(),card.getBack(),card.getHint(), card.getGroup());
    }
    
    
    public static boolean setScore(Card card, int n) throws SQLException{
        return setScore(card.getSno(), n);
    }
    
    
    
    public static boolean setScore(int sno, int n) throws SQLException{
        
        String q = "update cards set score=score+"+n+" where cid="+sno;
        Connection con = DBUtil.getConn();
        int count = con.createStatement().executeUpdate(q);
        //System.out.println(q);
        //PreparedStatement ps = con.prepareStatement(q);
        //ps.setInt(1, n);
        //ps.setInt(1, sno);

        return count>0;
    }
    
    public static void main(String[] args) throws SQLException {
        
        if(CardDao.setScore(13, 10)){
            System.out.println("Wroking...");
        }
    }


    

}
