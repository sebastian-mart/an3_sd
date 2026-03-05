package interfaces;

import ejb.StudentEntity;


import java.util.List;

public interface StudentsDBRemote {

    public List<StudentEntity> getAllStudents();

    public Boolean updateStudent(int id, String nume, String prenume, int varsta);

    public Boolean deleteStudent(int id);

    public Boolean addStudent(String nume, String prenume, int varsta);

}
