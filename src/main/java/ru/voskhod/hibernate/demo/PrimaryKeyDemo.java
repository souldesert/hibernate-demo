package ru.voskhod.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.voskhod.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {
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
            // create three student objects
            System.out.println("Creating new Student object");
            Student studentOne = new Student("John", "Doe", "john@voskhod.ru");
            Student studentTwo = new Student("Mary", "Public", "mary@voskhod.ru");
            Student studentThree = new Student("Bonita", "Applebum", "bonita@voskhod.ru");

            // start hibernate transaction
            session.beginTransaction();

            // save Student object
            System.out.println("Saving the student...");
            session.save(studentOne);
            session.save(studentTwo);
            session.save(studentThree);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }
    }
}
