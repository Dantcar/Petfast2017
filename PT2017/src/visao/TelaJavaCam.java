/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author deciodecarvalho
 */
public class TelaJavaCam extends javax.swing.JInternalFrame {

    private Object jFileChooser1;
    private static int SOURCE_CAM = 3;

    /**
     * Creates new form TelaJavaCam
     */
    public TelaJavaCam() {
        initComponents();
        cmdTakePicture.setEnabled(false);
        cmdStop.setEnabled(false);
        cmdClean.setEnabled(false);
        cmdStart.setEnabled(false);
        cmdDelPicture.setEnabled(false);
        btnSourceCam.setBackground(Color.green);
        btnSourceCam.setText("Escolha a Cam");

    }

    //definições
    //private DaemonThread myThread = null;
    private DaemonThreadCam1 myThreadCam1 = null;
//"C:\Documents and Settings\deciodecarvalho\Meus documentos\ImagensFast\src\visao\TelaClientePet.java"
    int count = 0;
    //VideoCapture webSource = null;
    VideoCapture camPet = null;

    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    /*
     /////////////////////////////////////////////////////////////////////
     class DaemonThread implements Runnable {

     protected volatile boolean runnable = false;
     Dimension d = jPanel1.getSize();
     int width = d.width;
     int height = d.height;

     @Override
     public void run() {
     synchronized (this) {
     while (runnable) {
     if (webSource.grab()) {
     try {
     webSource.retrieve(frame);
     Highgui.imencode(".bmp", frame, mem);
     Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

     BufferedImage buff = (BufferedImage) im;
     Graphics g = jPanel1.getGraphics();

     if (g.drawImage(buff, 0, 0, width, height - 150, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
     if (runnable == false) {
     System.out.println("Going to wait()");
     this.wait();
     }
     }
     } catch (Exception ex) {
     System.out.println("Error");
     }
     }
     }
     }
     }
     }
     /////////////////////////////////////////////////////////
     */

    class DaemonThreadCam1 implements Runnable {

        protected volatile boolean runnable = false;
        Dimension d = lblCam1.getSize();
        int width = d.width;
        int height = d.height;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (camPet.grab()) {
                        try {
                            camPet.retrieve(frame);
                            Highgui.imencode(".bmp", frame, mem);

                            //putTexto();
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;

                            Graphics gCam1 = lblCam1.getGraphics();

                            if (gCam1.drawImage(buff, 0, 0, width, height, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Going to wait()");
                                    this.wait();
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }
                    }
                }
            }
        }

