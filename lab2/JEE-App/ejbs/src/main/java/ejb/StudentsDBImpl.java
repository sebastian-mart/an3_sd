package ejb;

import interfaces.StatelessSessionBeanRemote;
import interfaces.StudentsDBRemote;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.*;
import javax.servlet.ServletException;

public class StudentsDBImpl implements StudentsDBRemote, Serializable {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
    public StudentsDBImpl(){
        System.out.println("[Glassfish] S-a instanţiat un stateless session bean: " +
        StudentsDBImpl.class.getName());
    }

    @Override
    public Boolean addStudent(String nume, String prenume, int varsta) {
        EntityManager em = factory.createEntityManager();
        try {
            // creare entitate JPA si populare cu datele primite din formular
            StudentEntity student = new StudentEntity();
            student.setNume(nume);
            student.setPrenume(prenume);
            student.setVarsta(varsta);
// adaugare entitate in baza de date (operatiune de persistenta)
// se face intr-o tranzactie
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(student);
            transaction.commit();
            return true;
        }catch (Exception e){
            throw new RuntimeException("Eroare introducere student in db "+e.getStackTrace());
        }
        finally {
            em.close();
        }
    }

    public List<StudentEntity> getAllStudents(){
        EntityManager em = factory.createEntityManager();
        try {
            TypedQuery<StudentEntity> query = em.createQuery("select student from StudentEntity student", StudentEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            em.close();
        }

    }

    public Boolean updateStudent(int id, String nume, String prenume, int varsta){
        EntityManager em = factory.createEntityManager();
        try {
            String query_string;
            query_string= "UPDATE StudentEntity s SET s.varsta='"+varsta+
                    "' ,s.nume='"+nume+"', s.prenume='"+prenume+"'WHERE s.id='"+id+"'";
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            TypedQuery<StudentEntity> query = em.createQuery(query_string, StudentEntity.class);
            int randuriSterse = query.executeUpdate();
            transaction.commit();
            return randuriSterse!=0;
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally {
            em.close();
        }
    }

    public Boolean deleteStudent(int id){
        EntityManager em = factory.createEntityManager();
        try{
            String query_string;
            query_string = "DELETE FROM StudentEntity s WHERE s.id=" + id;
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            TypedQuery<StudentEntity> query = em.createQuery(query_string, StudentEntity.class);
            int randuriSterse = query.executeUpdate();
            transaction.commit();
            return randuriSterse != 0;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            em.close();
        }

    }
}
