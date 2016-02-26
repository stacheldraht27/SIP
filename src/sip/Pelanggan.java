/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sip;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Azura
 */
public class Pelanggan extends JInternalFrame implements ActionListener, FocusListener {
    private JPanel jpPelanggan = new JPanel();
    
    private JLabel lblKode_Pelanggan = new JLabel("Kode Pelanggan:"),
            lblNama = new JLabel("Nama:"),
            lblAlamat = new JLabel("Alamat:"),
            lblKota = new JLabel("Kota:"),
            lblProvinsi = new JLabel("Provinsi:"),
            lblKodePos = new JLabel("Kode Pos:"),
            lblJK = new JLabel("Jenis Kelamin :"),
            lblTelepon = new JLabel("Telepon :"),
            lblHP = new JLabel("HP :"),
            lblAgama = new JLabel("Agama:");
    
    private JTextField txtKode_Pelanggan = new JTextField(),
            txtNama = new JTextField(),
            txtKode_Pos = new JTextField(),
            txtTelepon = new JTextField(),
            txtHP = new JTextField();
    
    private JTextArea txaAlamat = new JTextArea();
    
    private String[] arrAgama = {"Islam", "Kristen","Katolik","Hindu","Budha"};
    
    private JComboBox cboKota = new JComboBox(),
            cboProvinsi = new JComboBox(),
            cboAgama = new JComboBox(arrAgama);
    
    private ButtonGroup bgJK = new ButtonGroup();
    
    private JRadioButton rbPria = new JRadioButton("Pria",true),
            rbWanita = new JRadioButton("Wanita",false);
    
    private JButton btnTambah = new JButton(new ImageIcon("")),
            btnUbah = new JButton(new ImageIcon("")),
            btnHapus = new JButton(new ImageIcon("")),
            btnBersih = new JButton(new ImageIcon(""));
    
    Pelanggan(){
        //super(Judul, Ukuran, Close, Maksimum, Minimum)
        super("Data Entri Master Pelanggan",false, true, false, true);
        setSize(580,380);
        
        jpPelanggan.setLayout(null);
        
        //mengatur objek text are
        txaAlamat.setLineWrap(true);
        txaAlamat.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane()
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
