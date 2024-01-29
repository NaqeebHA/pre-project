package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            User userToRemoveById = session.get(User.class, id);
            if (userToRemoveById != null)
                session.delete(userToRemoveById);
            else
                System.out.println("User with such id is not found");
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM Users", User.class);
            userList = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            Query<?> query = session.createQuery("DELETE FROM Users");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
