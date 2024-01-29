package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection con = Util.getConnection()) {
            try (Statement stmt = con.createStatement()) {
                try (ResultSet resultSet = stmt.executeQuery("create table if not exists Users" +
                                                                "(id int primary key auto_increment," +
                                                                "name varchar(20), lastName varchar (20)," +
                                                                "age int);")) {
                    System.out.println("Table Users Created Successfully");
                } catch (Exception e1) {
                    throw e1;
                }
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
                try (ResultSet resultSet = stmt.executeQuery("DROP TABLE Users")) {
                    System.out.println("Table Users dropped successfully.");
                } catch (Exception e1) {
                    throw e1;
                }
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Users" +
                                                        "VALUES (?, ?, ?);")) {
                stmt.setString(1, name);
                stmt.setString(2, lastName);
                stmt.setByte(3, age);
                try (ResultSet resultSet = stmt.executeQuery()) {
                    System.out.printf("User %s %s saved successfully.\n", name, lastName);
                } catch (Exception e1) {
                    throw e1;
                }
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
                try (ResultSet resultSet = stmt.executeQuery()) {
                    System.out.printf("User with id = %n removed successfully.\n", id);
                } catch (Exception e1) {
                    throw e1;
                }
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {
        try (Connection con = Util.getConnection()) {
            try (Statement stmt = con.createStatement()) {
                try (ResultSet resultSet = stmt.executeQuery("DELETE FROM Users;")) {
                    System.out.println("Table Users cleaned successfully.");
                } catch (Exception e1) {
                    throw e1;
                }
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
