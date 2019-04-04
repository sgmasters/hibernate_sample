package by.practice.hibernate;

import by.practice.hibernate.domain.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Student> studentsList = null;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Student student = new Student();
            student.setId(1);
            student.setAge(23);
            student.setName("Vasja");
            student.setSurname("Pupkin");
            student.setGrade(3);
            student.setGrand(200);

            session.save(student);

            student = new Student();
            student.setId(2);
            student.setAge(21);
            student.setName("Vova");
            student.setSurname("Sumpkin");
            student.setGrade(1);
            student.setGrand(100);

            session.save(student);

            student = new Student();
            student.setId(3);
            student.setAge(22);
            student.setName("Chu");
            student.setSurname("Kin");
            student.setGrade(2);
            student.setGrand(15000);

            session.save(student);
            session.getTransaction().commit();

        } catch (Throwable t) {
            t.printStackTrace();
        }

        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM Student");
            studentsList = (List<Student>) query.list();
            session.getTransaction().commit();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        if (studentsList != null && !studentsList.isEmpty()) {
            for (Student student : studentsList) {
                System.out.println(student);
            }
        }
    }
}