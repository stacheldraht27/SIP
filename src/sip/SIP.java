/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sip;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Azura
 */
public class SIP extends JWindow {

    private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    private JLabel lblLogo = new JLabel(new ImageIcon("gambar/logo.png"));
    private JProgressBar barisProgress = new JProgressBar();
    private int time = 0;
    private Timer timer;

    /**
     * CONSTRUCTOR
     */
    public SIP() {
        //menyiapkan variable warna
        Color warna = Color.blue;

        //mengatur baris progress
        barisProgress.setValue(0);
        barisProgress.setPreferredSize(new Dimension(100, 15));
        barisProgress.setBackground(Color.white);
        barisProgress.setForeground(Color.red);
        barisProgress.setStringPainted(true);

        //mengatur warna garis pinggir
        barisProgress.setBorder(new LineBorder(warna, 1));
        lblLogo.setBorder(new LineBorder(warna, 1));

        //menempatkan objek ke container
        getContentPane().add(lblLogo, BorderLayout.NORTH);
        getContentPane().add(barisProgress, BorderLayout.CENTER);

        //mengatur lama window logo tampil pada layar monitor
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                barisProgress.setValue(time);
                if (barisProgress.getPercentComplete() == 1.0) {
                    timer.stop();
                    setVisible(false);
                    //menjalankan menu utama
                    //new Menu_Utama();
                }

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        timer.start();

        //menempatkan object ke memori sebelum di tampilkan
        pack();

        //mengatur lokasi window tepat di tengah layar monitor
        setLocation(dimensi.width / 2 - getWidth() / 2, dimensi.height / 2 - getHeight() / 2);
        
        //menampilkan window ke layar monitor
        show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SIP();
    }

}
