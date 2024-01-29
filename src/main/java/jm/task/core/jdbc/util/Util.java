package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/task_1_1_2";
    private static final String password = "0000";
    private static final String username = "haji";
    public static Connection getJdbcConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public static Session getHibernateSession() {
        Properties prop = new Properties();
        prop.setProperty("connection.driver_class", driver);
        prop.setProperty("hibernate.connection.url", url);
        prop.setProperty("hibernate.connection.username", username);
        prop.setProperty("hibernate.connection.password", password);
        prop.setProperty("hibernate.current_session_context_class", "thread");
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");


        Configuration cfg = new Configuration()
                .addAnnotatedClass(User.class)
                .setProperties(prop);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties()).build();

        SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
        return sf.getCurrentSession();
    }
}