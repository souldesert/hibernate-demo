package ru.voskhod.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.voskhod.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session

        // create and save Student object to MySQL database
        try (factory) {
            int studentId = 1;

            Session session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + studentId);

            Student student = session.get(Student.class, studentId);

            // delete the student
//            System.out.println("Deleting student: " + student);
//            session.delete(student);

            // delete student with id of 2
            session.createQuery("delete from Student where id=2")
                    .executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");

        }
    }
}
