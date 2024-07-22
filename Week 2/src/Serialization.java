import java.io.*;
import java.io.Serializable;

public class Student implements Serializable{
    private String name;
    private String mssv;

    public Student (String name, String mssv){
        this.name = name;
        this.mssv = mssv;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMssv(String mssv){
        this.mssv = mssv;
    }

    public String getName(){
        return name;
    }

    public String getMssv(){
        return mssv;
    }
}

public class Serialization{
    public static void main(String[] args){
        try (FileOutputStream file = new FileOutputStream("Student");
        ObjectOutputStream obj = new ObjectOutputStream(file)){
            Student student = new Student("Long", "20215417");
            obj.writeObject(Student);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try (FileInputStream filei = new FileInputStream("Student");
        ObjectInputStream obji = new ObjectInputStream(filei)){
            Student student = (Student) obji.readObject();
            System.out.println("Ten: " student.getName() + ", mã số sinh viên: " + student.getMssv());
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}