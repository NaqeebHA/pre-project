package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private final Util util = new Util();

    @Override
    public void createUsersTable() {
        try (Session session = util.getHibernateSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users" +
                                        "(id int primary key auto_increment," +
                                        "name varchar(20), lastName varchar (20)," +
                                        "age int)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = util.getHibernateSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = util.getHibernateSession()) {
            session.beginTransaction();
            session.saveOrUpdate(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getHibernateSession()) {
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
        List<User> userList = new ArrayList<>(0);
        try (Session session = util.getHibernateSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User", User.class);
            userList = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = util.getHibernateSession()) {
            session.beginTransaction();
            Query<?> query = session.createQuery("DELETE FROM User");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
