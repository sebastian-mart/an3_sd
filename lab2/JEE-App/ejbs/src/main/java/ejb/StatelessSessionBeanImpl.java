package ejb;

import interfaces.StatelessSessionBeanRemote;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//REMEMBER nu folosiţi tipuri primitive de date în enterprise beans care vor fi apelate
//prin RMI (bean-uri nelocale)! Tipurile primitive de date (int, char, double etc.) nu sunt
//serializabile. Folosiţi, în schimb, clasele lor corespondente Java: Integer, Char, Double
//etc.

public class StatelessSessionBeanImpl implements StatelessSessionBeanRemote, Serializable {
    public StatelessSessionBeanImpl() {
        System.out.println("[Glassfish] S-a instanţiat un stateless session bean:" +
        StatelessSessionBeanImpl.class.getName());
    }

    public String getCurrentTime() {
        System.out.println("[Glassfish] S-a apelat metoda getCurrentTime()");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }

    public Integer addNumbers(Integer a, Integer b) {
        System.out.println("[Glassfish] S-a apelat metoda addNumbers("+ a + ", " + b + ")");
        return a + b;
    }
}
