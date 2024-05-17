package praktikum10_1402023044;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Rizal Anditama
 */
public class Connection {

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost/praktikum10_1402023044";
    String user = "root";
    String password = "";

    java.sql.Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;

    boolean respons;

    public Connection() {
        try {
            Class.forName(jdbcDriver);
            System.out.println("Driver load...");
        } catch (ClassNotFoundException ex) {
            errorMessage("Driver tidak ditemukan", "Error");
            System.out.println("Driver tidak ditemukan");
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Berhasil terkoneksi");
        } catch (SQLException ex) {
            errorMessage("Koneksi tidak berhasil, Periksa confiq mysql!", "Error");
            System.out.println("Koneksi tidak berhasil, Periksa confiq mysql!");
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel buildTableModel(ResultSet rs) {
        Vector<String> columnNames = new Vector<String>();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        try {
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return new DefaultTableModel(data, columnNames);

    }

    public boolean insert(String namaDepan, String namaBelakang, String jenisKelamin, String jenisPengemudi) {
        String query = "INSERT INTO pengemudi (nama_depan, nama_belakang, jenis_kelamin, jenis_pengemudi) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, namaDepan);
            ps.setString(2, namaBelakang);
            ps.setString(3, jenisKelamin);
            ps.setString(4, jenisPengemudi);
            if (ps.executeUpdate() > 0) {
                respons = true;
                System.out.println("Berhasil memasukan data.");
            } else {
                respons = false;
                System.out.println("Gagal memasukan data");
            }
        } catch (SQLException ex) {
            respons = false;
            System.out.println("Gagal memasukan data");
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respons;
    }

    public DefaultTableModel getAll() {
        System.out.println("test1");
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nama Depan", "Nama Belakang", "Jenis Kelamin", "Jenis Pengemudi"}, 0);
        try ( Statement st = con.createStatement();  ResultSet rs = st.executeQuery("SELECT * FROM pengemudi")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String namaDepan = rs.getString("nama_depan");
                String namaBelakang = rs.getString("nama_belakang");
                String jenisKelamin = rs.getString("jenis_kelamin");
                String jenisPengemudi = rs.getString("jenis_pengemudi");
                System.out.printf("%d\n%s\n%s\n%s\n%s\n\n", id,namaDepan, namaBelakang, jenisKelamin, jenisPengemudi);
                System.err.println();
                model.addRow(new Object[]{id, namaDepan, namaBelakang, jenisKelamin, jenisPengemudi});
            }
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void errorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

}
