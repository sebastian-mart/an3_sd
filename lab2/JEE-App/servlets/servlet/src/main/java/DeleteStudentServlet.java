import ejb.StudentEntity;
import ejb.StudentsDBImpl;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Driverul SQLite nu a fost găsit!", e);
        }
        StringBuilder responseText = new StringBuilder();
        try {

            String id = request.getParameter("id");
            StudentsDBImpl db=new StudentsDBImpl();

            if(db.deleteStudent(Integer.parseInt(id))){
                responseText.append("<h2>A fost sters 1 student</h2><a href='./'>Ok</a>");
            }else {
                responseText.append("<h2>Au fost stersi 0 studenti</h2><a href='./'>Ok</a>");
            }
        } catch (Exception e) {
            responseText.append("<h1>Entry invalid!</h1><a href='./delete-student'>Ok</a>" +
                    e.getStackTrace());
        }
        finally {
            response.setContentType("text/html");
            response.getWriter().print(responseText.toString());
        }

    }


}