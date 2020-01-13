package ru.voskhod.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.voskhod.hibernate.demo.entity.Student;

public class ReadStudentDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session

        // create and save Student object to MySQL database
        try (factory) {
            Session session = factory.getCurrentSession();
            // create a Student object
            System.out.println("Creating new Student object");
            Student student =
                    new Student("Daffy", "Duck", "daffy@duck.ru");

            // start hibernate transaction
            session.beginTransaction();

            // save Student object
            System.out.println("Saving the student...");
            System.out.println(student);
            session.save(student);

            // commit transaction
            session.getTransaction().commit();

            // new code

            // find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + student.getId());

            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + student.getId());

            Student readStudent = session.get(Student.class, student.getId());
            System.out.println("Get complete: " + readStudent);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }
    }
}
