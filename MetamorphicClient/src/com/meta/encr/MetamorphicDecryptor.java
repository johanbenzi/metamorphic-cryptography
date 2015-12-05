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
public class MetamorphicDecryptor {

   private BufferedImage coverImage = null;
   private int coverImgWidth = 0;
   private int coverImgHeight = 0;
   private int pX = 0;
   private int pY = 0;
   private int noOfPixelsToBeDecrypted = 0;
   BufferedImage cipherImage = null;

   public MetamorphicDecryptor(File coverImageFile, File cipherImageFile, int pX, int pY) {
      this.pX = pX;
      this.pY = pY;
      try {
         if (coverImageFile.isFile() && coverImageFile.canRead()) {
            this.coverImage = ImageIO.read(coverImageFile);
            coverImgWidth = coverImage.getWidth();
            coverImgHeight = coverImage.getHeight();
         } else {
            System.out.println("Cover Image: " + coverImageFile.getAbsolutePath()
               + " is not a file or you don't have sufficient privilege to read it.");
         }

         if (cipherImageFile.isFile() && cipherImageFile.canRead()) {
            this.cipherImage = ImageIO.read(cipherImageFile);
            noOfPixelsToBeDecrypted = cipherImage.getWidth();
         } else {
            System.out.println("Cipher Image: " + cipherImageFile.getAbsolutePath()
               + " is not a file or you don't have sufficient privilege to read it.");
         }
      } catch (IOException ex) {
         Logger.getLogger(MetamorphicDecryptor.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public MetamorphicDecryptor(File coverImageFile, BufferedImage cipherImage, int pX, int pY) {
      this.pX = pX;
      this.pY = pY;

      try {
         if (coverImageFile.isFile() && coverImageFile.canRead()) {
            this.coverImage = ImageIO.read(coverImageFile);
            coverImgWidth = coverImage.getWidth();
            coverImgHeight = coverImage.getHeight();
         } else {
            System.out.println("Cover Image: " + coverImageFile.getAbsolutePath()
               + " is not a file or you don't have sufficient privilege to read it.");
         }
         
         this.cipherImage = cipherImage;
         noOfPixelsToBeDecrypted = cipherImage.getWidth();
      } catch (IOException ex) {
         Logger.getLogger(MetamorphicDecryptor.class.getName()).log(Level.SEVERE, null, ex);
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

   public int getNoOfPixelsToBeDecrypted() {
      return noOfPixelsToBeDecrypted;
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

   public int getPixel(int index) {
      return cipherImage.getRGB(index, 0);
   }

   public String getMessage() {
      return getIntermediateText();
   }

   public String getIntermediateText() {
      String intermediateText = "";
      for (int i = 0; i < noOfPixelsToBeDecrypted; i++) {
         intermediateText = intermediateText + getRGBCharsForPixelAt(i);
         if(i == noOfPixelsToBeDecrypted - 1) {
            int a = 0;
         }
      }

      return intermediateText;
   }

   public String getRGBCharsForPixelAt(int index) {
      String msg = "";
      int x = index % coverImgWidth;
      int y = index / coverImgWidth;

      int rgb = cipherImage.getRGB(index, 0);
      int red = (rgb >> 16) & 0xff;
      int green = (rgb >> 8) & 0xff;
      int blue = (rgb & 0xff);

      byte angle = findAngle(x, y);
      int distance = ((int) findDistance(x, y)) % 256;

      int redRR = BinaryEncoder.rotateRight(red, angle);
      int greenRR = BinaryEncoder.rotateRight(green, angle);
      int blueRR = BinaryEncoder.rotateRight(blue, angle);

      int firstChar = BinaryEncoder.xOR(redRR, distance);
      int secondChar = BinaryEncoder.xOR(greenRR, distance);
      int thirdChar = BinaryEncoder.xOR(blueRR, distance);
      msg = msg + ((index == (noOfPixelsToBeDecrypted - 1)) && redRR == 0
         ? "" : (char) firstChar)
         + ((index == (noOfPixelsToBeDecrypted - 1)) && greenRR == 0
         ? "" : (char) secondChar)
         + ((index == (noOfPixelsToBeDecrypted - 1)) && blueRR == 0
         ? "" : (char) thirdChar);

      return msg;
   }

   public boolean isAllNecessaryDataAvailable() {
      boolean allNecessaryDataAvailable = false;
      if (coverImage != null && coverImgWidth != 0 && coverImgHeight != 0) {
         allNecessaryDataAvailable = true;
      }

      return allNecessaryDataAvailable;
   }

   public BufferedImage getCipherImage() {
      return cipherImage;
   }
}
