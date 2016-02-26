/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sip;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.text.*;
import javax.swing.Timer;

/**
 *
 * @author Azura
 */
class Menu_Utama extends JFrame implements ActionListener {

    private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();

    //mengatur frame menjadi frameinduk
    private JDesktopPane desktop = new JDesktopPane();

    //Deklarasi + instansiasi menu Pulldown
    private JMenuBar barMenu = new JMenuBar();
    private JMenu mnuMaster = new JMenu("Master"),
            mnuTransaksi = new JMenu("Transaksi"),
            mnuCetak = new JMenu("Cetak"),
            mnuAbout = new JMenu("About");

    private JMenuItem mnuMstBarang = new JMenuItem("Barang", new ImageIcon("")),
            mnuMstPelanggan = new JMenuItem("Pelanggan", new ImageIcon("")),
            mnuMstSatuan = new JMenuItem("Satuan", new ImageIcon("")),
            mnuMstKota = new JMenuItem("Kota", new ImageIcon("")),
            mnuMstProvinsi = new JMenuItem("Provinsi", new ImageIcon("")),
            mnuKeluar = new JMenuItem("Exit", new ImageIcon("")),
            mnuTrsPesanan = new JMenuItem("Pesanan", new ImageIcon("")),
            mnuCetMstBarang = new JMenuItem("Daftar Barang", new ImageIcon("")),
            mnuCetTrsPesanan = new JMenuItem("Daftar Pesanan", new ImageIcon("")),
            mnuAuthor = new JMenuItem("About System & Author", new ImageIcon(""));

    //Deklarasi + instansiasi menu popup
    private JPopupMenu popMenu = new JPopupMenu();
    private JMenuItem mnuPopMstBarang = new JMenuItem("Master Barang", new ImageIcon("")),
            mnuPopMstPelanggan = new JMenuItem("Master Pelanggan", new ImageIcon("")),
            mnuPopTrsPesanan = new JMenuItem("Transaksi Pesanan", new ImageIcon(""));

