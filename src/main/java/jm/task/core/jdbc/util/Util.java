package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public Util() {

    }

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/task_1_1_2";
    private final String password = "0000";
    private final String username = "haji";
    public Connection getJdbcConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public Session getHibernateSession() throws Exception {
        Properties prop = new Properties();
        prop.setProperty("connection.driver_class", driver);
        prop.setProperty("hibernate.connection.url", url);
        prop.setProperty("hibernate.connection.username", username);
        prop.setProperty("hibernate.connection.password", password);
        prop.setProperty("hibernate.current_session_context_class", "thread");
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.hbm2ddl.auto", "update");

        Configuration cfg = new Configuration()
                .addAnnotatedClass(User.class)
                .setProperties(prop);

        SessionFactory sf = cfg.buildSessionFactory();
        return sf.getCurrentSession();
    }
}