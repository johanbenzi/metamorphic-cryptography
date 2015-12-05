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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


//Final Testing:
        //Encryption: Input -> message     Output -> ciper image
        MetamorphicEncryptor encryptor = null;
        File coverImageFile = new File("logo.png");
        File cipherImageFile = new File("cipherimage.png");
        BufferedImage cipherImage = null;
       String msg = "a";
        //  String msg = "Hello sujith";
        int pX = 0;
        int pY = 0;

//        for (int i = 0; i <= 255; i++) {
//            msg += (char) i;
//        }
        encryptor = new MetamorphicEncryptor(coverImageFile, pX, pY, msg);
        cipherImage = encryptor.generateCipherImage();
        try {
            ImageIO.write(cipherImage, "png", new File("cipher.png"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        String stegnographedStr = MetamorphicStegnographer.getStegnographedMessage(coverImageFile, cipherImage);
        System.out.println("Stegnographed Msg: ");
        System.out.println(stegnographedStr);


        //Pradox Encryption: Input -> intermediate text     Output -> final ciphered image
        encryptor = new MetamorphicEncryptor(coverImageFile, pX, pY, stegnographedStr);
        cipherImage = encryptor.generateCipherImage();
        try {
            ImageIO.write(cipherImage, "png", new File("final.png"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


        //Decryption: Input -> final image     Output -> intermediate text
        cipherImageFile = new File("final.png");
        MetamorphicDecryptor decryptor = new MetamorphicDecryptor(coverImageFile, cipherImageFile, pX, pY);
        String intermediateText = decryptor.getIntermediateText();
        System.out.println("Intermediate Text:");
        System.out.println(intermediateText);
      

        //Retrieval of Cipher Image: Input - > intermediate text     Output -> cipher image
        cipherImage = MetamorphicStegnographer.getCipherImage(intermediateText, coverImageFile);
        decryptor = new MetamorphicDecryptor(coverImageFile, cipherImage, pX, pY);
        String message = decryptor.getMessage();
        System.out.println(message.length());
        System.out.println("Final Message :" + message);

    }
}
