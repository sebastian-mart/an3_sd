import ejb.StudentEntity;
import ejb.StudentsDBImpl;
import interfaces.StudentsDBRemote;

import javax.ejb.EJB;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        StringBuilder responseText = new StringBuilder();
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String id = request.getParameter("id");
        String varsta = request.getParameter("varsta");
        responseText.append("<h2>Actualizare Student/i</h2>");
        responseText.append("<form action=\"./update-student\" method=\"post\">\n" +
                "            Nume:\n" +
                "            <input type=\"text\" name=\"nume\" value=\""+nume+"\" />\n" +
                "            <br />\n" +
                "\n" +
                "            Prenume:\n" +
                "            <input type=\"text\" name=\"prenume\" value=\""+prenume+"\" />\n" +
                "            <br />"+
                "            Varsta:\n" +
                "            <input type=\"number\" name=\"varsta\" value="+varsta+" />\n" +
                "            <br />"+
                "            <input type=\"hidden\" name=\"id\" value="+id+" />\n" +
                "            <br />"+

                "<button type=\"submit\" name=\"submit\">Trimite</button>");

        responseText.append("</tbody></table><br /><br /><a href='./'>Inapoi la meniul principal</a>");
// trimitere raspuns la client
        response.setContentType("text/html");
        response.getWriter().print(responseText.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Driverul SQLite nu a fost găsit!", e);
        }
        StringBuilder responseText = new StringBuilder();
        String nume = req.getParameter("nume");
        String prenume = req.getParameter("prenume");
        String id = req.getParameter("id");
        String varsta = req.getParameter("varsta");
        if(nume.matches("^[a-zA-z-]+$") || prenume.matches("^[a-zA-z-]+$"))
        {
            StudentsDBImpl db=new StudentsDBImpl();
            if(db.updateStudent(Integer.parseInt(id),nume,prenume,Integer.parseInt(varsta))){
                responseText.append("<h2>A fost actualizat 1 student</h2><a href='./'>Ok</a>");
            }else {
                responseText.append("<h2>A fost actualizat 1 student</h2><a href='./'>Ok</a>");
            }
        }else{
            responseText.append("<h1>Entry invalid!</h1><a href='./delete-student'>Ok</a>");
        }
        resp.setContentType("text/html");
        resp.getWriter().print(responseText.toString());
    }
}