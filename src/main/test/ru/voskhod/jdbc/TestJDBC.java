package ru.voskhod.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC  {

    @Test
    public void testJDBCConnection() {

        String JDBCUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=Europe/Moscow";
        String user = "hbstudent";
        String password = "hbstudent";

        try {
            System.out.println("Connecting to database: " + JDBCUrl);

            Connection connection =
                    DriverManager.getConnection(JDBCUrl, user, password);

            System.out.println("Connection successful!");
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @Test
    public void testJDBCConnectionWithBadPassword() {

        String JDBCUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=Europe/Moscow";
        String user = "hbstudent";
        String password = "hbstudentzzz";

        try {
            System.out.println("Connecting to database: " + JDBCUrl);

            Connection connection =
                    DriverManager.getConnection(JDBCUrl, user, password);

            System.out.println("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
