package com.example.service;

import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.domain.UserException;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(Parameterized.class)
public class UserServiceTest {

    UserService userService = new UserService();

    private final String username;
    private final String password;

    public UserServiceTest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Parameterized.Parameters(name = "{index}: username = {0}, password = {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"admin", "123456"},
                {123456, "123456"},
                {"admin", 123456},
                {123456, 123456},
                {null, "123456"},
                {"admin", null},
                {null, null},
                {"admin"},
                {"admin", "123456","111"},
                {"adminadminadmin", "123456"},
                {"admin", "123456123456"},
                {"adminadminadmin", "123456123456"},
                {"ad", "123456"},
                {"admin", "123"},
                {"ad", "123"},
                {"*****", "123456"},
                {"admin", "******"},
                {"*****", "******"},
                });
    }

    @Test
    public void testLoginVerify() {
        try{
            User user = new User(username, password);

            UserDao userDao = EasyMock.createMock(UserDao.class);
            EasyMock.expect(userDao.selectByUsername(user.getUsername())).andReturn(1);
            EasyMock.expect(userDao.selectUser(user.getUsername(), user.getPassword())).andReturn(1);
            EasyMock.replay(userDao);

            userService.setUserDao(userDao);
            assertTrue(userService.loginVerify(user.getUsername(), user.getPassword()));

            System.out.println("\tlogin success.");

        } catch(UserException e) {
            System.out.println(e.getMessage());
            System.out.println("\tlogin fail.");
        }
    }

    @Test
    public void testUserRegister() {
        try{
            User user = new User(username, password);

            UserDao userDao = EasyMock.createMock(UserDao.class);
            EasyMock.expect(userDao.selectByUsername(user.getUsername())).andReturn(1);
            EasyMock.expect(userDao.selectUser(user.getUsername(), user.getPassword())).andReturn(1);
            EasyMock.replay(userDao);

            userService.setUserDao(userDao);
            assertFalse(userService.userRegister(user));

            System.out.println("\tregister success.");

        } catch(UserException e) {
            System.out.println(e.getMessage());
            System.out.println("\tregister fail.");
        }
    }
}