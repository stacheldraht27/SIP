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
public class Barang extends JInternalFrame implements ActionListener, FocusListener {

    private JPanel jpBarang = new JPanel();
    private JLabel lblKode_Barang = new JLabel("KOde Barang:"),
            lblNama = new JLabel("Nama Barang :"),
            lblSatuan = new JLabel("Satuan :"),
            lblHarga = new JLabel("Harga :");

    private JTextField txtKode_Barang = new JTextField(),
            txtNama = new JTextField(),
            txtHarga = new JTextField();

    private JComboBox cboSatuan = new JComboBox();
    private JButton btnTambah = new JButton(new ImageIcon("")),
            btnUbah = new JButton(new ImageIcon("")),
            btnHapus = new JButton(new ImageIcon("")),
            btnBersih = new JButton(new ImageIcon(""));

    Barang() {
        //super(Judul, Ukuran, Close, Maksimum, Minimum)
        super("Data Entri Master Barang", false, true, false, true);
        setSize(470, 200);

        jpBarang.setLayout(null);

        //Mengatur letak objek pada container
        lblKode_Barang.setBounds(15, 20, 100, 25);
        lblNama.setBounds(15, 55, 100, 25);
        lblSatuan.setBounds(15, 90, 100, 25);
        lblHarga.setBounds(15, 125, 100, 25);

        txtKode_Barang.setBounds(115, 20, 100, 25);
        txtNama.setBounds(115, 55, 205, 25);
        cboSatuan.setBounds(115, 90, 92, 25);
        txtHarga.setBounds(115, 125, 100, 25);

        btnTambah.setBounds(340, 20, 85, 25);
        btnUbah.setBounds(115, 55, 205, 25);
        btnHapus.setBounds(340, 90, 85, 25);
        btnBersih.setBounds(340, 125, 85, 25);

        //mengatur posisi pengetikan
        txtHarga.setHorizontalAlignment(JTextField.RIGHT);

        //mengatur objek tombol aktif atau tidak
        btnTambah.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnBersih.setEnabled(true);

        //mengatur letak objek untuk dapat berinteraksi dengan user
        txtKode_Barang.addFocusListener(this);
        btnTambah.addActionListener(this);
        btnUbah.addActionListener(this);
        btnHapus.addActionListener(this);
        btnBersih.addActionListener(this);

        //untuk membatasi user menginput hanya bilangan saja
        txtHarga.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)))) {
                    getToolkit().beep();
                    ke.consume();
                }

            }
        });

        //mengatur /meletakan seluruh kontrol pada objek panel.
        jpBarang.add(lblKode_Barang);
        jpBarang.add(txtKode_Barang);
        jpBarang.add(lblNama);
        jpBarang.add(txtNama);
        jpBarang.add(lblSatuan);
        jpBarang.add(cboSatuan);
        jpBarang.add(lblHarga);
        jpBarang.add(txtHarga);
        jpBarang.add(btnTambah);
        jpBarang.add(btnUbah);
        jpBarang.add(btnHapus);
        jpBarang.add(btnBersih);

        //menambahkan objek panel ke container frame
        getContentPane().add(jpBarang);

        //memanggil metode untuk mengisi kota kombo
        IsiKomboSatuan();

        //menampilkan frame ke layar monitor
        setVisible(true);
    }

    /**
     * fungsi jika user melakukan action penekanan tombol
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object obj = e.getSource();
        if (obj == btnTambah) {
            Tambah();
        }
        if (obj == btnUbah) {
            Ubah();
        }
        if (obj == btnHapus) {
            Hapus();
        }
        Bersih();
    }

    /**
     * fungsi untuk memeriksa kurasor saat meninggalkan objek
     */
    @Override
    public void focusGained(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (txtKode_Barang.getText().equals("")) {
        } else {
            Cari();
        }
    }

    /**
     * fungsi untuk mencari data ke table barang
     */
    void Cari() {
        try {
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM Barang WHERE Kode_Barang"
                    + "='" + txtKode_Barang.getText() + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                //Data ditemukan
                txtKode_Barang.setText(rs.getString("Kode_Barang"));
                txtNama.setText(rs.getString("Nama"));
                cboSatuan.setSelectedItem(rs.getString("Satuan"));
                txtHarga.setText(rs.getString("Harga"));
                btnTambah.setEnabled(false);
                btnUbah.setEnabled(true);
                btnHapus.setEnabled(true);
                txtNama.requestFocus();
            } else {
                //Data tidak di temukan
                btnTambah.setEnabled(true);
                btnUbah.setEnabled(false);
                btnHapus.setEnabled(false);
                txtNama.requestFocus();
            }
            rs.close();
            con.close();
        } catch (SQLException e) {

        }
    }

    /**
     * Fungsi untuk menambahkan data ke tabel barang
     */
    void Tambah() {
        try {
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            Statement st = con.createStatement();
            String sql = "insert into Barang"
                    + "(Kode_Barang, Nama, Satuan, Harga)"
                    + "values('" + txtKode_Barang.getText() + "','"
                    + txtNama.getText() + "','" + cboSatuan.getSelectedItem() + "','"
                    + txtHarga.getText() + ")";
            int rows = st.executeUpdate(sql);
            if (rows == 1) {
                JOptionPane.showMessageDialog(this, "Data sudah di tambahkan");
            }
            con.close();
        } catch (SQLException e) {

        }
    }

    /**
     * fungsi untuk merubah data yang ada pada table barang
     */
    void Ubah() {
        try {
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            Statement st = con.createStatement();
            String sql = "update Barang set Nama='" + txtNama.getText()
                    + ",Satuan='" + cboSatuan.getSelectedItem() + "',Harga=" + txtHarga.getText()
                    + "where Kode_barang='" + txtKode_Barang.getText() + "'";
            int rows = st.executeUpdate(sql);
            if (rows == 1) {
                JOptionPane.showMessageDialog(this, "Data sudah di ubah");
            }
            con.close();
        } catch (SQLException e) {

        }
    }

    /**
     * fungsi untuk menghapus data yang ada pada table barang
     */
    void Hapus() {
        try{
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            Statement st = con.createStatement();
            String sql = "delete from Barang where Kode_Barang ='"+txtKode_Barang.getText()+"'";
            int rows = st.executeUpdate(sql);
            if (rows == 1) {
                JOptionPane.showMessageDialog(this, "Data sudah di ubah");
            }
            con.close();
        }catch(SQLException e){
            
        }
    }

    /**
     * fungsi untuk membersihkan semua inputan
     */
    void Bersih() {
        txtKode_Barang.setText("");
        txtNama.setText("");
        cboSatuan.setSelectedIndex(0);
        txtHarga.setText("");
        btnTambah.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        txtKode_Barang.requestFocus();
    }

    void IsiKomboSatuan() {
        try{
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            Statement st = con.createStatement();
            String sql = "select * from satuan ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cboSatuan.addItem(""+rs.getString("nama_satuan"));
            }
            con.close();
        }catch(SQLException e){
            
        }
    }

}
