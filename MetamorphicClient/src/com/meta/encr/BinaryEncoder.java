/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meta.encr;

/**
 *
 * @author AK
 */
public class BinaryEncoder {

   private static boolean debug = false;

   public static int xOR(int character, double distance) {
      if (debug) {
         System.out.println("char: " + character + " Distance: " + distance);
      }
      return (int) (((int) distance) ^ character);
   }

   public static int xOR(int value1, int value2) {
      return (value1 ^ value2);
   }

   public static int rotateLeft(int value, byte angle) {
      String binValue = Integer.toBinaryString((int) value);

      while (binValue.length() != 8) {
         binValue = "0" + binValue;
      }

      if (debug) {
         System.out.print("Before RL: " + value + ", " + binValue + " angle: " + angle);
      }
      for (int i = 0; i < angle; i++) {
         binValue = binValue.charAt(binValue.length() - 1)
            + binValue.substring(0, binValue.length() - 1);
      }
      if (debug) {
         System.out.println(" After RL: " + Integer.valueOf(binValue, 2).intValue()
            + ", " + binValue);
      }
      return Integer.valueOf(binValue, 2).intValue();
   }

   public static int rotateRight(int value, byte angle) {
      String binValue = Integer.toBinaryString((int) value);

      while (binValue.length() != 8) {
         binValue = "0" + binValue;
      }
      if (debug) {
         System.out.print("Before RR: " + value + ", " + binValue + " angle: " + angle);
      }
      for (int i = 0; i < angle; i++) {
         binValue = binValue.substring(1) + binValue.charAt(0);
      }
      if (debug) {
         System.out.println(" After RR: " + Integer.valueOf(binValue, 2).intValue()
            + ", " + binValue);
      }
      
      return Integer.valueOf(binValue, 2).intValue();
   }

   public static String stegnoEncode(char colorType, int colorValue) {
      int firstNibble = (colorValue >> 4) & 0xf;
      int secondNibble = (colorValue) & 0xf;
      String rEncodeStr = "ABCDEFGHIJKLMNOP";
      String gEncodeStr = "abcdefghijklmnop";
      String bEncodeStr = "QRSTUVWXYZqrstuv";
      String encodedStr = "";
      switch (colorType) {
         case 'r':
            encodedStr = encodedStr + rEncodeStr.charAt(firstNibble) + rEncodeStr.charAt(secondNibble);
            break;
         case 'g':
            encodedStr = encodedStr + gEncodeStr.charAt(firstNibble) + gEncodeStr.charAt(secondNibble);
            break;
         case 'b':
            encodedStr = encodedStr + bEncodeStr.charAt(firstNibble) + bEncodeStr.charAt(secondNibble);
      }

      return encodedStr;
   }

   public static int stegnoDecode(String colorStr) {

      String colorEncodeStr = "ABCDEFGHIJKLMNOPabcdefghijklmnopQRSTUVWXYZqrstuv";
      int firstNibble = colorEncodeStr.indexOf(colorStr.charAt(0)) % 16;
      int secondNibble = colorEncodeStr.indexOf(colorStr.charAt(1)) % 16;
      int colorValue = (firstNibble << 4) | secondNibble;
      return colorValue;
   }
}
