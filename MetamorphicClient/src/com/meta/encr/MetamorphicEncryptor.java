/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meta.encr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author AK
 */
public class MetamorphicEncryptor {

   private BufferedImage coverImage = null;
   private int coverImgWidth = 0;
   private int coverImgHeight = 0;
   private int pX = 0;
   private int pY = 0;
   private String msg = null;
   private int noOfPixelsToBeEncrypted = 0;

   public MetamorphicEncryptor(File coverImageFile, int pX, int pY, String msg) {
      try {
         if (coverImageFile.isFile() && coverImageFile.canRead()) {
            this.coverImage = ImageIO.read(coverImageFile);
            this.pX = pX;
            this.pY = pY;
            this.msg = msg;

            coverImgWidth = coverImage.getWidth();
            coverImgHeight = coverImage.getHeight();
            noOfPixelsToBeEncrypted = (int) Math.ceil(msg.length() / 3.0);
            System.out.println(msg.length() + "," + noOfPixelsToBeEncrypted);
         } else {
            System.out.println(coverImageFile.getAbsolutePath()
               + " is not a file or you don't have sufficient privilege to read it.");
         }
      } catch (IOException ex) {
         Logger.getLogger(MetamorphicEncryptor.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public BufferedImage getCoverImg() {
      return coverImage;
   }

   public void setCoverImg(BufferedImage coverImg) {
      this.coverImage = coverImg;
   }

   public int getpX() {
      return pX;
   }

   public void setpX(int pX) {
      this.pX = pX;
   }

   public int getCoverImgWidth() {
      return coverImgWidth;
   }

   public int getCoverImgHeight() {
      return coverImgHeight;
   }

   public int getpY() {
      return pY;
   }

   public void setpY(int pY) {
      this.pY = pY;
   }

   public String getMsg() {
      return msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public byte findAngle(int x, int y) {
      double distance = findDistance(x, y);
      int angle = (int) (Math.acos((pX - x) / distance) * 180 / Math.PI);
      angle = angle % 8;

      return (byte) angle;
   }

   public double findDistance(int x, int y) {
      int dx = pX - x;
      int dy = pY - y;
      double distance = Math.sqrt((dx * dx) + (dy * dy));

      return distance;
   }

   public int getNoOfpixelsToEncode() {
      return noOfPixelsToBeEncrypted;
   }

   public int getEncodedPixelRGB(int index) {
      int startCharIdx = index * 3;
      int rgb = 0;

      if (isAllNecessaryDataAvailable()) {
         int x = index % coverImgWidth;
         int y = index / coverImgWidth;
         byte angle = findAngle(x, y);
         int distance = ((int) findDistance(x, y)) % 256;

         int firstCharXOR = 0;
         int secondCharXOR = 0;
         int thirdCharXOR = 0;
         int firstCharRL = 0;
         int secondCharRL = 0;
         int thirdCharRL = 0;

         if (index != (getNoOfpixelsToEncode() - 1)) {
            firstCharXOR = BinaryEncoder.xOR(msg.charAt(startCharIdx), distance);
            secondCharXOR = BinaryEncoder.xOR(msg.charAt(startCharIdx + 1), distance);
            thirdCharXOR = BinaryEncoder.xOR(msg.charAt(startCharIdx + 2), distance);

            firstCharRL = BinaryEncoder.rotateLeft(firstCharXOR, angle);
            secondCharRL = BinaryEncoder.rotateLeft(secondCharXOR, angle);
            thirdCharRL = BinaryEncoder.rotateLeft(thirdCharXOR, angle);

         } else {
            switch (msg.length() % 3) {
               case 0:
                  firstCharXOR = BinaryEncoder.xOR(msg.charAt(startCharIdx), distance);
                  secondCharXOR = BinaryEncoder.xOR(msg.charAt(startCharIdx + 1), distance);
                  thirdCharXOR = BinaryEncoder.xOR(msg.charAt(startCharIdx + 2), distance);

                  firstCharRL = BinaryEncoder.rotateLeft(firstCharXOR, angle);
                  secondCharRL = BinaryEncoder.rotateLeft(secondCharXOR, angle);
                  thirdCharRL = BinaryEncoder.rotateLeft(thirdCharXOR, angle);
                  break;
               case 1:
                  firstCharXOR = BinaryEncoder.xOR(msg.charAt(startCharIdx), distance);

                  firstCharRL = BinaryEncoder.rotateLeft(firstCharXOR, angle);
                  break;
               case 2:
                  firstCharXOR = BinaryEncoder.xOR(msg.charAt(startCharIdx), distance);
                  secondCharXOR = BinaryEncoder.xOR(msg.charAt(startCharIdx + 1), distance);

                  firstCharRL = BinaryEncoder.rotateLeft(firstCharXOR, angle);
                  secondCharRL = BinaryEncoder.rotateLeft(secondCharXOR, angle);
            }
         }
         rgb = (255 << 24) | (firstCharRL << 16) | (secondCharRL << 8) | thirdCharRL;
      }

      return rgb;
   }

   public int getPixel(int index) {
      String str = "" + ((double) index / coverImgWidth);
      int x = Integer.parseInt(str.substring(0, str.indexOf('.')));
      int y = Integer.parseInt(str.substring(str.indexOf('.') + 1));

      return coverImage.getRGB(x, y);
   }

   public boolean isAllNecessaryDataAvailable() {
      boolean allNecessaryDataAvailable = false;
      if (coverImage != null && coverImgWidth != 0 && coverImgHeight != 0) {
         allNecessaryDataAvailable = true;
      }

      return allNecessaryDataAvailable;
   }

   public BufferedImage generateCipherImage() {
      BufferedImage cipherImage = new BufferedImage(noOfPixelsToBeEncrypted, 1, BufferedImage.TYPE_3BYTE_BGR);

      System.out.println("Encryption:");
      for (int i = 0; i < noOfPixelsToBeEncrypted; i++) {
         int rgb = getEncodedPixelRGB(i);
         cipherImage.setRGB(i, 0, rgb);
      }

      return cipherImage;
   }
}
