package mypro.util;

import java.util.ArrayList;
import java.util.Random;

public class GetData {

    private static Random r = new Random();
    private static String letters[] = {"R", "T", "P", "S", "D", "F", "G", "K", "L", "B", "N", "M"};
    private static String vowels[] = {"a", "e", "u", "i", "o"};
    private static ArrayList<String> al = new ArrayList<String>();
    private static ArrayList<String> sections = new ArrayList<String>();
    private static ArrayList<String> schools = new ArrayList<String>();
    private static ArrayList<java.sql.Date> dates = new ArrayList<java.sql.Date>();
    
    static{
        setSec();
        setSchool();
        setDate();
        setName();
    }
    
    public static void main(String[] args) {
        //GetData.getName();
        for (int i = 0; i < 50; i++) {
            System.out.println(GetData.getDate());
        }

    }

    public static String getGender() {
        String gender[] = {"F", "M","O"};
        return gender[r.nextInt(3)];
    }
    
    public static String getEmail(){
        return GetData.getName(4,6,true)+"@"+GetData.getName(3,4,false)+".com";
    }

    public static String getName(){
        return al.get(r.nextInt(40));
    }
    
    public static String getSection(){
        return sections.get(r.nextInt(3));
    }
    public static String getSchool(){
        return schools.get(r.nextInt(5));
    }
    
    public static java.sql.Date getDatte(){
        return dates.get(r.nextInt(80));
    }
    
    public static String getName(int min,int max,boolean haveNulls) {
        int len = getRandomInt(min, max,haveNulls); // 0-
        String name = "";
        String gen = "";
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                name += letters[r.nextInt(12)];
            } else {
                name += vowels[r.nextInt(5)];
            }
        }
        return name.toLowerCase();
    }

    public static java.sql.Date getDate() {
        
        int year = getRandomInt(1990, 2012,false);
        int month = getRandomInt(1, 12,false);
        int date = getRandomInt(1, 31,false);
        String d = year+"-"+month+"-"+date;
        java.sql.Date sqlDate = java.sql.Date.valueOf(d);
        
        if(r.nextInt(5)==0)
            sqlDate = java.sql.Date.valueOf("1994-9-2");
        
        return sqlDate;
    }

    public static double getRandomDecimal(int len, int pre) {
        double val = r.nextDouble();
        val = Math.round(val * Math.pow(10, len));
        val = val / Math.pow(10, pre);
        return val;
    }

    public static int getRandomInt(int min, int max, boolean haveNulls) {

        int val = 0;
        if (haveNulls) {
            if (r.nextInt(5) != 0) //0.25 Prabability to add null values
            {
                val = r.nextInt(max - min + 1) + min;
            } else {
                val = 0;
            }
        } else {
            val = r.nextInt(max - min + 1) + min;
        }

        return val;
    }
    
    private static void setName(){
        for(int i=0;i<40;i++){
            al.add(GetData.getName(5, 8, false));
        }
    }
    
    private static void setDate(){
        for(int i=0;i<45;i++){
            dates.add(GetData.getDate());
        }
    }
    
    private static void setSec(){
        sections.add("A");
        sections.add("B");
        sections.add("C");
        
    }
    
    private static void setSchool(){
        schools.add("SCHS");
        schools.add("Arunodhaya");
        schools.add("ZPPS");
        schools.add("MNTS");
        schools.add("null");
    }
   
    
}
