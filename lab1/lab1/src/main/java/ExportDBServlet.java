import beans.StudentBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExportDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentBean> students = new ArrayList<>();
        try {
             Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            while (rs.next()) {
                StudentBean s = new StudentBean();
                s.setNume(rs.getString("nume"));
                s.setPrenume(rs.getString("prenume"));
                s.setVarsta(rs.getInt("varsta"));
                s.setOras(rs.getString("oras"));
                s.setAdresa(rs.getString("adresa"));
                s.setId(rs.getInt("id"));
                students.add(s);
            }

        } catch (SQLException e) {
            response.sendError(500, e.toString());
            return;
        }

        // 2. Configurare răspuns HTTP pentru descărcare fișier
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // Această linie forțează browserul să descarce fișierul, nu să-l afișeze
        response.setHeader("Content-Disposition", "attachment; filename=studenti_export.json");

        // 3. Conversie în JSON folosind Jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // Face JSON-ul ușor de citit (cu spații și indentare)
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Trimitem lista direct către stream-ul de ieșire al răspunsului
        mapper.writeValue(response.getWriter(), students);
        response.sendRedirect("index.jsp");
    }
}