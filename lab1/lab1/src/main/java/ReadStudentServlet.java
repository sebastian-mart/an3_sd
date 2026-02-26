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
import java.util.ArrayList;
import java.util.List;

public class ReadStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int anCurent = Year.now().getValue();
        request.setAttribute("anCurent",anCurent);
        String option=request.getParameter("option");
        String search=request.getParameter("item_cautat");
        String append="";
        if("nume".equals(option))
            append=" WHERE lower(nume)=lower('"+search+"')";
        else if ("prenume".equals(option))
            append=" WHERE lower(prenume)=lower('"+search+"')";
        else if ("oras".equals(option))
            append=" WHERE lower(oras)=lower'"+search+"')";
        else if ("varsta".equals(option))
            append=" WHERE varsta='"+search+"'";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            String sql = "SELECT * FROM student"+append;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<StudentBean> students = new ArrayList<>();
            while(rs.next()){
                //preia valori din db
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                int varsta = rs.getInt("varsta");
                String oras= rs.getString("oras");
                String adresa= rs.getString("adresa");
                int id = rs.getInt("id");
                //creeaza obiect studentbean pt a putea fi trimis
                StudentBean student = new StudentBean();
                student.setOras(oras);
                student.setAdresa(adresa);
                student.setNume(nume);
                student.setPrenume(prenume);
                student.setVarsta(varsta);
                student.setId(id);
                students.add(student);
            }
            request.setAttribute("students",students);
            request.getRequestDispatcher("info-student.jsp").forward(request,response);
        }catch (SQLException s){
            response.sendError(404, s.toString());
            return;
//            // deserializare student din fisierul XML de pe disc
//            File file = new File("/home/sebastian/facultate/an3/sem2/sd/labs/lab1/Popescu Ion/student.xml");
//            // se returneaza un raspuns HTTP de tip 404 in cazul in care nu se gaseste fisierul cu date
//            if (!file.exists()) {
//                response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
//                return;
//            }
//            XmlMapper xmlMapper = new XmlMapper();
//            StudentBean bean = xmlMapper.readValue(file, StudentBean.class);
////            request.setAttribute("nume", bean.getNume());
////            request.setAttribute("prenume", bean.getPrenume());
////            request.setAttribute("varsta", bean.getVarsta());
//            List<StudentBean> students = new ArrayList<>();
//            students.add(bean);
//            request.setAttribute("students",students);
//            // redirectionare date catre pagina de afisare a informatiilor studentului
//            request.getRequestDispatcher("./info-student.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            response.sendError(404, e.toString());
            return;
        }


    }
}