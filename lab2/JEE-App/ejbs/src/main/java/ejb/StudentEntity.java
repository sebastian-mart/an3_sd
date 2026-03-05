package ejb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity//marchează faptul că această clasă este o entitate JPA, adnotarea
public class StudentEntity {
    @Id//indică după care câmp vor fi identificate înregistrările din tabela rezultată în baza de date (cheia primară)
    @GeneratedValue(strategy = GenerationType.TABLE) //marchează faptul
//    că acel câmp adnotat (în acest caz, câmpul id) conţine o valoare generată automat la inserarea
//    unei înregistrări - nu trebuie dat explicit de utilizator


//Câmpurile încapsulate trebuie să fie tipuri de date simple (primitive sau nu), şi să fie
//serializabile.
    private int id;
    private String nume;
    private String prenume;
    private int varsta;

    public StudentEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
}