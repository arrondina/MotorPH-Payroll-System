/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



/**
 *
 * @author Rosh
 */
public class LeaveManagement extends HRStaff {
    
    private String requestDate;
    private String leaveType;
    private String days;
    private String startDate;
    private String endDate;
    
    public String getRequestDate() {
        return requestDate;
    }
    
    public String getLeaveType() {
        return leaveType;
    }    
    
    public String getDays() {
        return days;
    }  
    
    public String getStartDate() {
        return startDate;
    }  
    
    public String getEndDate() {
        return endDate;
    }  
    
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
    
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
    
    public void setDays(String days) {
        this.days = days;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
    public boolean applyForLeave(String filename, String employeeID, String leaveType, String days) throws IOException, CsvValidationException {
        try (CSVReader csvreader = new CSVReader(new FileReader(filename))) {
            String[] line;
            int leaveDays = Integer.parseInt(days);
            
            while ((line = csvreader.readNext()) != null) {
                if (line[0].equals(employeeID)) {
                    if (leaveType.equals("Sick Leave")) {
                        int remainSL = Integer.parseInt(line[3]);
                        if (leaveDays > remainSL) {
                            return false;
                        }
                    } else if (leaveType.equals("Vacation Leave")) {
                        int remainVL = Integer.parseInt(line[4]);
                        if (leaveDays > remainVL) {
                            return false;
                        }
                    }  else if (leaveType.equals("Emergency Leave")) {
                        int remainEL = Integer.parseInt(line[5]);
                        if (leaveDays > remainEL) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    } 
    
    public void approveLeave(String filename, String employeeID, String requestID) throws IOException, CsvValidationException {
        String tempfilename = filename.replace(".csv", ".tmp");
        
        // Flag to track if changes were made
        boolean changesMade = false;
        
        try (CSVReader csvreader = new CSVReader(new FileReader(filename));
             CSVWriter csvwriter = new CSVWriter(new FileWriter(tempfilename, true))) {
            
            String[] line;
            String[] header = csvreader.readNext();
            csvwriter.writeNext(header);
            
            while ((line = csvreader.readNext()) != null) {
                if (line[1].trim().equals(employeeID) && line[0].trim().equals(requestID)) {
                    int statusIndex = 8;
                    
                    // Check if the status is "Pending" or "Rejected"
                    if (line[statusIndex].trim().equalsIgnoreCase("Pending") || line[statusIndex].trim().equalsIgnoreCase("Rejected")) {
                        
                        // Change the status to "Approved"
                        line[statusIndex] = "Approved";
                        changesMade = true;
                    }
                    
                    // Write the modified line to the temporary file
                    csvwriter.writeNext(line);
                    } else {
                        
                        // Write unchange lines to the temporary file
                        csvwriter.writeNext(line);
                }
            } 
        } finally {
            // Replace the original file only if changes were made
            if (changesMade) {
                Files.deleteIfExists(Paths.get(filename));
                Files.move(Paths.get(tempfilename), Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
            } else {
                Files.deleteIfExists(Paths.get(tempfilename));
            }
        } 
    }
    
    public void rejectLeave(String filename, String employeeID, String status) throws IOException, CsvValidationException {
        String tempfilename = filename.replace(".csv", ".tmp");
        
        // Flag to track if changes were made
        boolean changesMade = false;
        
        try (CSVReader csvreader = new CSVReader(new FileReader(filename));
             CSVWriter csvwriter = new CSVWriter(new FileWriter(tempfilename, true))) {
            
            String[] line;
            String[] header = csvreader.readNext();
            csvwriter.writeNext(header);
            
            while ((line = csvreader.readNext()) != null) {
                if (line[1].trim().equals(employeeID)) {
                    int statusIndex = 8;
                    
                    // Change the status to "Rejected"
                    line[statusIndex] = "Rejected";
                    changesMade = true;
                }
                
                // Write the modified line to the temporary file
                csvwriter.writeNext(line);
            } 
        } finally {
            // Replace the original file only if changes were made
            if (changesMade) {
                Files.deleteIfExists(Paths.get(filename));
                Files.move(Paths.get(tempfilename), Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
            } else {
                Files.deleteIfExists(Paths.get(tempfilename));
            }
        } 
    }
    
    public void calculateLeaveBalance(String filename, String employeeID, String leaveType, String days) throws IOException, CsvValidationException {
        String tempfilename = filename.replace(".csv", ".tmp");
        
        try (CSVReader csvreader = new CSVReader(new FileReader(filename));
             CSVWriter csvwriter = new CSVWriter(new FileWriter(tempfilename, true))) {
            
            String[] line;
            String[] header = csvreader.readNext();
            csvwriter.writeNext(header);
            
            while ((line = csvreader.readNext()) != null) {
                if (line[0].equals(employeeID)) {
                    int sickLeaveIndex = 3;
                    int vacationLeaveIndex = 4;
                    int emergencyLeaveIndex = 5;
                    
                    if (leaveType.equals("Sick Leave")) {
                        line[sickLeaveIndex] = String.valueOf(Integer.parseInt(line[sickLeaveIndex]) - Integer.parseInt(days));
                    } else if (leaveType.equals("Vacation Leave")) {
                        line[vacationLeaveIndex] = String.valueOf(Integer.parseInt(line[vacationLeaveIndex]) - Integer.parseInt(days));
                    } else if (leaveType.equals("Emergency Leave")) {
                        line[emergencyLeaveIndex] = String.valueOf(Integer.parseInt(line[emergencyLeaveIndex]) - Integer.parseInt(days));
                    }
                }
                
                csvwriter.writeNext(line);
            }
        } finally {
            Files.deleteIfExists(Paths.get(filename));
            Files.move(Paths.get(tempfilename), Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
        }
        
    }
    
    public void generateLeaveReport() {
        
    }
    
}
