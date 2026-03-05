package database;

import beans.StudentBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManagement {
    private String dbPath = "jdbc:sqlite:students.db";

    public DBManagement() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbPath);
    }


    public List<StudentBean> getStudents(String option, String search) throws SQLException {
        List<StudentBean> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        if (option != null && !option.isEmpty() && search != null && !search.isEmpty()) {
            sql += " WHERE lower(" + option + ") = lower(?)";
        }

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (sql.contains("?")) {
                pstmt.setString(1, search);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        }
        return students;
    }

    public StudentBean getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
        }
        return null;
    }

    public void updateStudent(StudentBean s) throws SQLException {
        String sql = "UPDATE student SET nume=?, prenume=?, varsta=?, oras=?, adresa=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s.getNume());
            pstmt.setString(2, s.getPrenume());
            pstmt.setInt(3, s.getVarsta());
            pstmt.setString(4, s.getOras());
            pstmt.setString(5, s.getAdresa());
            pstmt.setInt(6, s.getId());
            pstmt.executeUpdate();
        }
    }

    private StudentBean mapResultSetToStudent(ResultSet rs) throws SQLException {
        StudentBean s = new StudentBean();
        s.setId(rs.getInt("id"));
        s.setNume(rs.getString("nume"));
        s.setPrenume(rs.getString("prenume"));
        s.setVarsta(rs.getInt("varsta"));
        s.setOras(rs.getString("oras"));
        s.setAdresa(rs.getString("adresa"));
        return s;
    }
}