        public void putTexto() {
            /*   
             try {
             
             //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
             Core.putText(
             frame, 
             "Tutorialspoint.com",
             new Point(frame.rows() / 2,frame.cols() / 2), 
             20, 
             new Scalar(255));
             //Highgui.imwrite(im, frame);
                
             } catch (Exception e) {
             System.out.println("Error: " + e.getMessage());

             }

             import org.opencv.core.Core;
             import org.opencv.core.Mat;

             import org.opencv.highgui.Highgui;
             import org.opencv.imgproc.Imgproc;

             public class Main {
             public static void main( String[] args ){
   
             try{
             System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
             Mat source = Highgui.imread("digital_image_processing.jpg",  Highgui.CV_LOAD_IMAGE_COLOR);
             Mat destination = new Mat(source.rows(),source.cols(), source.type());  
         
             Core.putText(source, "Tutorialspoint.com", new Point  (source.rows()/2,source.cols()/2), Core.FONT_ITALIC,new Double(1),new  Scalar(255));

             Highgui.imwrite("watermarked.jpg", source);
         
             } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
             }
             }
             }
            
             */

        }//final putTexto

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmdStart = new javax.swing.JButton();
        cmdStop = new javax.swing.JButton();
        cmdTakePicture = new javax.swing.JButton();
        cmdSair = new javax.swing.JButton();
        lblFotoTake = new javax.swing.JLabel();
        cmdClean = new javax.swing.JButton();
        cmdDelPicture = new javax.swing.JButton();
        lblCam1 = new javax.swing.JLabel();
        lblPetFotoCaminho = new javax.swing.JLabel();
        btnSourceCam = new javax.swing.JToggleButton();
        lblStatusWebCam = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 104, 0), 5));
        jPanel1.setForeground(new java.awt.Color(0, 104, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(104, 0, 0), 5));

        cmdStart.setText("Start");
        cmdStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdStartActionPerformed(evt);
            }
        });

        cmdStop.setText("Stop");
        cmdStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdStopActionPerformed(evt);
            }
        });

        cmdTakePicture.setText("Take Picture");
        cmdTakePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTakePictureActionPerformed(evt);
            }
        });

        cmdSair.setText("Sair");
        cmdSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSairActionPerformed(evt);
            }
        });

        lblFotoTake.setBackground(new java.awt.Color(104, 104, 104));
        lblFotoTake.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmdClean.setText("Clean");
        cmdClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCleanActionPerformed(evt);
            }
        });

        cmdDelPicture.setText("Del Picture");
        cmdDelPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDelPictureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(cmdStart))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmdStop)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFotoTake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmdTakePicture)
                                    .addComponent(cmdClean, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmdDelPicture))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmdClean, cmdDelPicture, cmdSair, cmdStart, cmdStop, cmdTakePicture});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cmdStart)
                .addGap(18, 18, 18)
                .addComponent(cmdStop)
                .addGap(18, 18, 18)
                .addComponent(cmdClean, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFotoTake, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdTakePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdDelPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmdClean, cmdSair, cmdStart, cmdStop, cmdTakePicture});

        lblCam1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 104), 5));

        lblPetFotoCaminho.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btnSourceCam.setBackground(new java.awt.Color(104, 104, 0));
        btnSourceCam.setText("Source");
        btnSourceCam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSourceCamMouseClicked(evt);
            }
        });
        btnSourceCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSourceCamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblPetFotoCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblCam1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSourceCam, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(lblStatusWebCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCam1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSourceCam)
                        .addGap(18, 18, 18)
                        .addComponent(lblStatusWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addComponent(lblPetFotoCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdStartActionPerformed
        // TODO add your handling code here:
        //webSource = new VideoCapture(1);
        cmdClean.setEnabled(true);

        camPet = new VideoCapture(SOURCE_CAM);
//Delay para verifica se a cam abriu e habilitar o botão de tirar foto
        try {
            Thread.sleep(500);
            if (!camPet.isOpened()) {
                cmdTakePicture.setEnabled(false);
                //btnSourceCam.setForeground(Color.green);
                btnSourceCam.setBackground(Color.green);
                // cmdStart.setEnabled(false);
            } else {
                cmdTakePicture.setEnabled(true);
                //btnSourceCam.setForeground(Color.red);
                btnSourceCam.setBackground(Color.red);
                //cmdStart.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //myThread = new DaemonThread();
        //Thread t = new Thread(myThread);
        myThreadCam1 = new DaemonThreadCam1();
        Thread tCam1 = new Thread(myThreadCam1);

        //t.setDaemon(true);
        tCam1.setDaemon(true);

        // myThread.runnable = true;
        myThreadCam1.runnable = true;

        //t.start();
        tCam1.start();

        cmdSair.setEnabled(false); //sair button
        cmdStart.setEnabled(false);  //start button
        cmdStop.setEnabled(true);  // stop button
        btnSourceCam.setEnabled(false); //botão cam


    }//GEN-LAST:event_cmdStartActionPerformed

    private void cmdStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdStopActionPerformed
        // TODO add your handling code here:
        // myThread.runnable = false;
        myThreadCam1.runnable = false;

        cmdStop.setEnabled(false);
        cmdStart.setEnabled(true);

        //webSource.release();
        camPet.release();
        cmdSair.setEnabled(true); //sair button
        cmdTakePicture.setEnabled(false);
        cmdDelPicture.setEnabled(true);
        cmdDelPicture.setEnabled(false);
        btnSourceCam.setEnabled(true); //botão cam

        lblFoto.setIcon(null);
        lblFotoTake.setIcon(null);
        lblPetFotoCaminho.setText("");

        try {
            Thread.sleep(400);
            lblCam1.setIcon(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.repaint();
    }//GEN-LAST:event_cmdStopActionPerformed

    private void cmdTakePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTakePictureActionPerformed
        // TODO add your handling code here:
        ////////////////snapshot button
//      
        cmdClean.setEnabled(true);
        cmdDelPicture.setEnabled(true);
        jFileChooser.addChoosableFileFilter(new TextFilter());

        int returnVal = jFileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            Highgui.imwrite(file.getPath(), frame);

            //matfoto = Highgui.imread(file.getAbsolutePath().toString(), 0);
            Dimension d = lblFotoTake.getSize();
            Dimension dFoto = lblFoto.getSize();

            ImageIcon foto, fotoPet;
            foto = new ImageIcon(file.getPath());
            fotoPet = new ImageIcon(file.getPath());
            int width = d.width;
            int height = d.height;

            foto.setImage(foto.getImage().getScaledInstance(width, height, 100));

            fotoPet.setImage(fotoPet.getImage().getScaledInstance(dFoto.width, dFoto.height, 100));

            lblFotoTake.setIcon(foto);
            lblFoto.setIcon(fotoPet);
            lblPetFotoCaminho.setText(file.getAbsolutePath());

        } else {
            System.out.println("File access cancelled by user.");
        }

//////////////////////////////////////////////

    }//GEN-LAST:event_cmdTakePictureActionPerformed

    private void cmdSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSairActionPerformed
        // TODO add your handling code here:
        this.dispose();

        this.repaint();
    }//GEN-LAST:event_cmdSairActionPerformed

    private void cmdCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCleanActionPerformed
        // Limpar fotos
        lblFoto.setIcon(null);
        lblFotoTake.setIcon(null);
        lblPetFotoCaminho.setText("");
        this.repaint();
        cmdClean.setEnabled(false);
        cmdDelPicture.setEnabled(false);

    }//GEN-LAST:event_cmdCleanActionPerformed

    private void btnSourceCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSourceCamActionPerformed
        // TODO add your handling code here:
        boolean selected = btnSourceCam.isSelected();

        if (selected) {
            SOURCE_CAM = 0;
            btnSourceCam.setText("Webcam 0");

            cmdStart.setEnabled(true);
        } else {
            SOURCE_CAM = 1;
            btnSourceCam.setText("Webcam 1");
            cmdStart.setEnabled(true);
        }


    }//GEN-LAST:event_btnSourceCamActionPerformed

    private void btnSourceCamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSourceCamMouseClicked

    }//GEN-LAST:event_btnSourceCamMouseClicked

    private void cmdDelPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDelPictureActionPerformed
        // TODO add your handling code here:
        File path = new File(lblPetFotoCaminho.getText());
        if (path.delete()) {
            System.out.println("deletado");
            lblFoto.setIcon(null);
            lblFotoTake.setIcon(null);
            lblPetFotoCaminho.setText("");

        } else {
            System.out.println("Erro ao Deletar arquivo");
        }
        this.repaint();
    }//GEN-LAST:event_cmdDelPictureActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnSourceCam;
    private javax.swing.JButton cmdClean;
    private javax.swing.JButton cmdDelPicture;
    private javax.swing.JButton cmdSair;
    private javax.swing.JButton cmdStart;
    private javax.swing.JButton cmdStop;
    private javax.swing.JButton cmdTakePicture;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCam1;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblFotoTake;
    private javax.swing.JLabel lblPetFotoCaminho;
    private javax.swing.JLabel lblStatusWebCam;
    // End of variables declaration//GEN-END:variables
}
