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
            System.out.println("\n\nStudents with last name of 'Doe':");
            displayTheStudents(students);

            // query students: lastName = 'Doe' OR firstName = 'Daffy'
            students = session
                    .createQuery("from Student s where s.lastName='Doe' or s.firstName='Daffy'", Student.class)
                    .getResultList();

            // display search result
            System.out.println("\n\nStudents with last name of 'Doe' OR first name of 'Daffy':");
            displayTheStudents(students);

            // search for students whose email ends with "duck.ru"
            students = session
                    .createQuery("from Student s where s.email like '%duck.ru'", Student.class)
                    .getResultList();

            System.out.println("\n\nStudents whose email ends with 'duck.ru': ");
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
