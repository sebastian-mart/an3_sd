import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Year;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
// se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        String oras = request.getParameter("oras");
        String adresa = request.getParameter("adresa");
        /*
        procesarea datelor - calcularea anului nasterii
        */
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");//conexiune db
            //creare comanda de insert in tabela
            String sql = "INSERT INTO student(nume, prenume, varsta,oras,adresa) VALUES(?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,nume);
            ps.setString(2,prenume);
            ps.setInt(3,varsta);
            ps.setString(4,oras);
            ps.setString(5,adresa);
            ps.executeUpdate();

        } catch (SQLException s) {//in cazul in care apare exceptie la tabela bd se scrie in xml
            // initializare serializator Jackson
            XmlMapper mapper = new XmlMapper();

            // creare bean si populare cu date
            StudentBean bean = new StudentBean();
            bean.setNume(nume);
            bean.setPrenume(prenume);
            bean.setVarsta(varsta);
            bean.setAdresa(adresa);
            bean.setOras(oras);

            String directorBaza = "/home/sebastian/facultate/an3/sem2/sd/labs/lab1/Popescu Ion";
            File folderStudent = new File(directorBaza);
            if (!folderStudent.exists()) {
                folderStudent.mkdirs();
            }
            File fisierXml = new File(folderStudent, "student.xml");

            // serializare bean sub forma de string XML
            mapper.writeValue(fisierXml, bean);
        } catch (ClassNotFoundException e) {
            response.sendError(404, e.toString());
            return;
        } finally {
            response.sendRedirect("index.jsp");
//            response.setContentType("text/html");
//            PrintWriter out =response.getWriter();
//            out.println("<html>");
//            out.println("<head><title>Continut inregistrat</title></head>");
//            out.println("<body>");
//            out.println("<h1>Continutul a fost inregistrat</h1>");
//            // Buton care trimite la index.jsp
//            out.println("<form action='index.jsp' method='get'>");
//            out.println("<button type='submit'>Intoarcere pagina principala</button>");
//            out.println("</form>");
//            out.println("</body>");
//            out.println("</html>");
        }


// se trimit datele primite si anul nasterii catre o alta pagina JSP pentru afisare
//        request.setAttribute("nume", nume);
//        request.setAttribute("prenume", prenume);
//        request.setAttribute("varsta", varsta);
//        request.setAttribute("anNastere", anNastere);
//        request.setAttribute("oras", oras);
//        request.setAttribute("adresa", adresa);


//        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}