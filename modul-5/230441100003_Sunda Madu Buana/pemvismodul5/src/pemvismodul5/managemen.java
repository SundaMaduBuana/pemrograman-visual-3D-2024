/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pemvismodul5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class managemen extends javax.swing.JFrame {

    Connection conn; //mendapatkan koneksi database
    private DefaultTableModel model_tbl_managemen;
    private DefaultTableModel model_tbl_proyek;
    private DefaultTableModel model_tbl_transaksi;

    public managemen() {
        initComponents();
        setLocationRelativeTo(null);
        conn = koneksi.getConnection();
        model_tbl_managemen = new DefaultTableModel(); // tabel karyawan
        tbkaryawan.setModel(model_tbl_managemen);
        model_tbl_managemen.addColumn("ID");
        model_tbl_managemen.addColumn("Nama");
        model_tbl_managemen.addColumn("Jabatan");
        model_tbl_managemen.addColumn("Departemen");

        loadDataKaryawan();
        setButtonKaryawan(); // Set button karyawan
        ListenerManagemen(); // mengatur status tombol karyawan berdasarkan input
        tbkaryawan.setDefaultEditor(Object.class, null); // agar tabel karyawan tidak bisa di edit

        model_tbl_proyek = new DefaultTableModel(); // tabel proyek
        tbProyek.setModel(model_tbl_proyek);
        model_tbl_proyek.addColumn("ID");
        model_tbl_proyek.addColumn("Nama Proyek");
        model_tbl_proyek.addColumn("Durasi Pengerjaan");

        loadDataProyek();
        setButtonProyek(); // set button proyek
        ListenerManagemen(); // mengatur status tombol proyek berdasarkan input
        tbProyek.setDefaultEditor(Object.class, null); // agar tabel proyek tidak bisa di edit

        model_tbl_transaksi = new DefaultTableModel(); // tabel transaksi
        tbtransaksi.setModel(model_tbl_transaksi);
        model_tbl_transaksi.addColumn("ID Karyawan");
        model_tbl_transaksi.addColumn("ID Proyek");
        model_tbl_transaksi.addColumn("Peran");

        loadDataTransaksi();
        loadComboBoxKaryawan();
        loadComboBoxProyek();
        tbProyek.setDefaultEditor(Object.class, null); // agar tabel transaksi tidak bisa di edit
    }

    private void loadDataKaryawan() {
        model_tbl_managemen.setRowCount(0);
        try {
            String sql = "SELECT * FROM karyawan";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();
            while (hasil.next()) {
                // Menambahkan baris ke dalam model tabel
                model_tbl_managemen.addRow(new Object[]{
                    hasil.getInt("id"),
                    hasil.getString("nama"),
                    hasil.getString("jabatan"),
                    hasil.getString("departemen")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Save Data" + e.getMessage());
        }
    }

    private void loadDataProyek() {
        model_tbl_proyek.setRowCount(0);
        try {
            String sql = "SELECT * FROM proyek";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();
            while (hasil.next()) {
                // Menambahkan baris ke dalam model tabel
                int durasi = hasil.getInt("durasi_pengerjaan");
                String durasii = durasi + " Minggu";
                model_tbl_proyek.addRow(new Object[]{
                    hasil.getInt("id"),
                    hasil.getString("nama_proyek"),
                    durasii
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Save Data" + e.getMessage());
        }
    }

    private void loadDataTransaksi() {
        model_tbl_transaksi.setRowCount(0);
        try {
            String sql = "SELECT * FROM transaksi";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();
            while (hasil.next()) {
                // Menambahkan baris ke dalam model tabel
                model_tbl_transaksi.addRow(new Object[]{
                    hasil.getInt("id_karyawan"),
                    hasil.getString("id_proyek"),
                    hasil.getString("peran")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Save Data" + e.getMessage());
        }
    }

    private void loadComboBoxKaryawan() {
        try {
            String sql = "SELECT id, nama FROM karyawan";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();
            cbkaryawan.removeAllItems(); // Clear existing items
            cbkaryawan.addItem("Pilih"); // Add default item
            while (hasil.next()) {
                cbkaryawan.addItem(hasil.getInt("id") + " - " + hasil.getString("nama"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading karyawan: " + e.getMessage());
        }
    }

    private void loadComboBoxProyek() {
        try {
            String sql = "SELECT id, nama_proyek FROM proyek";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();
            cbproyek.removeAllItems();
            cbproyek.addItem("Pilih");
            while (hasil.next()) {
                cbproyek.addItem(hasil.getInt("id") + " - " + hasil.getString("nama_proyek"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading proyek: " + e.getMessage());
        }
    }

    private void saveDataKaryawan() {
        try {
            String sql = "INSERT INTO karyawan (nama, jabatan, departemen) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tfnamaKaryawan.getText());
            ps.setString(2, tfjbtnKaryawan.getText());
            ps.setString(3, tfdeparKaryawan.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data saved successfully");
            resetDatakaryawan(); // Mengosongkan text field
            loadDataKaryawan();
            setButtonKaryawan(); // Update button state
        } catch (SQLException e) {
            System.out.println("Error Save Data: " + e.getMessage());
        }
    }

    private void saveDataProyek() {
        try {
            int durasi = Integer.parseInt(tfdurasiProyek.getText());
            String sql = "INSERT INTO proyek (nama_proyek, durasi_pengerjaan) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tfnamaProyek.getText());
            ps.setInt(2, durasi);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data saved successfully");
            resetDataProyek(); // Mengosongkan text field
            loadDataProyek();
            setButtonProyek(); // Update button state
        } catch (SQLException e) {
            System.out.println("Error Save Data: " + e.getMessage());
        }
    }

    private void saveDataTransaksi() {
        try {
            String selectedKaryawan = (String) cbkaryawan.getSelectedItem();
            String selectedProyek = (String) cbproyek.getSelectedItem();

            if (selectedKaryawan.equals("Pilih") || selectedProyek.equals("Pilih")) {
                JOptionPane.showMessageDialog(this, "Silakan pilih karyawan dan proyek yang valid.");
                return;
            }
            int idKaryawan = Integer.parseInt(selectedKaryawan.split(" - ")[0]);
            int idProyek = Integer.parseInt(selectedProyek.split(" - ")[0]);

            String sql = "INSERT INTO transaksi (id_karyawan, id_proyek, peran) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKaryawan);
            ps.setInt(2, idProyek);
            ps.setString(3, tfperan.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data saved successfully");
            resetDataTransaksi(); // hapus data 
            loadDataTransaksi();
        } catch (SQLException e) {
            System.out.println("Error Save Data: " + e.getMessage());
        }
    }

    private void updateDataKaryawan() {
        try {
            String sql = "UPDATE karyawan SET nama = ?, jabatan = ?, departemen = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tfnamaKaryawan.getText());
            ps.setString(2, tfjbtnKaryawan.getText());
            ps.setString(3, tfdeparKaryawan.getText());
            ps.setInt(4, Integer.parseInt(tfIDkaryawan.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data updated successfully");
            resetDatakaryawan(); // Mengosongkan text field
            loadDataKaryawan();
            setButtonKaryawan(); // Update button state
        } catch (SQLException e) {
            System.out.println("Error Update Data: " + e.getMessage());
        }
    }

    private void updateDataProyek() {
        try {
            String sql = "UPDATE proyek SET nama_proyek = ?, durasi_pengerjaan = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tfnamaProyek.getText());
            ps.setString(2, tfdurasiProyek.getText());
            ps.setInt(3, Integer.parseInt(tfIDproyek.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data updated successfully");
            resetDataProyek(); // Mengosongkan text field
            loadDataProyek();
            setButtonProyek(); // Update button state
        } catch (SQLException e) {
            System.out.println("Error Update Data: " + e.getMessage());
        }
    }

    private void updateDataTransaksi() {
        try {
            String selectedKaryawan = (String) cbkaryawan.getSelectedItem();
            String selectedProyek = (String) cbproyek.getSelectedItem();

            if (selectedKaryawan.equals("Pilih") || selectedProyek.equals("Pilih")) {
                JOptionPane.showMessageDialog(this, "Silakan pilih karyawan dan proyek yang valid.");
                return;
            }

            int idKaryawan = Integer.parseInt(selectedKaryawan.split(" - ")[0]);
            int idProyek = Integer.parseInt(selectedProyek.split(" - ")[0]);
            String peran = tfperan.getText();

            String sql = "UPDATE transaksi SET peran = ? WHERE id_karyawan = ? AND id_proyek = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, peran); 
            ps.setInt(2, idKaryawan); 
            ps.setInt(3, idProyek); 
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Data updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan untuk karyawan dan proyek yang dipilih.");
            }

            resetDataTransaksi();
            loadDataTransaksi();
        } catch (SQLException e) {
            System.out.println("Error Update Data: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memperbarui data: " + e.getMessage());
        }
    }

    private void deleteDataKaryawan() {
        try {
            String sql = "DELETE FROM karyawan WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tfIDkaryawan.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data deleted successfully");
            resetDatakaryawan(); // Mengosongkan text field
            loadDataKaryawan();
            setButtonKaryawan(); // Update button state
        } catch (SQLException e) {
            System.out.println("Error Delete Data: " + e.getMessage());
        }
    }

    private void deleteDataProyek() {
        try {
            String sql = "DELETE FROM proyek WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tfIDproyek.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data deleted successfully");
            resetDataProyek(); // Mengosongkan text field
            loadDataProyek();
            setButtonProyek(); // Update button state
        } catch (SQLException e) {
            System.out.println("Error Delete Data: " + e.getMessage());
        }
    }

    private void deleteDataTransaksi() {
        try {
            String selectedKaryawan = (String) cbkaryawan.getSelectedItem();
            String selectedProyek = (String) cbproyek.getSelectedItem();

            // Check if the selected items are valid
            if (selectedKaryawan.equals("Pilih") || selectedProyek.equals("Pilih")) {
                JOptionPane.showMessageDialog(this, "Silakan pilih karyawan dan proyek yang valid.");
                return;
            }
            int idKaryawan = Integer.parseInt(selectedKaryawan.split(" - ")[0]);
            int idProyek = Integer.parseInt(selectedProyek.split(" - ")[0]);

            String sql = "DELETE FROM transaksi WHERE id_karyawan = ? AND id_proyek = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKaryawan);
            ps.setInt(2, idProyek);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Data deleted successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan untuk karyawan dan proyek yang dipilih.");
            }

            resetDataTransaksi();
            loadDataTransaksi();
        } catch (SQLException e) {
            System.out.println("Error Delete Data: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data: " + e.getMessage());
        }
    }

    private void resetDatakaryawan() {
        tfnamaKaryawan.setText("");
        tfjbtnKaryawan.setText("");
        tfdeparKaryawan.setText("");
        tfIDkaryawan.setText("");
        setButtonKaryawan(); // mengupdate button setelah di reset
    }

    private void resetDataProyek() {
        tfnamaProyek.setText("");
        tfdurasiProyek.setText("");
        tfIDproyek.setText("");
        setButtonProyek();
    }

    private void resetDataTransaksi() {
        cbkaryawan.setSelectedItem("Pilih");
        cbproyek.setSelectedItem("Pilih");
        tfperan.setText("");
    }

    private void setButtonKaryawan() {
        // Mengatur status tombol berdasarkan isi text field
        boolean isIdFilled = !tfIDkaryawan.getText().trim().isEmpty();
        boolean isAllFilled = !tfnamaKaryawan.getText().trim().isEmpty()
                && !tfjbtnKaryawan.getText().trim().isEmpty()
                && !tfdeparKaryawan.getText().trim().isEmpty();

        // nonaktifkan button kl semuanya kosong
        if (!isIdFilled && !isAllFilled) {
            btnsimpankaryawan.setEnabled(false);
            btnupdatekaryawan.setEnabled(false);
            btndeletekaryawan.setEnabled(false);
        } else {
            btnsimpankaryawan.setEnabled(!isIdFilled); // Simpan hanya aktif jika ID kosong
            btnupdatekaryawan.setEnabled(isIdFilled && isAllFilled); // Update hanya aktif jika semua field terisi
            btndeletekaryawan.setEnabled(isIdFilled && !isAllFilled); // Delete hanya aktif jika ID terisi
        }
    }

    private void setButtonProyek() {
        // Mengatur status tombol berdasarkan isi text field
        boolean isIdFilled = !tfIDproyek.getText().trim().isEmpty();
        boolean isAllFilled = !tfnamaProyek.getText().trim().isEmpty()
                && !tfdurasiProyek.getText().trim().isEmpty();

        if (!isIdFilled && !isAllFilled) {
            btnsimpanProyek.setEnabled(false);
            btnupdateProyek.setEnabled(false);
            btndeleteProyek.setEnabled(false);
        } else {
            btnsimpanProyek.setEnabled(!isIdFilled); // Simpan hanya aktif jika ID kosong
            btnupdateProyek.setEnabled(isIdFilled && isAllFilled); // Update hanya aktif jika semua field terisi
            btndeleteProyek.setEnabled(isIdFilled && !isAllFilled); // Delete hanya aktif jika ID terisi
        }
    }
    
    private void ListenerManagemen() {
        // Menambahkan DocumentListener untuk memantau perubahan pada text field
        DocumentListener listenerkaryawan = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                setButtonKaryawan();
            }
            public void removeUpdate(DocumentEvent e) {
                setButtonKaryawan();
            }
            public void changedUpdate(DocumentEvent e) {
                setButtonKaryawan();
            }
        };

        tfIDkaryawan.getDocument().addDocumentListener(listenerkaryawan);
        tfnamaKaryawan.getDocument().addDocumentListener(listenerkaryawan);
        tfjbtnKaryawan.getDocument().addDocumentListener(listenerkaryawan);
        tfdeparKaryawan.getDocument().addDocumentListener(listenerkaryawan);

        DocumentListener listenerproyek = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                setButtonProyek();
            }
            public void removeUpdate(DocumentEvent e) {
                setButtonProyek();
            }
            public void changedUpdate(DocumentEvent e) {
                setButtonProyek();
            }
        };

        tfIDproyek.getDocument().addDocumentListener(listenerproyek);
        tfnamaProyek.getDocument().addDocumentListener(listenerproyek);
        tfdurasiProyek.getDocument().addDocumentListener(listenerproyek);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfIDkaryawan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfnamaKaryawan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfjbtnKaryawan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfdeparKaryawan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnsimpankaryawan = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnupdatekaryawan = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btndeletekaryawan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbkaryawan = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tfIDproyek = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfnamaProyek = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfdurasiProyek = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnupdateProyek = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btndeleteProyek = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btnsimpanProyek = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProyek = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cbkaryawan = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbproyek = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        tfperan = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        btnupdatetransaksi = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        btndeletetransaksi = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        btnsimpantransaksi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbtransaksi = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 249, 191));

        jPanel2.setBackground(new java.awt.Color(198, 231, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(212, 246, 255));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel5.add(jLabel3, gridBagConstraints);

        tfIDkaryawan.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 3, 5, 4);
        jPanel5.add(tfIDkaryawan, gridBagConstraints);

        jLabel4.setText("Nama");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel5.add(jLabel4, gridBagConstraints);

        tfnamaKaryawan.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 3, 5, 4);
        jPanel5.add(tfnamaKaryawan, gridBagConstraints);

        jLabel5.setText("Jabatan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel5.add(jLabel5, gridBagConstraints);

        tfjbtnKaryawan.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 3, 5, 4);
        jPanel5.add(tfjbtnKaryawan, gridBagConstraints);

        jLabel6.setText("Departemen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel5.add(jLabel6, gridBagConstraints);

        tfdeparKaryawan.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 3, 5, 4);
        jPanel5.add(tfdeparKaryawan, gridBagConstraints);

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 260, 150));

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel2.setText("INPUT DATA KARYAWAN");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        btnsimpankaryawan.setBackground(new java.awt.Color(182, 249, 255));
        btnsimpankaryawan.setText("Simpan");
        btnsimpankaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpankaryawanActionPerformed(evt);
            }
        });
        jPanel6.add(btnsimpankaryawan);

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 100, 30));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        btnupdatekaryawan.setBackground(new java.awt.Color(182, 249, 255));
        btnupdatekaryawan.setText("Update");
        btnupdatekaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdatekaryawanActionPerformed(evt);
            }
        });
        jPanel7.add(btnupdatekaryawan);

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 100, 30));

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        btndeletekaryawan.setBackground(new java.awt.Color(182, 249, 255));
        btndeletekaryawan.setText("Delete");
        btndeletekaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletekaryawanActionPerformed(evt);
            }
        });
        jPanel8.add(btndeletekaryawan);

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 100, 30));

        tbkaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Jabatan", "Departemen"
            }
        ));
        jScrollPane1.setViewportView(tbkaryawan);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 520, 185));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pemvismodul5/karyawan.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, -1));

        jTabbedPane1.addTab("Karyawan", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 215, 196));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel8.setText("INPUT DATA PROYEK");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 207, 179));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel9.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 4, 6);
        jPanel9.add(jLabel9, gridBagConstraints);

        tfIDproyek.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 4, 3);
        jPanel9.add(tfIDproyek, gridBagConstraints);

        jLabel10.setText("Nama Proyek");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 4, 6);
        jPanel9.add(jLabel10, gridBagConstraints);

        tfnamaProyek.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 4, 3);
        jPanel9.add(tfnamaProyek, gridBagConstraints);

        jLabel11.setText("Durasi Pengerjaan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 4, 6);
        jPanel9.add(jLabel11, gridBagConstraints);

        tfdurasiProyek.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 4, 3);
        jPanel9.add(tfdurasiProyek, gridBagConstraints);

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 130));

        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        btnupdateProyek.setBackground(new java.awt.Color(255, 245, 205));
        btnupdateProyek.setText("Update");
        btnupdateProyek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateProyekActionPerformed(evt);
            }
        });
        jPanel10.add(btnupdateProyek);

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 90, 30));

        jPanel11.setLayout(new java.awt.GridLayout(1, 0));

        btndeleteProyek.setBackground(new java.awt.Color(255, 245, 205));
        btndeleteProyek.setText("Delete");
        btndeleteProyek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteProyekActionPerformed(evt);
            }
        });
        jPanel11.add(btndeleteProyek);

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 90, 30));

        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        btnsimpanProyek.setBackground(new java.awt.Color(255, 245, 205));
        btnsimpanProyek.setText("Simpan");
        btnsimpanProyek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanProyekActionPerformed(evt);
            }
        });
        jPanel12.add(btnsimpanProyek);

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 90, 30));

        tbProyek.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Proyek", "Durasi Pengerjaan"
            }
        ));
        jScrollPane2.setViewportView(tbProyek);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 540, 200));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pemvismodul5/proyek.png"))); // NOI18N
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        jTabbedPane1.addTab("Proyek", jPanel3);

        jPanel4.setBackground(new java.awt.Color(198, 231, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel12.setText("INPUT DATA TRANSAKSI");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jPanel13.setBackground(new java.awt.Color(212, 246, 255));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel13.setText("ID Karyawan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(jLabel13, gridBagConstraints);

        cbkaryawan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbkaryawan.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(cbkaryawan, gridBagConstraints);

        jLabel14.setText("ID Proyek");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(jLabel14, gridBagConstraints);

        cbproyek.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbproyek.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(cbproyek, gridBagConstraints);

        jLabel15.setText("Peran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(jLabel15, gridBagConstraints);

        tfperan.setPreferredSize(new java.awt.Dimension(125, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(tfperan, gridBagConstraints);

        jPanel4.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 260, 130));

        jPanel14.setBackground(new java.awt.Color(241, 200, 240));
        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        btnupdatetransaksi.setBackground(new java.awt.Color(182, 249, 255));
        btnupdatetransaksi.setText("Update");
        btnupdatetransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdatetransaksiActionPerformed(evt);
            }
        });
        jPanel14.add(btnupdatetransaksi);

        jPanel4.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 90, 30));

        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        btndeletetransaksi.setBackground(new java.awt.Color(182, 249, 255));
        btndeletetransaksi.setText("Delete");
        btndeletetransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletetransaksiActionPerformed(evt);
            }
        });
        jPanel15.add(btndeletetransaksi);

        jPanel4.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 90, 30));

        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        btnsimpantransaksi.setBackground(new java.awt.Color(182, 249, 255));
        btnsimpantransaksi.setText("Simpan");
        btnsimpantransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpantransaksiActionPerformed(evt);
            }
        });
        jPanel16.add(btnsimpantransaksi);

        jPanel4.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 90, 30));

        tbtransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Karyawan", "ID Proyek", "Peran"
            }
        ));
        jScrollPane3.setViewportView(tbtransaksi);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 520, 180));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pemvismodul5/transaksi.png"))); // NOI18N
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        jTabbedPane1.addTab("Transaksi", jPanel4);

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 22)); // NOI18N
        jLabel1.setText("Aplikasi Managemen Karyawan Dan Proyek");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnupdatekaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdatekaryawanActionPerformed
        updateDataKaryawan();
    }//GEN-LAST:event_btnupdatekaryawanActionPerformed

    private void btnsimpankaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpankaryawanActionPerformed
        saveDataKaryawan();
    }//GEN-LAST:event_btnsimpankaryawanActionPerformed

    private void btndeletekaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletekaryawanActionPerformed
        deleteDataKaryawan();
    }//GEN-LAST:event_btndeletekaryawanActionPerformed

    private void btnsimpanProyekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanProyekActionPerformed
        saveDataProyek();
    }//GEN-LAST:event_btnsimpanProyekActionPerformed

    private void btnupdateProyekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateProyekActionPerformed
        updateDataProyek();
    }//GEN-LAST:event_btnupdateProyekActionPerformed

    private void btndeleteProyekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteProyekActionPerformed
        deleteDataProyek();
    }//GEN-LAST:event_btndeleteProyekActionPerformed

    private void btnsimpantransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpantransaksiActionPerformed
        saveDataTransaksi();
    }//GEN-LAST:event_btnsimpantransaksiActionPerformed

    private void btnupdatetransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdatetransaksiActionPerformed
        updateDataTransaksi();
    }//GEN-LAST:event_btnupdatetransaksiActionPerformed

    private void btndeletetransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletetransaksiActionPerformed
        deleteDataTransaksi();
    }//GEN-LAST:event_btndeletetransaksiActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(managemen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(managemen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(managemen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(managemen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new managemen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndeleteProyek;
    private javax.swing.JButton btndeletekaryawan;
    private javax.swing.JButton btndeletetransaksi;
    private javax.swing.JButton btnsimpanProyek;
    private javax.swing.JButton btnsimpankaryawan;
    private javax.swing.JButton btnsimpantransaksi;
    private javax.swing.JButton btnupdateProyek;
    private javax.swing.JButton btnupdatekaryawan;
    private javax.swing.JButton btnupdatetransaksi;
    private javax.swing.JComboBox<String> cbkaryawan;
    private javax.swing.JComboBox<String> cbproyek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbProyek;
    private javax.swing.JTable tbkaryawan;
    private javax.swing.JTable tbtransaksi;
    private javax.swing.JTextField tfIDkaryawan;
    private javax.swing.JTextField tfIDproyek;
    private javax.swing.JTextField tfdeparKaryawan;
    private javax.swing.JTextField tfdurasiProyek;
    private javax.swing.JTextField tfjbtnKaryawan;
    private javax.swing.JTextField tfnamaKaryawan;
    private javax.swing.JTextField tfnamaProyek;
    private javax.swing.JTextField tfperan;
    // End of variables declaration//GEN-END:variables
}
