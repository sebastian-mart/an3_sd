import ejb.StudentEntity;
import ejb.StudentsDBImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
public class FetchStudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Driverul SQLite nu a fost găsit!", e);
        }
        StringBuilder responseText = new StringBuilder();
        try{
            responseText.append("<h2>Lista studenti</h2>");
            responseText.append("<table border='1'><thead><tr><th>ID</th><th>Nume</th><th>Prenume</th><th>Varsta</th></thead>");
            responseText.append("<tbody>");
// preluare date studenti din baza de date
            StudentsDBImpl db = new StudentsDBImpl();
            List<StudentEntity> results = db.getAllStudents();
            for (StudentEntity student : results) {
// se creeaza cate un rand de tabel HTML pentru fiecare student gasit
                responseText.append("<tr><td>" + student.getId() +
                        "</td><td>" +
                        student.getNume() + "</td><td>" +
                        student.getPrenume() +
                        "</td><td>" + student.getVarsta() + "</td><td>"+
                        "<form action=\"delete-student\" method=\"get\">" +
                        "<input type=\"hidden\" name=\"id\" value=\"" + student.getId() + "\">" +
                        "<button type=\"submit\">Delete</button>" +
                        "</form>" +
                        "</td>" +

                        "<td>" +
                        "<form action=\"update-student\" method=\"get\">" +
                        "<input type=\"hidden\" name=\"id\" value=\"" + student.getId() + "\">" +
                        "<input type=\"hidden\" name=\"nume\" value=\"" + student.getNume() + "\">" +
                        "<input type=\"hidden\" name=\"prenume\" value=\"" + student.getPrenume() + "\">" +
                        "<input type=\"hidden\" name=\"varsta\" value=\"" + student.getVarsta() + "\">" +
                        "<button type=\"submit\">Actualizeaza</button>" +
                        "</form>" +
                        "</td>" +
                        "</tr>");
            }
            responseText.append("</tbody></table><br /><br /><a href='./'>Inapoi la meniul principal</a>");
        } catch (Exception e) {
            responseText= new StringBuilder("<h1>Eroare fetch studenti!</h1><a href='./'>Ok</a>" +
                    e.getStackTrace());
        }finally {
            response.setContentType("text/html");
            response.getWriter().print(responseText.toString());
        }
    }
}