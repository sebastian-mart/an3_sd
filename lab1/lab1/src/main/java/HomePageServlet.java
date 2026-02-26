import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class HomePageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            String sql = "CREATE TABLE IF NOT EXISTS student (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nume TEXT," +
                    "prenume TEXT," +
                    "varsta INTEGER,"+
                    "oras TEXT,"+
                    "adresa TEXT)";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            String mesaj="Conectare cu succes la baza de date.";
            request.setAttribute("mesaj",mesaj);
        }
        catch (SQLException s)
        {
            String mesaj="Conectare esuata la baza de date. Reincarcati pagina pentru a reincerca";
            request.setAttribute("mesaj",mesaj);
        } catch (ClassNotFoundException e) {
            response.sendError(404, e.toString());
            return;
        } finally {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
