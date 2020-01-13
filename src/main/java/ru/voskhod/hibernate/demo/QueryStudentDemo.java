package ru.voskhod.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.voskhod.hibernate.demo.entity.Student;

import java.util.List;

public class QueryStudentDemo {
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

            // start hibernate transaction
            session.beginTransaction();

            // query students
            List<Student> students = session
                    .createQuery("from Student", Student.class)
                    .getResultList();

            // display the students
            displayTheStudents(students);

            // query students: lastName = 'Doe'
            students = session
                    .createQuery("from Student s where s.lastName='Doe'", Student.class)
                    .getResultList();

            // display search result
            System.out.println("\n\nStudents with last name 'Doe':");
            displayTheStudents(students);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }
    }

    private static void displayTheStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
