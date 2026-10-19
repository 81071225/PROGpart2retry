package com.mycompany.prog5121poe_part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login();
        login.setInputData("kyl_1", "Valid1@pass", "+27838968976", "John", "Doe");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login();
        login.setInputData("kyle!!!!!!!", "Valid1@pass", "+27838968976", "John", "Doe");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        Login login = new Login();
        login.setInputData("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login login = new Login();
        login.setInputData("kyl_1", "password", "+27838968976", "John", "Doe");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login login = new Login();
        login.setInputData("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login();
        login.setInputData("kyl_1", "Ch&&sec@ke99!", "08966553", "John", "Doe");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccessful() {
        Login login = new Login();
        login.setInputData("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        login.registerUser();
        
        boolean success = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(success);
        
        String status = login.returnLoginStatus(success);
        assertEquals("Welcome Kyle, Smith it is great to see you again.", status);
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login();
        login.setInputData("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        login.registerUser();
        
        boolean failed = login.loginUser("wrong", "wrongpass");
        assertFalse(failed);
    }
}
