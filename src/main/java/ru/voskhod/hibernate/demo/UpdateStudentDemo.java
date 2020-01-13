package ru.voskhod.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.voskhod.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
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

            System.out.println("\n Getting student with id: " + studentId);

            Student student = session.get(Student.class, studentId);

            System.out.println("Get complete: " + student);

            System.out.println("Updating...");

            student.setFirstName("Scooby");

            session.getTransaction().commit();

            // NEW CODE

            session = factory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("Update email for all students");

            session.createQuery("update Student set email='foo@bar.com'")
                    .executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");

        }
    }
}
