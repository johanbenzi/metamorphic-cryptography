/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.meta.encr.MetamorphicDecryptor;
import com.meta.encr.MetamorphicStegnographer;
import com.util.ClientValidator;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrator
 */
public class DecryptDialog extends javax.swing.JDialog {

    /**
     * Creates new form ResponseWindow
     */
    BufferedImage finalImage = null;
    BufferedImage cipherImage = null;
    File coverImageFile = null;
    File cipherImageFile = null;
    String intermediateText = "";
    int finalImageH = 0;
    int finalImageW = 0;
    int cipherIconWidth = 0;
    int cipherIconHeight = 0;
    int pX = 0;
    int pY = 0;
    int newImageWidth = 60;
    int newImageHeight = 10;
    private ClientHome parent = null;
    private MetamorphicDecryptor decryptor = null;

    public DecryptDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //this.parent = (ClientHome) parent;
        initComponents();
          setIconImage(new ImageIcon(this.getClass().getResource("/com/icon/met_client_icon.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        decryptPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        CoverImgFileTextField = new javax.swing.JTextField();
        coverImgBrowseButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pXTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pYTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cipherImageTextField = new javax.swing.JTextField();
        cipherImageBrowseButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        intermediateTextArea = new javax.swing.JTextArea();
        decryptButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cipherZoomButton = new javax.swing.JButton();
        cipherZoomOutButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        cipherImgLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        finalTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Metomorphic Response");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Decryption  Requirments"));

        jLabel3.setText("Cover Image :");

        coverImgBrowseButton.setText("Browse");
        coverImgBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coverImgBrowseButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("pX :");

        jLabel2.setText("pY :");

        jLabel4.setText("Cipher Image :");

        cipherImageBrowseButton.setText("Browse");
        cipherImageBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cipherImageBrowseButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Intermediate Text"));

        intermediateTextArea.setColumns(20);
        intermediateTextArea.setRows(5);
        jScrollPane2.setViewportView(intermediateTextArea);

        decryptButton.setText("DECRYPT");
        decryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CoverImgFileTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(coverImgBrowseButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pXTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pYTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12)
                        .addComponent(cipherImageTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cipherImageBrowseButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(decryptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CoverImgFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(coverImgBrowseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pXTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(pYTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cipherImageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cipherImageBrowseButton))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addComponent(decryptButton)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cipher Image"));

        cipherZoomButton.setText("ZOOM IN");
        cipherZoomButton.setEnabled(false);
        cipherZoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cipherZoomButtonActionPerformed(evt);
            }
        });

