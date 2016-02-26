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
public class Satuan extends JFrame implements ActionListener, FocusListener {

    private JPanel jpSatuan = new JPanel();
    private JLabel lblNama_Satuan = new JLabel("Nama Satuan");
    private JTextField txtNama_Satuan = new JTextField();
    private JButton btnTambah = new JButton(new ImageIcon("")),
            btnHapus = new JButton(new ImageIcon("")),
            btnBersih = new JButton(new ImageIcon(""));

    Satuan() {
        super("entry satuan barang");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(335, 140);

        jpSatuan.setLayout(null);

        //Mengatur letak objek pada kontainer
        lblNama_Satuan.setBounds(15, 20, 100, 25);
        txtNama_Satuan.setBounds(110, 20, 200, 25);

        btnTambah.setBounds(15, 60, 85, 25);
        btnHapus.setBounds(120, 60, 85, 25);
        btnBersih.setBounds(225, 60, 85, 25);

        //Mengatur objek tombol aktif dan tidak
        btnTambah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnBersih.setEnabled(true);

        //mengatur objek untuk dapat berinteraksi dengan user
        txtNama_Satuan.addFocusListener(this);
        btnTambah.addFocusListener(this);
        btnHapus.addFocusListener(this);
        btnBersih.addFocusListener(this);

        //mengatur / meletakan seluruh kontrol pada objek panel
        jpSatuan.add(lblNama_Satuan);
        jpSatuan.add(txtNama_Satuan);
        jpSatuan.add(btnTambah);
        jpSatuan.add(btnHapus);
        jpSatuan.add(btnBersih);
        //jpSatuan.add(btnBatal);

        //Menambahkan objek panel ke container frame
        getContentPane().add(jpSatuan);

        //menampilkan frame ke layar monitor;
        setVisible(true);

    }

    /**
     * Fungsi jika user melakukan penekanan tombol
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object obj = ae.getSource();
        if (obj == btnTambah) {
            Tambah();
        }
        if (obj == btnHapus) {
            Hapus();
        }
        Bersih();
    }

    /**
     * fungsi untuk memeriaksa kursor saat meninggalkan objek
     *
     * @param e
     */
    @Override
    public void focusGained(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (txtNama_Satuan.getText().equals("")) {
        } else {
            Cari();
        }
    }

    /**
     * fungsi untuk mencari data ke table satuan
     */
    void Cari() {
        try {
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM Satuan WHERE"
                    + "Nama_Satuan = '" + txtNama_Satuan.getText() + "'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                //Data ditemukan
                btnTambah.setEnabled(false);
                btnHapus.setEnabled(true);
                btnHapus.requestFocus();
            } else {
                //Data tidak di temukan
                btnTambah.setEnabled(true);
                btnHapus.setEnabled(false);
                btnTambah.requestFocus();
            }
            rs.close();
            con.close();
        } catch (SQLException e) {

        }
    }

    /**
     * Fungsi untuk menambahkan data ke tabel satuan
     */
    void Tambah() {
        try {
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            Statement st = con.createStatement();
            String sql = "insert into Satuan(Nama_Satuan)"
                    + "values('" + txtNama_Satuan.getText() + "')";
            int rows = st.executeUpdate(sql);
            if (rows == 1) {
                JOptionPane.showMessageDialog(this, "Data sudah di tambahkan");
            }
            con.close();
        } catch (SQLException e) {

        }
    }

    /**
     * fungsi untuk menghapus data yang ada pada table satuan
     */
    void Hapus() {
        try {
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            Statement st = con.createStatement();
            String sql = "delete from Satuan where Nama_Satuan=" + txtNama_Satuan.getText() + "'";
            int rows = st.executeUpdate(sql);
            if (rows == 1) {
                JOptionPane.showMessageDialog(this, "Data sudah di hapus");
            }
            con.close();
        } catch (SQLException e) {

        }
    }

    /**
     * fungsi untuk membersihkan semua inputan
     */
    void Bersih() {
        txtNama_Satuan.setText("");
        btnTambah.setEnabled(false);
        btnHapus.setEnabled(false);
        txtNama_Satuan.requestFocus();
    }

    /**
     * fungsi utama
     * @param args 
     */
    public static void main(String[] args) {
        new Satuan();
    }
}
