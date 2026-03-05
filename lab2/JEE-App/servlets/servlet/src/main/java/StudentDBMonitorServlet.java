import ejb.StudentEntity;
import ejb.StudentsDBImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServlet;
import java.util.List;

public class StudentDBMonitorServlet extends HttpServlet implements Runnable {
    private volatile Boolean running = true;
    public StudentDBMonitorServlet(){
    }

    @Override
    public void init() {
        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();
        while(running){}
    }


    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (running){
            try {
                Thread.sleep(4000);
                check();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
    private void check(){
        String nMin = (String) getServletContext().getAttribute("numeA");
        String nMax = (String) getServletContext().getAttribute("numeB");
        String vMin = (String) getServletContext().getAttribute("varstaA");
        String vMax = (String) getServletContext().getAttribute("varstaB");

        if (nMin == null || vMin == null) return; // Dacă nu s-au setat parametri, nu facem nimic
        Boolean isDangerous = false;

        StringBuilder respon=new StringBuilder("");


        EntityManagerFactory factory=Persistence.createEntityManagerFactory("bazaDeDateSQLite");

        EntityManager em = factory.createEntityManager();
        TypedQuery<StudentEntity> query = em.createQuery("select student from StudentEntity student", StudentEntity.class);
        List<StudentEntity> results= query.getResultList();
//        db = new StudentsDBImpl();
//        List<StudentEntity> results = db.getAllStudents();
//        db = null;
        for (StudentEntity student : results) {
            int lungimeNume = student.getNume().length();
            boolean numeInvalid = (lungimeNume < Integer.parseInt(nMin) || lungimeNume > Integer.parseInt(nMax));
            if(student.getVarsta()<Integer.parseInt(vMin)||student.getVarsta()>Integer.parseInt(vMax)|| numeInvalid)
            {
                isDangerous=true;
                respon=new StringBuilder("<h2>Atentie!</h2>"+"<br/>"+"Element neasteptat in bd:<br/>ID:"+student.getId()
                        +"Camp periculos->varsta:"+student.getVarsta()+"<br/><br/>"+
                        "<form action=\"delete-student\" method=\"get\">" +
                        "<input type=\"hidden\" name=\"id\" value=\"" + student.getId() + "\">" +
                        "<button type=\"submit\">Delete</button>" +
                        "</form>" +
                        "<a href='./'>Inapoi la meniul principal</a>");
            }
        }
        getServletContext().setAttribute("codHtmlAlerte", respon.toString());


    }
    @Override
    public void destroy() {
        running = false;
    }
}