    //Deklarasi menu ToolBar dan objek panel serta label jam digital
    private JToolBar toolBar = new JToolBar();
    private JPanel ToolbarPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT)),
            ToolbarPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    private JButton mnuBtnMstBarang = new JButton(new ImageIcon()),
            mnuBtnMstPelanggan = new JButton(new ImageIcon()),
            mnuBtnTrsPesanan = new JButton(new ImageIcon()),
            mnuBtnAuthor = new JButton(new ImageIcon());

    private JLabel lblJam = new JLabel();

    //mengambil tanggal aktif dan mengatur format tanggal.
    private Date tglAktif = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy", Locale.getDefault());
    private String tanggal = sdf.format(tglAktif);

    //deklarasi komponen panel dan objek label untuk status bar
    private JPanel statusBar = new JPanel();
    private JLabel lblAuthor = new JLabel("" + "Copyright(c) AEndF 2007-2008",
            JLabel.LEFT),
            lblTgl = new JLabel("" + tanggal + "", JLabel.RIGHT);

    /**
     * metode berupa konstruktor
     */
    public Menu_Utama() {

        //mengatur tile / judul dan icon pada frame.
        super("Sistem Informasi Pemesanan Barang pada PT.XXX");
        setIconImage(getToolkit().getImage(""));

        //mengatur ukuran dan lokasi frame pada layar monitor
        setSize(800, 600);
        setLocation((dimensi.width - getWidth()) / 2,
                (dimensi.height = getHeight()) / 2);

        //memberikan listener dan menjalankan fungsi keluar
        //saat tombol close di klik
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                Keluar();
            }
        });

        //mengatur tombol alt pada menu fulldown
        mnuMaster.setMnemonic('M');
        mnuTransaksi.setMnemonic('T');
        mnuCetak.setMnemonic('C');
        mnuAbout.setMnemonic('A');
        mnuMstBarang.setMnemonic('B');
        mnuMstPelanggan.setMnemonic('P');
        mnuMstSatuan.setMnemonic('S');
        mnuMstKota.setMnemonic('K');
        mnuMstProvinsi.setMnemonic('V');
        mnuKeluar.setMnemonic('X');
        mnuTrsPesanan.setMnemonic('E');
        mnuAuthor.setMnemonic('H');

        //mengatur penggunaan shortcut pada menu pulldown
        mnuMstBarang.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK));
        mnuMstPelanggan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
        mnuKeluar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
        mnuTrsPesanan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
        mnuCetMstBarang.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, Event.CTRL_MASK));
        mnuCetTrsPesanan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, Event.CTRL_MASK));
        mnuAuthor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, Event.CTRL_MASK));

        //mengatur menu pulldown agar mengerti saat di click
        mnuKeluar.addActionListener(this);
        mnuAuthor.addActionListener(this);

        //menambahkan sub menu ke masing-masing menu baris
        mnuMaster.add(mnuMstBarang);
        mnuMaster.add(mnuMstPelanggan);
        mnuMaster.addSeparator();
        mnuMaster.add(mnuMstSatuan);
        mnuMaster.add(mnuMstKota);
        mnuMaster.add(mnuMstProvinsi);
        mnuMaster.addSeparator();
        mnuMaster.add(mnuKeluar);

        mnuTransaksi.add(mnuTrsPesanan);

        mnuCetak.add(mnuCetMstBarang);
        mnuCetak.add(mnuCetTrsPesanan);

        mnuAbout.add(mnuAuthor);

        //mengatur objek barMenu untuk menampung menu baris.
        setJMenuBar(barMenu);

        //menambahkan menu baris ke menu bar
        barMenu.add(mnuMaster);
        barMenu.add(mnuTransaksi);
        barMenu.add(mnuCetak);
        barMenu.add(mnuAbout);

        //menambahkan sub menu popup ke objek popmenu
        popMenu.add(mnuPopMstBarang);
        popMenu.add(mnuPopMstPelanggan);
        popMenu.add(mnuPopTrsPesanan);

        //menampilkan menu PopUp saat click kanan pada mouse
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                checkMouseTrigger(me);
            }

            public void mouseReleased(MouseEvent me) {
                checkMouseTrigger(me);
            }

            private void checkMouseTrigger(MouseEvent me) {
                if (me.isPopupTrigger()) {
                    popMenu.show(me.getComponent(), me.getX(), me.getY());
                }
            }
        });

        //mengatur string saat mouse mendekati objek tombol 
        mnuBtnMstBarang.setToolTipText("Master Barang");
        mnuBtnMstPelanggan.setToolTipText("Master Pelanggan");
        mnuBtnTrsPesanan.setToolTipText("Transaksi Pesanan");
        mnuBtnAuthor.setToolTipText("Author");

        //mengatur menu pulldown agar mengerti saat di click
        mnuKeluar.addActionListener(this);

        //mengatur menu popup agar mengerti saat di klik
        //menambahkan objek tombol ke ToolbarPanel1
        ToolbarPanel1.add(mnuBtnMstBarang);
        ToolbarPanel1.add(mnuBtnMstPelanggan);
        ToolbarPanel1.add(mnuBtnTrsPesanan);
        ToolbarPanel1.add(mnuBtnAuthor);

        //menambahkan objek tombol ke ToolbarPanel2
        lblJam.setForeground(Color.red);
        lblJam.setFont(new Font("Arial", Font.BOLD, 30));

        //menambahkan objek label ke dalam baris status
        statusBar.add(lblAuthor, BorderLayout.WEST);
        statusBar.add(lblTgl, BorderLayout.EAST);

        //mengatur warna latar objek desktop
        desktop.setBackground(Color.gray);

        //menambahkan isi kontainder dengan objek
        //toolbar, desktop dan statusbar dengan layout border
        getContentPane().add(desktop, BorderLayout.CENTER);
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(statusBar, BorderLayout.SOUTH);

        //menjalankan jam digital
        setJam();

        //menampilkan menu utama ke layar monitor
        show();
    }

    /**
     * method untuk memeriksa objek yang sebelumnya mendapat listener
     * "addActionListener"
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent AE) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object obj = AE.getSource();

        if (obj == mnuKeluar) {
            Keluar();
        }

        if (obj == mnuAuthor || obj == mnuBtnAuthor) {
            String msg = "Sistem Informasi Pemesanan Barang \n"
                    + "dengan Bahasa Java dan Database Access \n Versi 1.2 \n \n"
                    + "Oleh : \n Anif \n AEndF@2007-2008\n";
            JOptionPane.showMessageDialog(this, msg, "About-System and Author",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Metode atau Fungsi keluar dari sistem
     */
    private void Keluar() {
        try {
            int reply = JOptionPane.showConfirmDialog(this,
                    "Yakin Mau Keluar dari Sistem Penjualan?",
                    "Sistem Penjualan - Keluar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (reply == JOptionPane.YES_OPTION) {
                setVisible(false); //menyembunyikan frame
                dispose(); //membersihkan resource
                System.out.println("Terimakasih sudah menggunakan sistem ini.");
                System.out.println("Author ->>>-M.Anif-<<<-");
                System.out.println("\nAENDF@2008");
                //membebaskan aplikasi dari memmory
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0); // keluar dari aplikasi
            }
        } catch (Exception e) {

        }
    }

    /**
     * metode atau fungsi pengaturan waktu dari sistem
     */
    public void setJam() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "", nol_detik = "";

                Date dateTime = new Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();

                if (nilai_jam <= 9) {
                    nol_jam = "0";
                }
                if (nilai_menit <= 9) {
                    nol_menit = "0";
                }
                if (nilai_detik <= 9) {
                    nol_detik = "0";
                }

                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                lblJam.setText(jam + ":" + menit + ":" + detik + "");
            }
        };
        new Timer(1000, taskPerformer).start();
    }
}
