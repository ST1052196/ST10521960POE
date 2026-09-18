package com.mycompany.main;
//st10521960
import java.util.Scanner;

public class Main {
    // Creates Scanner for user input
    static Scanner input = new Scanner(System.in);
    // Stores the successfully registered details
    static String registeredUsername;
    static String registeredPassword;
    static String registeredCellPhone;

    // Checks if the username is valid
    public static boolean checkUserName(String username) {
        // Username must have exactly 5 characters and must contain an underscore
        return username.length() == 5 & username.contains("_");
    }

    // Checks if the password is valid
    public static boolean checkPasswordComplexity(String password) {
        // Password must have at least 8 characters and contain uppercase, lowercase, number and special character
        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[^a-zA-Z0-9].*");
    }

    // Checks if the cellphone number is valid
    public static boolean checkCellPhoneNumber(String number) {
        // Number must start with +27 followed by exactly 9 digits
        return number.matches("^\\+27[0-9]{9}$");
    }

    // Handles the registration process
    public static void registerUser() {
        String username, password, cellPhone;

        // Infinite loop for username
        while (true) {
            System.out.print("Enter your username (5 chars with _): ");
            username = input.nextLine();
            if (checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            }
        }

        // infinite Loop for password
        while (true) {
            System.out.print("Enter your password: ");
            password = input.nextLine();
            if (checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
            }
        }

        // infinite Loop for cellphone
        while (true) {
            System.out.print("Enter your cellphone number (+27...): ");
            cellPhone = input.nextLine();
            if (checkCellPhoneNumber(cellPhone)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        // Save the valid username details
        registeredUsername = username;
        // Save the valid password 
        registeredPassword = password;
        // Save the valid cellPhone number 
        registeredCellPhone = cellPhone;
        // Display success meesage 
        System.out.println("User registered successfully.");
    }

    // Checks the login details
    public static boolean loginUser(String username, String password) {
        // Compare entered details with registered details
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    // Returns the login status message
    public static String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome, nice to see you again dzy.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Main method
    public static void main(String[] args) {
        // Call registration method
        registerUser();

        // Ask for login details separately
        System.out.print("Enter your username to login: ");
        String loginUsername = input.nextLine();
        System.out.print("Enter your password to login: ");
        String loginPassword = input.nextLine();

        // Call login method
        boolean loginSuccessful = loginUser(loginUsername, loginPassword);
        // Call login status method
        System.out.println(returnLoginStatus(loginSuccessful));
    }
}
