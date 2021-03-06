package ru.voskhod.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.voskhod.hibernate.demo.entity.Student;

import java.text.ParseException;
import java.util.Date;

public class CreateStudentDemo {
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
                    new Student("Alex", "Vasilkov", DateUtils.parseDate("21/06/1995"),"y915@yandex.ru");

            // start hibernate transaction
            session.beginTransaction();

            // save Student object
            System.out.println("Saving the student...");
            System.out.println(student);
            session.save(student);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
