/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 84985
 */
public class XDate {
   public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    //chuuyển String sang Date
    /*
    @param date truyền vào date kiểu String
    @param pattern truyền vào kiểu
    return trả về date kiểu Date
    */
    public static Date toDate(String date,String...pattern){
        try {
            if(pattern.length>0)sdf.applyPattern(pattern[0]);
            if(date==null) XDate.now();
            return sdf.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex); 
        }
    }
    public static String toString(Date d,String pattern){
        if(d == null){
            d = new Date();
        }         
        sdf.applyPattern(pattern);
        
            return sdf.format(d);
    }
    public static Date addDate(Date d,long days){
        d.setTime(d.getTime()+days*24*60*60*1000);
        return d;
    }
    public static Date now(){
        return new Date();
    }
    public static void main(String[] args) {
        String date = "09-10-2021";
        Date d = toDate(date);
        System.out.println(d);
    }
   
    
}
