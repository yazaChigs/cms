/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.util;

import java.text.DecimalFormat;

/**
 *
 * @author kanaz
 */
public class AppUtil {
     public static String twoDecimalString(double amt){
          DecimalFormat df = new DecimalFormat("0.00"); 
          return df.format(amt);
     }
      public static String zeroAppend(int number){
         String num  = String.valueOf(number);
         StringBuilder sb = new StringBuilder();
         if(num.length()==1){
            sb.append("0");
            sb.append(num);
         }
         else{
             sb.append(num);
         }
         return sb.toString();
    }
      public static String multipleZeros(int number){
         String num  = String.valueOf(number);
         StringBuilder sb = new StringBuilder();
         switch (num.length()) {
             case 1:
                 sb.append("000");
                 sb.append(num);
                 break;
             case 2:
                 sb.append("00");
                 sb.append(num);
                 break;
             case 3:
                 sb.append("0");
                 sb.append(num);
                 break;
             default:
                 sb.append(num);
                 break;
         }
         return sb.toString();
    }
}
