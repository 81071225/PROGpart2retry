package com.mycompany.prog5121poe_part1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Login class handles user registration, validation, and authentication.
 */
public class Login {
    
    // Stored user details
    private String registeredUsername;
    private String registeredPassword;
    private String registeredCellPhoneNumber;
    private String firstName;
    private String lastName;

    // Temporary input fields during registration
    private String inputUsername;
    private String inputPassword;
    private String inputCellPhone;

    // Constructor
    public Login() {
    }

    // Setters for capturing user input before validation
    public void setInputData(String username, String password, String cellPhone, String firstName, String lastName) {
        this.inputUsername = username;
        this.inputPassword = password;
        this.inputCellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Checks if the username contains an underscore and is no more than 5 characters long.
     */
    public boolean checkUserName() {
        if (inputUsername == null) return false;
        boolean hasUnderscore = inputUsername.contains("_");
        boolean isValidLength = inputUsername.length() <= 5;
        return hasUnderscore && isValidLength;
    }

    /**
     * Checks if the password is at least 8 characters long, contains a capital letter, 
     * a number, and a special character.
     */
    public boolean checkPasswordComplexity() {
        if (inputPassword == null) return false;
        
        boolean hasLength = inputPassword.length() >= 8;
        boolean hasUpper = false;
        boolean hasNum = false;
        boolean hasSpecial = false;

        for (int i = 0; i < inputPassword.length(); i++) {
            char ch = inputPassword.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasNum = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }
        return hasLength && hasUpper && hasNum && hasSpecial;
    }

    /**
     * Validates cell phone number using a regular expression.
     */
    public boolean checkCellPhoneNumber() {
        if (inputCellPhone == null) return false;
        String regex = "^\\+\\d{1,3}\\d{9}$"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputCellPhone);
        return matcher.matches();
    }

    /**
     * Handles registration logic and returns the appropriate status message.
     */
    public String registerUser() {
        boolean isUsernameValid = checkUserName();
        boolean isPasswordValid = checkPasswordComplexity();
        boolean isCellValid = checkCellPhoneNumber();

        if (!isUsernameValid) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!isPasswordValid) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!isCellValid) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // If all checks pass, save credentials
        this.registeredUsername = this.inputUsername;
        this.registeredPassword = this.inputPassword;
        this.registeredCellPhoneNumber = this.inputCellPhone;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    /**
     * Verifies that the entered login credentials match stored registration data.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (registeredUsername == null || registeredPassword == null) {
            return false;
        }
        return registeredUsername.equals(enteredUsername) && registeredPassword.equals(enteredPassword);
    }

    /**
     * Returns the appropriate login status message.
     */
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}