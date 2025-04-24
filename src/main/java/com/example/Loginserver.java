package com.example;

import java.util.HashMap;
import java.util.Map;

public class Loginserver {
    private Map<String, String> validUsers;
    private boolean loggedIn;
    private String currentUser;

    public Loginserver() {
        validUsers = new HashMap<>();
        validUsers.put("user", "password");
        validUsers.put("admin", "admin123");
        loggedIn = false;
        currentUser = null;
    }

    public boolean login(String username, String password) {
        if (validUsers.containsKey(username) && validUsers.get(username).equals(password)) {
            loggedIn = true;
            currentUser = username;
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        loggedIn = false;
        currentUser = null;
    }
}
