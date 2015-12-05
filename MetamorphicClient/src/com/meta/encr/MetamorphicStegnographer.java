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
public class MetamorphicStegnographer {

   public static String getStegnographedMessage(File coverImageFile, File cipherImageFile) {
      BufferedImage cipherImage = null;
      try {
         if (cipherImageFile.isFile() && cipherImageFile.canRead()) {
            cipherImage = ImageIO.read(cipherImageFile);
         } else {
            System.out.println("Cipher Image: " + cipherImageFile.getAbsolutePath()
               + " is not a file or you don't have sufficient privilege to read it.");
         }
      } catch (IOException ex) {
         Logger.getLogger(MetamorphicStegnographer.class.getName()).log(Level.SEVERE, null, ex);
      }

      return getStegnographedMessage(coverImageFile, cipherImage);
   }

   public static String getStegnographedMessage(File coverImageFile, BufferedImage cipherImage) {
      BufferedImage coverImage = null;
      String stegnographedStr = "";
      int y = 0;

      try {
         if (coverImageFile.isFile() && coverImageFile.canRead()) {
            coverImage = ImageIO.read(coverImageFile);
         } else {
            System.out.println("Cover Image: " + coverImageFile.getAbsolutePath()
               + " is not a file or you don't have sufficient privilege to read it.");
         }
      } catch (IOException ex) {
         Logger.getLogger(MetamorphicStegnographer.class.getName()).log(Level.SEVERE, null, ex);
      }

      for (int i = 0; i < cipherImage.getWidth(); i++) {
         if (i > 0 && ((i % coverImage.getWidth()) == 0)) {
            y++;
         }
         int rgbOfCoverImage = coverImage.getRGB(i % coverImage.getWidth(), y);
         int rgbOfCipherImage = cipherImage.getRGB(i, 0);

         int rOfCoverImage = (rgbOfCoverImage >> 16) & 0xff;
         int gOfCoverImage = (rgbOfCoverImage >> 8) & 0xff;
         int bOfCoverImage = (rgbOfCoverImage & 0xff);

         int rOfCipherImage = (rgbOfCipherImage >> 16) & 0xff;
         int gOfCipherImage = (rgbOfCipherImage >> 8) & 0xff;
         int bOfCipherImage = (rgbOfCipherImage & 0xff);

         int rXOR = BinaryEncoder.xOR(rOfCoverImage, rOfCipherImage);
         int gXOR = BinaryEncoder.xOR(gOfCoverImage, gOfCipherImage);
         int bXOR = BinaryEncoder.xOR(bOfCoverImage, bOfCipherImage);

         stegnographedStr = stegnographedStr + BinaryEncoder.stegnoEncode('r', rXOR)
            + BinaryEncoder.stegnoEncode('g', gXOR) + BinaryEncoder.stegnoEncode('b', bXOR);
      }

      return stegnographedStr;
   }

   public static BufferedImage getCipherImage(String msg, File coverImageFile) {
      BufferedImage coverImage = null;
      int width = msg.length() / 6;
      BufferedImage cipherImage = new BufferedImage(width, 1, BufferedImage.TYPE_3BYTE_BGR);

      try {
         coverImage = ImageIO.read(coverImageFile);
      } catch (IOException ex) {
         Logger.getLogger(MetamorphicStegnographer.class.getName()).log(Level.SEVERE, null, ex);
      }
      int y = 0;
      int j = 0;
      for (int i = 0; i < cipherImage.getWidth(); i++) {
         if (i > 0 && ((i % coverImage.getWidth()) == 0)) {
            y++;
         }
         int rgbCvrImg = coverImage.getRGB(i % coverImage.getWidth(), y);

         int redCvrImg = (rgbCvrImg >> 16) & 0xff;
         int greenCvrImg = (rgbCvrImg >> 8) & 0xff;
         int blueCvrImg = (rgbCvrImg & 0xff);

         int redCprImg = redCvrImg ^ (BinaryEncoder.stegnoDecode(msg.substring(j, j + 2)));
         int greenCprImg = greenCvrImg ^ (BinaryEncoder.stegnoDecode(msg.substring(j + 2, j + 4)));
         int blueCprImg = blueCvrImg ^ (BinaryEncoder.stegnoDecode(msg.substring(j + 4, j + 6)));
//         System.out.println("Red: " + redCprImg + ", Green: " + greenCprImg + ", Blue: " + blueCprImg);
         int rgbCprImg = (255 << 24) | (redCprImg << 16) | (greenCprImg << 8) | (blueCprImg);
         cipherImage.setRGB(i, 0, rgbCprImg);

         j = j + 6;
      }

      return cipherImage;
   }
}
