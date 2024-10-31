/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pemvismodul4;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
public class permainan_angka extends javax.swing.JFrame {
    Random rndm = new Random();
    int target = 0;
    int kesempatan = 10;
    int score = 0;
    ArrayList<String> daftaruser = new ArrayList<>();

    public permainan_angka() {
        initComponents();
        setLocationRelativeTo(null);
        resetGame();
        
        tfcheck.setEditable(false);
        tfscore.setEditable(false);       
        tfkesempatan.setEditable(false); 
        TAskor.setEditable(false);
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    } // untuk memeriksa hnya berupa angka

    private void resetGame() { //mereset game
        target = rndm.nextInt(100) + 1;  // Angka acak antara 1 - 100
        kesempatan = 10;
        score = 0;
        tfscore.setText("0");
        tfcheck.setText("");
        tfkesempatan.setText("10"); 
        tfangka.setText("");
    }

    private void updatedaftar() { //untuk menambahkan daftar nama pemain kedlm TA skor
        TAskor.setText("");
        for (String s : daftaruser) {
            TAskor.append(s + "\n");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btntebak = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfangka = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfcheck = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfscore = new javax.swing.JTextField();
        tfkesempatan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TAskor = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(228, 177, 240));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btntebak.setText("tebak");
        btntebak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntebakActionPerformed(evt);
            }
        });
        jPanel4.add(btntebak);

        jLabel2.setText("Silahkan masukkan angka :");

        tfangka.setBackground(new java.awt.Color(255, 225, 255));
        tfangka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfangkaKeyReleased(evt);
            }
        });

        jLabel3.setText("Check :");

        tfcheck.setBackground(new java.awt.Color(255, 225, 255));

        jLabel4.setText("Score :");

        jLabel7.setText("Kesempatan :");

        tfscore.setBackground(new java.awt.Color(255, 225, 255));

        tfkesempatan.setBackground(new java.awt.Color(255, 225, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(tfcheck, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfangka)
                    .addComponent(tfscore, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(tfkesempatan, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfangka, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(tfscore, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfkesempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("tebak angka", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        TAskor.setBackground(new java.awt.Color(255, 225, 255));
        TAskor.setColumns(20);
        TAskor.setRows(5);
        jScrollPane1.setViewportView(TAskor);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("score pemain", jPanel3);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 270, 330));

        jLabel1.setFont(new java.awt.Font("Stencil", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PERMAINAN TEBAK ANGKA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\USER\\Pictures\\pnjel.png")); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\USER\\Pictures\\rmbut1.png")); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, -1));

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

    private void btntebakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntebakActionPerformed
        String masukkan = tfangka.getText();
        
        if (!isNumeric(masukkan)) { //untuk memvalidasi inputan berupa angka
            JOptionPane.showMessageDialog(null, "Masukkan angka yang valid!");
            return;
        }
        int tebakan = Integer.parseInt(masukkan);
        kesempatan--;

        if (tebakan == target) {
            tfcheck.setText("Anda berhasil menebak angka dengan benar!");
            String nama = JOptionPane.showInputDialog("SELAMAT ANDA MENANG! Masukkan nama Anda:");
            score = kesempatan * 10;
            tfscore.setText(String.valueOf(score));
            daftaruser.add(nama + " : " + score);
            updatedaftar();
            resetGame();
        } else {
            if (tebakan > target) {
                tfcheck.setText("Angka terlalu besar!");
            } else {
                tfcheck.setText("Angka terlalu kecil!");
            }
            tfkesempatan.setText(String.valueOf(kesempatan));

            if (kesempatan <= 0) {
                JOptionPane.showMessageDialog(null, "Anda Gagal. Silahkan coba lagi.");
                resetGame();
            }
        }
    }//GEN-LAST:event_btntebakActionPerformed

    private void tfangkaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfangkaKeyReleased
        String input = tfangka.getText();
        // Jika input bukan angka, kosongkan teks
        if (!input.matches("\\d*")) {  
            tfangka.setText("");
        }
    }//GEN-LAST:event_tfangkaKeyReleased

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new permainan_angka().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TAskor;
    private javax.swing.JButton btntebak;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField tfangka;
    private javax.swing.JTextField tfcheck;
    private javax.swing.JTextField tfkesempatan;
    private javax.swing.JTextField tfscore;
    // End of variables declaration//GEN-END:variables
}
