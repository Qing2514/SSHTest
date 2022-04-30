package com.example.action;

import com.example.domain.User;
import com.example.domain.UserException;
import com.example.service.UserService;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(Parameterized.class)
public class LoginActionTest {

    LoginAction loginAction = new LoginAction();

    private final String username;
    private final String password;

    public LoginActionTest(String username, String password) {
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
    public void testExecute() {
        try{
            User user = new User(username, password);

            UserService userService = EasyMock.createMock(UserService.class);
            EasyMock.expect(userService.loginVerify(user.getUsername(), user.getPassword())).andReturn(true);
            EasyMock.replay(userService);

            loginAction.setUserService(userService);
            String result = loginAction.execute(user.getUsername(), user.getPassword());
            assertEquals("login success", result);

            System.out.println("\tlogin success.");

        } catch(UserException e) {
            System.out.println(e.getMessage());
            System.out.println("\tlogin fail.");
        }
    }
}