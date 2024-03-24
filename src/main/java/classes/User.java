/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Rosh
 */
public class User extends Employee {
    private String username;
    private String password;
    private String role;
    private boolean isLoggedIn;
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isLoggedIn = false;
    }
    
    // Getter and Setter    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean login(String enteredUsername, String enteredPassword) {
        if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
            isLoggedIn = true;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean authenticate(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
    
    public boolean validateRole(String enteredRole) {
        return role.equalsIgnoreCase(enteredRole);
    }
    
    public static User loadFromCSV(String csvFilename, String enteredUsername, String enteredPassword) 
            throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Anna\\Downloads\\employeeAccounts.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String username = line[1];
                String password = line[2];
                String role = line[3];
                
                if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
                    return new User(username, password, role);
                }
            }
        }
        return null;
    }
}