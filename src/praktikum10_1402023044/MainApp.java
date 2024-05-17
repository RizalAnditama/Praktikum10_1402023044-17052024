package praktikum10_1402023044;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rizal Anditama
 */
public class MainApp extends javax.swing.JFrame {

    public String nama_depan;
    public String nama_belakang;
    public String jenis_kelamin;
    public String jenis_pengemudi;
    public boolean setuju_ketentuan;
    public String captcha;
    public ButtonGroup buttonGroup;

    Connection conn;

    private static JDialog dialog;
    JFrame jFrame;
    JTable jTable;
    DefaultTableModel table;
    JScrollPane jScrollPane;

    /**
     * Creates new form MainApp
     *
     * @throws java.sql.SQLException
     */
    public MainApp() {
        initComponents();
        initTable();
        initDialog();

        conn = new Connection();
        jFrame = new JFrame();

        setCaptcha(generateCaptcha());
        fieldNamaDepan.addActionListener(enterPressed);
        fIeldNamaBelakang.addActionListener(enterPressed);
        CaptchaField.addActionListener(enterPressed);
    }

    /**
     * Self made code
     */
    private void initTable() {

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nama Depan", "Nama Belakang", "Jenis Kelamin", "Jenis Pengemudi"}, 0);
        JTable table = new JTable(model);

        try ( 
                java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/praktikum10_1402023044", "root", "");  
                Statement stm = conn.createStatement();  
                ResultSet rs = stm.executeQuery("SELECT * FROM pengemudi")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String namaDepan = rs.getString("nama_depan");
                String namaBelakang = rs.getString("nama_belakang");
                String jenisKelamin = rs.getString("jenis_kelamin");
                String jenisPengemudi = rs.getString("jenis_pengemudi");
                model.addRow(new Object[]{id, namaDepan, namaBelakang, jenisKelamin, jenisPengemudi});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
//        jTable = new JTable(conn.buildTableModel(conn.getAll()));
        jScrollPane = new JScrollPane(table);
    }

    private void initDialog() {
        // init jDialog
        dialog = new JDialog(jFrame, "Daftar pengemudi", true);
        dialog.setLayout(new FlowLayout());
        dialog.add(jScrollPane);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();

//        dialog.setSize(600, 600);
        dialog.setVisible(false);
    }

//    public void refreshTable() {
//        // reset row
//        int jumlahRow = table.getRowCount();
//        for (int i = jumlahRow - 1; i >= 0; i--) {
//            table.removeRow(i);
//        }
//
//        ResultSet data = conn.getAll();
//        try {
//            while (data.next()) {
//                table.addRow(new Object[]{
//                    data.getString("id"),
//                    data.getString("nama_depan"),
//                    data.getString("nama_belakang"),
//                    data.getString("jenis_kelamin"),
//                    data.getString("jenis_pengemudi"),});
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    void refreshPage() {
        fieldNamaDepan.setText(null);
        fIeldNamaBelakang.setText(null);
        jRadioButton1.setSelected(true);

        ComboBoxJenisPengemudi.removeAllItems();
        ComboBoxJenisPengemudi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"", "Pilih Jenis Pengemudi", "Motor", "Mobil"}));
        ComboBoxJenisPengemudi.setSelectedIndex(0);

        checkBoxSetujuKetentuan.setSelected(false);
        CaptchaField.setText(null);
        setCaptcha(generateCaptcha());
    }

    private void setCaptcha(String captcha) {
        this.captcha = captcha;
        String shownCaptcha = "";
        for (int i = 0; i < captcha.length(); i++) {
            shownCaptcha += captcha.charAt(i) + " ";
        }
        CaptchaLabel.setText(shownCaptcha);
    }

    private String generateCaptcha() {
        Random random = new Random();
        String generatedCaptcha = "";
        while (generatedCaptcha.length() < 4) {
            int randomNumber = random.nextInt(73) + 48;
            while ((randomNumber >= 58 && randomNumber <= 64) || (randomNumber >= 91 && randomNumber <= 96)) {
                randomNumber = (random.nextInt(73) + 48);
            }
            generatedCaptcha += (char) randomNumber;
            System.out.println(randomNumber + ": " + (char) randomNumber);
        }
        return generatedCaptcha;
    }

    boolean verifyCaptcha(String insertedCaptcha) {
        return !insertedCaptcha.equals(captcha);
    }

    public void infoMessage(String message, String title) {
        JOptionPane.showMessageDialog(rootPane, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void warningMessage(String message, String title) {
        JOptionPane.showMessageDialog(rootPane, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public void errorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    Action enterPressed = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonDaftar.doClick();
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fieldNamaDepan = new javax.swing.JTextField();
        LabelNamaDepan = new javax.swing.JLabel();
        fIeldNamaBelakang = new javax.swing.JTextField();
        LabelNamaDepan1 = new javax.swing.JLabel();
        LabelJenisKelamin = new javax.swing.JLabel();
        RadioButtonPria = new javax.swing.JRadioButton();
        RadioButtonWanita = new javax.swing.JRadioButton();
        ComboBoxJenisPengemudi = new javax.swing.JComboBox<>();
        checkBoxSetujuKetentuan = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        CaptchaField = new javax.swing.JTextField();
        CaptchaLabel = new javax.swing.JLabel();
        buttonLihatPendaftar = new javax.swing.JButton();
        buttonDaftar = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Ojek Online FTI");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Daftar Sekarang !");

        fieldNamaDepan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNamaDepanActionPerformed(evt);
            }
        });

        LabelNamaDepan.setText("  Nama Depan");

        fIeldNamaBelakang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fIeldNamaBelakangActionPerformed(evt);
            }
        });

        LabelNamaDepan1.setText("  Nama Belakang");

        LabelJenisKelamin.setText("Jenis Kelamin");

        buttonGroup1.add(RadioButtonPria);
        RadioButtonPria.setLabel("Pria");
        RadioButtonPria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonPriaActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioButtonWanita);
        RadioButtonWanita.setLabel("Wanita");

        ComboBoxJenisPengemudi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jenis Pengemudi", "Motor", "Mobil" }));
        ComboBoxJenisPengemudi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxJenisPengemudiActionPerformed(evt);
            }
        });

        checkBoxSetujuKetentuan.setText("Saya menyetujui peraturan yang berlaku");
        checkBoxSetujuKetentuan.setActionCommand("      Saya menyetujui peraturan yang berlaku");
        checkBoxSetujuKetentuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxSetujuKetentuanActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Masukkan Captcha");

        CaptchaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaptchaFieldActionPerformed(evt);
            }
        });

        CaptchaLabel.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        CaptchaLabel.setText("Captcha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(CaptchaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(CaptchaField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(CaptchaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CaptchaLabel)
                        .addGap(40, 40, 40))))
        );

        buttonLihatPendaftar.setText("LIHAT PENDAFTAR");
        buttonLihatPendaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLihatPendaftarActionPerformed(evt);
            }
        });

        buttonDaftar.setBackground(new java.awt.Color(255, 153, 0));
        buttonDaftar.setText("DAFTAR SEKARANG");
        buttonDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDaftarActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jRadioButton1.setEnabled(false);
        jRadioButton1.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(LabelJenisKelamin))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(buttonLihatPendaftar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(buttonDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fieldNamaDepan, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LabelNamaDepan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LabelNamaDepan1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fIeldNamaBelakang, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(RadioButtonWanita)
                                .addComponent(jRadioButton1)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(RadioButtonPria))
                        .addComponent(checkBoxSetujuKetentuan)
                        .addComponent(ComboBoxJenisPengemudi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNamaDepan, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNamaDepan1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldNamaDepan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fIeldNamaBelakang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelJenisKelamin)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RadioButtonWanita)
                    .addComponent(RadioButtonPria))
                .addGap(18, 18, 18)
                .addComponent(ComboBoxJenisPengemudi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checkBoxSetujuKetentuan)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLihatPendaftar)
                    .addComponent(buttonDaftar))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldNamaDepanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNamaDepanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNamaDepanActionPerformed

    private void fIeldNamaBelakangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fIeldNamaBelakangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fIeldNamaBelakangActionPerformed

    private void RadioButtonPriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonPriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButtonPriaActionPerformed

    private void checkBoxSetujuKetentuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxSetujuKetentuanActionPerformed

    }//GEN-LAST:event_checkBoxSetujuKetentuanActionPerformed

    private void CaptchaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaptchaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CaptchaFieldActionPerformed

    private void buttonLihatPendaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLihatPendaftarActionPerformed
        dialog.setVisible(true);
    }//GEN-LAST:event_buttonLihatPendaftarActionPerformed

    private void buttonDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDaftarActionPerformed
        String namaDepan = fieldNamaDepan.getText();
        String namaBelakang = fIeldNamaBelakang.getText();
        String jenisKelamin = RadioButtonPria.isSelected() ? "pria" : "wanita";
        String jenisPengemudi = String.valueOf(ComboBoxJenisPengemudi.getSelectedItem());

        if (!checkBoxSetujuKetentuan.isSelected()) { // check if user not setuju peraturan
            infoMessage("Harap ceklis \"Saya menyetujui peraturan yang berlaku\"!", "Belum setuju peraturan");
            System.out.println("Belum setuju peraturan");
        } else if (verifyCaptcha(CaptchaField.getText())) { // check if user not entering the right captcha
            infoMessage("Captcha yang anda masukkan salah!", "Captcha salah");
            System.out.println("Captcha salah");
            setCaptcha(generateCaptcha());
            CaptchaField.setText(null);
            CaptchaField.requestFocus();
        } else if (!(namaDepan.length() > 0 // Check if there's no bad stuff left
                && namaBelakang.length() > 0
                && (RadioButtonPria.isSelected() || RadioButtonWanita.isSelected())
                && (jenisPengemudi.equalsIgnoreCase("motor") || jenisPengemudi.equalsIgnoreCase("mobil")))) {
            infoMessage("Harap isi semua data!", "Masukan kurang");
            System.out.println("Masukan kurang");
            System.out.printf("FIeldNamaDepan: %s\n", namaDepan.length() > 0);
            System.out.printf("FIeldNamaBelakang: %s\n", namaBelakang.length() > 0);
            System.out.printf("RadioButton: %b\n", RadioButtonPria.isSelected() || RadioButtonWanita.isSelected());
            System.out.printf("ComboBoxJenisPengemudi: %s\n", jenisPengemudi.length() > 0);
        } else { // You're good to go👍
//            boolean insert = true;
            boolean insert = conn.insert(namaDepan, namaBelakang, jenisKelamin, jenisPengemudi);
            if (insert) {
                refreshPage();
                infoMessage("Data berhasil ditambahkan!", "Berhasil");
                System.out.printf("Data berhasil ditambahkan!%s\n", "");
                System.out.printf("FIeldNamaDepan: %s\n", namaDepan);
                System.out.printf("FIeldNamaBelakang: %s\n", namaBelakang);
                System.out.printf("RadioButton: %b\n", RadioButtonPria.isSelected() || RadioButtonWanita.isSelected());
                System.out.printf("ComboBoxJenisPengemudi: %s\n", jenisPengemudi);
            } else {
                warningMessage("Data gagal ditambahkan, harap periksa kembali data yang dimasukkan.", "Gagal");
            }
        }
    }//GEN-LAST:event_buttonDaftarActionPerformed

    private void ComboBoxJenisPengemudiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxJenisPengemudiActionPerformed
        // Delete the first item in combobox so it looks nice
        if (ComboBoxJenisPengemudi.getItemCount() > 2) {
            ComboBoxJenisPengemudi.removeItemAt(0);
        }
    }//GEN-LAST:event_ComboBoxJenisPengemudiActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CaptchaField;
    private javax.swing.JLabel CaptchaLabel;
    private javax.swing.JComboBox<String> ComboBoxJenisPengemudi;
    private javax.swing.JLabel LabelJenisKelamin;
    private javax.swing.JLabel LabelNamaDepan;
    private javax.swing.JLabel LabelNamaDepan1;
    private javax.swing.JRadioButton RadioButtonPria;
    private javax.swing.JRadioButton RadioButtonWanita;
    private javax.swing.JButton buttonDaftar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonLihatPendaftar;
    private javax.swing.JCheckBox checkBoxSetujuKetentuan;
    private javax.swing.JTextField fIeldNamaBelakang;
    private javax.swing.JTextField fieldNamaDepan;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    // End of variables declaration//GEN-END:variables
}
