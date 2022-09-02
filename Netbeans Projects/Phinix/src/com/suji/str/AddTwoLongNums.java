package com.suji.str;


public class AddTwoLongNums {

    public static void main(String[] args) {
        String num1 = "1000000";
        String num2 = "1";
       
        StringBuilder n1 = new StringBuilder(num1);
        StringBuilder n2 = new StringBuilder(num1);
        StringBuilder ans = new StringBuilder();
        
        String lar = ( num1.length() > num2.length() )? num1: num2;
       
        int carry = 0;
        for (int i = 0; i < lar.length(); i++) {
            
           if(n1.length() > i && n2.length() > i){
               int no1 = 0, no2 = 0;
               try {
                   no1 = Integer.parseInt(num1.charAt(num1.length()-1-i)+"");
               } catch (Exception ex) {
                   no2 = 0;
               }
               try {
                   no2 = Integer.parseInt(num2.charAt(num1.length()-1-i)+"");
               } catch (Exception ex) {
                   no2 = 0;
               }
              
               System.out.println(no1+" "+no2);
              int sum = no1 + no2 + carry;
               System.out.println("Sum: "+sum);
              carry = sum / 10;
               System.out.println("Carry: "+carry);
              int left = sum % 10;
              ans.insert(0, left);
           }
        }
        if(carry >0){
            ans.insert(0, carry);
        }
        System.out.println(ans);
        
        
        
        
        
    }

}
