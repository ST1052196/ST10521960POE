package com.mycompany.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for Main.java.
 *
 * Note: registerUser() is not tested directly since it reads from
 * System.in in an infinite loop and is meant for interactive use.
 * Instead, the static fields it would populate (registeredUsername /
 * registeredPassword) are set directly in setUp() so that loginUser()
 * can be tested in isolation.
 */
class MainTest {

    @BeforeEach
    void setUp() {
        // Reset state before each test so tests don't leak into each other
        Main.registeredUsername = null;
        Main.registeredPassword = null;
        Main.registeredCellPhone = null;
    }

    // ---------- checkUserName ----------

    @Test
    @DisplayName("Valid username: 5 chars, contains underscore")
    void checkUserName_valid() {
        assertTrue(Main.checkUserName("kyl_1"));
    }

    @Test
    @DisplayName("Invalid username: no underscore")
    void checkUserName_noUnderscore() {
        assertFalse(Main.checkUserName("kyle1"));
    }

    @Test
    @DisplayName("Invalid username: too short")
    void checkUserName_tooShort() {
        assertFalse(Main.checkUserName("ky_1"));
    }

    @Test
    @DisplayName("Invalid username: too long")
    void checkUserName_tooLong() {
        assertFalse(Main.checkUserName("kyle_123"));
    }

    @Test
    @DisplayName("Invalid username: empty string")
    void checkUserName_empty() {
        assertFalse(Main.checkUserName(""));
    }

    // ---------- checkPasswordComplexity ----------

    @Test
    @DisplayName("Valid password: meets all complexity rules")
    void checkPasswordComplexity_valid() {
        assertTrue(Main.checkPasswordComplexity("Ch&&sec4ke"));
    }

    @Test
    @DisplayName("Invalid password: too short")
    void checkPasswordComplexity_tooShort() {
        assertFalse(Main.checkPasswordComplexity("Ch&4k"));
    }

    @Test
    @DisplayName("Invalid password: missing uppercase")
    void checkPasswordComplexity_noUppercase() {
        assertFalse(Main.checkPasswordComplexity("ch&&sec4ke"));
    }

    @Test
    @DisplayName("Invalid password: missing lowercase")
    void checkPasswordComplexity_noLowercase() {
        assertFalse(Main.checkPasswordComplexity("CH&&SEC4KE"));
    }

    @Test
    @DisplayName("Invalid password: missing number")
    void checkPasswordComplexity_noNumber() {
        assertFalse(Main.checkPasswordComplexity("Ch&&secake"));
    }

    @Test
    @DisplayName("Invalid password: missing special character")
    void checkPasswordComplexity_noSpecialChar() {
        assertFalse(Main.checkPasswordComplexity("Chxxsec4ke"));
    }

    // ---------- checkCellPhoneNumber ----------

    @Test
    @DisplayName("Valid cellphone number: +27 followed by 9 digits")
    void checkCellPhoneNumber_valid() {
        assertTrue(Main.checkCellPhoneNumber("+27821234567"));
    }

    @Test
    @DisplayName("Invalid cellphone number: missing international code")
    void checkCellPhoneNumber_missingCode() {
        assertFalse(Main.checkCellPhoneNumber("0821234567"));
    }

    @Test
    @DisplayName("Invalid cellphone number: too few digits after +27")
    void checkCellPhoneNumber_tooFewDigits() {
        assertFalse(Main.checkCellPhoneNumber("+2782123456"));
    }

    @Test
    @DisplayName("Invalid cellphone number: too many digits after +27")
    void checkCellPhoneNumber_tooManyDigits() {
        assertFalse(Main.checkCellPhoneNumber("+278212345678"));
    }

    @Test
    @DisplayName("Invalid cellphone number: wrong country code")
    void checkCellPhoneNumber_wrongCountryCode() {
        assertFalse(Main.checkCellPhoneNumber("+1821234567"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+27abcde6789", "27821234567", "+27 821234567", ""})
    @DisplayName("Invalid cellphone number: various malformed inputs")
    void checkCellPhoneNumber_malformedInputs(String number) {
        assertFalse(Main.checkCellPhoneNumber(number));
    }

    // ---------- loginUser ----------

    @Test
    @DisplayName("Login succeeds with correct username and password")
    void loginUser_correctCredentials() {
        Main.registeredUsername = "kyl_1";
        Main.registeredPassword = "Ch&&sec4ke";

        assertTrue(Main.loginUser("kyl_1", "Ch&&sec4ke"));
    }

    @Test
    @DisplayName("Login fails with incorrect username")
    void loginUser_incorrectUsername() {
        Main.registeredUsername = "kyl_1";
        Main.registeredPassword = "Ch&&sec4ke";

        assertFalse(Main.loginUser("wrong_", "Ch&&sec4ke"));
    }

    @Test
    @DisplayName("Login fails with incorrect password")
    void loginUser_incorrectPassword() {
        Main.registeredUsername = "kyl_1";
        Main.registeredPassword = "Ch&&sec4ke";

        assertFalse(Main.loginUser("kyl_1", "wrongPass1!"));
    }

    @Test
    @DisplayName("Login fails when both username and password are incorrect")
    void loginUser_incorrectBoth() {
        Main.registeredUsername = "kyl_1";
        Main.registeredPassword = "Ch&&sec4ke";

        assertFalse(Main.loginUser("nope_", "nope"));
    }

    // ---------- returnLoginStatus ----------

    @Test
    @DisplayName("Returns welcome message when login is successful")
    void returnLoginStatus_success() {
        assertEquals("Welcome, nice to see you again dzy.", Main.returnLoginStatus(true));
    }

    @Test
    @DisplayName("Returns error message when login fails")
    void returnLoginStatus_failure() {
        assertEquals("Username or password incorrect, please try again.", Main.returnLoginStatus(false));
    }
}