        cipherZoomOutButton.setText("ZOOM OUT");
        cipherZoomOutButton.setEnabled(false);
        cipherZoomOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cipherZoomOutButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(cipherImgLabel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 335, Short.MAX_VALUE)
                        .addComponent(cipherZoomButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cipherZoomOutButton))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cipherZoomOutButton)
                    .addComponent(cipherZoomButton)))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Final Text"));

        finalTextArea.setColumns(20);
        finalTextArea.setRows(5);
        jScrollPane1.setViewportView(finalTextArea);

        javax.swing.GroupLayout decryptPanelLayout = new javax.swing.GroupLayout(decryptPanel);
        decryptPanel.setLayout(decryptPanelLayout);
        decryptPanelLayout.setHorizontalGroup(
            decryptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decryptPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(decryptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(decryptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(decryptPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        decryptPanelLayout.setVerticalGroup(
            decryptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decryptPanelLayout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(decryptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(decryptPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(decryptPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(decryptPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-606)/2, (screenSize.height-547)/2, 606, 547);
    }// </editor-fold>//GEN-END:initComponents

    private void coverImgBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coverImgBrowseButtonActionPerformed
// TODO add your handling code here:
   /*
         * JFileChooser jFileChooser = new JFileChooser() {
         *
         * @Override public void approveSelection() { File selectedFile =
         * getSelectedFile(); if (selectedFile.exists() &&
         * selectedFile.getName().endsWith(".png")) { super.approveSelection();
         * } else { JOptionPane.showMessageDialog(this, "Please select a '.png'
         * file", "Invalid File Selection", JOptionPane.OK_OPTION); } } };
         */
        JFileChooser chooser = new JFileChooser();
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "gif");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        chooser.setFileFilter(filter);
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            coverImageFile = chooser.getSelectedFile();
            decryptButton.setEnabled(true);
            CoverImgFileTextField.setText(coverImageFile.getAbsolutePath());
        }
    }//GEN-LAST:event_coverImgBrowseButtonActionPerformed

    private void cipherImageBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cipherImageBrowseButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "gif");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        chooser.setFileFilter(filter);
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            cipherImageFile = chooser.getSelectedFile();
            cipherImageTextField.setText(cipherImageFile.getAbsolutePath());
        }

    }//GEN-LAST:event_cipherImageBrowseButtonActionPerformed

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptButtonActionPerformed
        String msg = ClientValidator.validateDecrypting(coverImageFile, pXTextField.getText(), pYTextField.getText(), cipherImageFile);
        if (msg.equals("")) {
            pX = Integer.parseInt(pXTextField.getText());
            pY = Integer.parseInt(pYTextField.getText());
            decryptor = new MetamorphicDecryptor(coverImageFile, cipherImageFile, pX, pY);
            intermediateText = decryptor.getIntermediateText();
            intermediateTextArea.setText(intermediateText);
            System.out.println("Intermediate Text:");
            System.out.println(intermediateText);


            //Retrieval of Cipher Image: Input - > intermediate text     Output -> cipher image
            cipherImage = MetamorphicStegnographer.getCipherImage(intermediateText, coverImageFile);
            decryptor = new MetamorphicDecryptor(coverImageFile, cipherImage, pX, pY);
            String message = decryptor.getMessage();
            System.out.println(message.length());
            System.out.println("Final Message :" + message);
            finalTextArea.setText(message);

            ImageIcon cipherImageIcon = new ImageIcon(cipherImage);
            cipherIconHeight = cipherImageIcon.getIconHeight();
            cipherIconWidth = cipherImageIcon.getIconWidth();
            cipherImgLabel.setIcon(cipherImageIcon);

            //-------------------zoom---------------------------------
            cipherZoomButton.setEnabled(true);
            cipherZoomOutButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, msg, "Decoding Form", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_decryptButtonActionPerformed

    private void cipherZoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cipherZoomButtonActionPerformed

        if (cipherIconHeight < 1000 && cipherIconWidth < 2000) {
            System.out.println(" " + newImageHeight);
            System.out.println(" " + newImageWidth);

            newImageWidth = cipherIconWidth * 10;
            newImageHeight = cipherIconHeight * 10;

            //----------------------------------------------
            BufferedImage resizedImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(cipherImage, 0, 0, newImageWidth, newImageHeight, null);
            g.dispose();
            cipherImgLabel.setIcon(new ImageIcon(resizedImage));
            cipherIconHeight = newImageHeight;
            cipherIconWidth = newImageWidth;
        }


    }//GEN-LAST:event_cipherZoomButtonActionPerformed

    private void cipherZoomOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cipherZoomOutButtonActionPerformed
        if (cipherIconHeight > 1 && cipherIconWidth > 2) {
            System.out.println(" " + newImageHeight);
            System.out.println(" " + newImageWidth);
            newImageWidth = cipherIconWidth / 10;
            newImageHeight = cipherIconHeight / 10;

            //------------------------------------------------
            BufferedImage resizedImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(cipherImage, 0, 0, newImageWidth, newImageHeight, null);
            g.dispose();
            cipherImgLabel.setIcon(new ImageIcon(resizedImage));
            cipherIconHeight = newImageHeight;
            cipherIconWidth = newImageWidth;
        }

    }//GEN-LAST:event_cipherZoomOutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DecryptDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DecryptDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DecryptDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DecryptDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DecryptDialog dialog = new DecryptDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CoverImgFileTextField;
    private javax.swing.JButton cipherImageBrowseButton;
    private javax.swing.JTextField cipherImageTextField;
    private javax.swing.JLabel cipherImgLabel;
    private javax.swing.JButton cipherZoomButton;
    private javax.swing.JButton cipherZoomOutButton;
    private javax.swing.JButton coverImgBrowseButton;
    private javax.swing.JButton decryptButton;
    private javax.swing.JPanel decryptPanel;
    private javax.swing.JTextArea finalTextArea;
    private javax.swing.JTextArea intermediateTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField pXTextField;
    private javax.swing.JTextField pYTextField;
    // End of variables declaration//GEN-END:variables
}
