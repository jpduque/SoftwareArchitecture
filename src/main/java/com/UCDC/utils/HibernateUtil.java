package com.UCDC.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * The session factory allows the database connection factory to be instantiated, and set the connection data according
     * to the properties
     * @return
     */
    private static SessionFactory buildSessionFactory() {
        try {
            Properties dbConnectionProperties = new Properties();
            try {
                    dbConnectionProperties.setProperty("hibernate.connection.url", Props.getProperty("hibernate.connection.url")+"?useTimezone=true&serverTimezone=UTC");
                    dbConnectionProperties.setProperty("hibernate.connection.username", Props.getProperty("hibernate.connection.username"));
                    dbConnectionProperties.setProperty("hibernate.connection.password", Props.getProperty("hibernate.connection.password"));
                    dbConnectionProperties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Configuration().configure().addProperties(dbConnectionProperties).buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}