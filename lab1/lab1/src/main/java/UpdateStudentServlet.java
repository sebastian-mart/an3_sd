import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.Year;

public class UpdateStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_=request.getParameter("id");
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            String sql = "SELECT * FROM student"+" where id='"+id_+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //preia valori din db
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                int varsta = rs.getInt("varsta");
                String oras= rs.getString("oras");
                String adresa= rs.getString("adresa");
                int id = rs.getInt("id");
                //creeaza obiect studentbean pt a putea fi trimis
                request.setAttribute("nume", nume);
                request.setAttribute("prenume", prenume);
                request.setAttribute("varsta", varsta);
                request.setAttribute("adresa", adresa);
                request.setAttribute("oras",oras);
                request.setAttribute("id",id);
            }

            request.getRequestDispatcher("./update-student.jsp").forward(request,response);
        } catch (SQLException | ClassNotFoundException e) {
            // deserializare student din fisierul XML de pe disc
            File file = new File("/home/sebastian/facultate/an3/sem2/sd/labs/lab1/Popescu Ion/student.xml");
            // se returneaza un raspuns HTTP de tip 404 in cazul in care nu se gaseste fisierul cu date
            if (!file.exists()) {
                response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
                return;
            }
            XmlMapper xmlMapper = new XmlMapper();
            StudentBean bean = xmlMapper.readValue(file, StudentBean.class);
            request.setAttribute("nume", bean.getNume());
            request.setAttribute("prenume", bean.getPrenume());
            request.setAttribute("varsta", bean.getVarsta());
            request.setAttribute("adresa", bean.getAdresa());
            request.setAttribute("oras",bean.getOras());
            request.setAttribute("id",bean.getId());
            // redirectionare date catre pagina de afisare a informatiilor studentului
            request.getRequestDispatcher("./update-student.jsp").forward(request, response);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        String oras = request.getParameter("oras");
        String adresa = request.getParameter("adresa");
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            String sql = "UPDATE student SET nume=?, prenume=?, varsta=?, oras=?, adresa=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nume);
            stmt.setString(2, prenume);
            stmt.setInt(3, varsta);
            stmt.setString(4, oras);
            stmt.setString(5, adresa);
            stmt.setInt(6, id);

            int rowsUpdated = stmt.executeUpdate();

            stmt.close();
            conn.close();
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        }
        catch (SQLException | ClassNotFoundException s)
        {
            int anCurent = Year.now().getValue();
            int anNastere = anCurent - varsta;
// se trimit datele primite si anul nasterii catre o alta pagina JSP pentru afisare
            request.setAttribute("nume", nume);
            request.setAttribute("prenume", prenume);
            request.setAttribute("varsta", varsta);
            request.setAttribute("anNastere", anNastere);
            request.setAttribute("oras", oras);


            // initializare serializator Jackson
            XmlMapper mapper = new XmlMapper();

            // creare bean si populare cu date
            StudentBean bean = new StudentBean();
            bean.setNume(nume);
            bean.setPrenume(prenume);
            bean.setVarsta(varsta);
            bean.setOras(oras);
            bean.setAdresa(adresa);


            String directorBaza = "/home/sebastian/facultate/an3/sem2/sd/labs/lab1/Popescu Ion";
            File folderStudent = new File(directorBaza);
            if (!folderStudent.exists()) {
                folderStudent.mkdirs();
            }
            File fisierXml = new File(folderStudent, "student.xml");

            // serializare bean sub forma de string XML
            mapper.writeValue(fisierXml, bean);
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(500, "Eroare la update student: " + e.getMessage());
        }

    }
}
