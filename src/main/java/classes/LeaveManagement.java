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
public class LeaveManagement {
    
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
    
    public void approveLeave() {
        
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
