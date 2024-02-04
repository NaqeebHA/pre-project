package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        User user1 = new User("name1", "lastName1", (byte) 35);
        User user2 = new User("name2", "lastName2", (byte) 24);
        User user3 = new User("name3", "lastName3", (byte) 21);
        User user4 = new User("name4", "lastName4", (byte) 62);


        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.printf("User with name - %s added to the database\n", user1.getName());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.printf("User with name - %s added to the database\n", user2.getName());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.printf("User with name - %s added to the database\n", user3.getName());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.printf("User with name - %s added to the database\n", user4.getName());

        System.out.println("List of Users:\n" + userService.getAllUsers().toString());

        userService.removeUserById(1L);

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
