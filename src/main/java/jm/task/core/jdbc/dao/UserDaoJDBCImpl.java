package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection con = Util.getConnection()) {
            try (Statement stmt = con.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS Users" +
                                "(id int primary key auto_increment," +
                                "name varchar(20), lastName varchar (20)," +
                                "age int);");
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection con = Util.getConnection()) {
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate("DROP TABLE IF EXISTS Users");
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Users (name, lastName, age)" +
                                                                "VALUES (?, ?, ?);")) {
                stmt.setString(1, name);
                stmt.setString(2, lastName);
                stmt.setByte(3, age);
                stmt.executeUpdate();
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement("DELETE FROM Users WHERE id = ?;")) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection con = Util.getConnection()) {
            try (Statement stmt = con.createStatement()) {
                try (ResultSet resultSet = stmt.executeQuery("SELECT name, lastName, age FROM Users;")) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("name");
                        String lastName = resultSet.getString("lastName");
                        Byte age = resultSet.getByte("age");
                        userList.add(new User(name, lastName, age));
                    }
                } catch (Exception e1) {
                    throw e1;
                }
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Connection con = Util.getConnection()) {
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate("DELETE FROM Users;");
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
