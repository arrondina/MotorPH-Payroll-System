/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rosh
 */
public class HRStaff extends Employee { 
    
    private int staffID;
    private String department;
    
    public int getStaffID() {
        return staffID;
    }
    
    public String getDepartment() {
        return department;
    }    
    
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void addEntries(String filename) throws IOException {
        try(CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\Anna\\Downloads\\employeeDetails.csv", true))) {
            String[] employeeDetails = new String[13];
            employeeDetails[0] = String.valueOf(getEmployeeID());
            employeeDetails[1] = getLastName();
            employeeDetails[2] = getFirstName();
            employeeDetails[3] = getBirthday();
            employeeDetails[4] = getAddress();
            employeeDetails[5] = getPhoneNumber();
            employeeDetails[6] = getSSS();
            employeeDetails[7] = getPhilhealth();
            employeeDetails[8] = getTIN();
            employeeDetails[9] = getPagibig();
            employeeDetails[10] = getStatus();
            employeeDetails[11] = getPosition();
            employeeDetails[12] = getImmediateSupervisor();
            writer.writeNext(employeeDetails);
        }
    }

    public void updateEntries(String CSVFilename) throws FileNotFoundException, IOException, CsvValidationException {
        String tempFilename = CSVFilename.replace(".csv", ".tmp");
        CSVReader reader = new CSVReader(new FileReader(CSVFilename));
        String[] line;
        try(CSVWriter writer = new CSVWriter(new FileWriter(tempFilename, true))) {
            while((line = reader.readNext()) != null) {
                if (line[0].equals(String.valueOf(employeeID))) {
                    line[0] = String.valueOf(getEmployeeID());
                    line[1] = getLastName();
                    line[2] = getFirstName();
                    line[3] = getBirthday();
                    line[4] = getAddress();
                    line[5] = getPhoneNumber();
                    line[6] = getSSS();
                    line[7] = getPhilhealth();
                    line[8] = getTIN();
                    line[9] = getPagibig();
                    line[10] = getStatus();
                    line[11] = getPosition();
                    line[12] = getImmediateSupervisor();
                }
                writer.writeNext(line);
            }
            reader.close();
        } finally {
            new File(CSVFilename).delete();
            new File(tempFilename).renameTo(new File(CSVFilename));
        }
    }

    public void deleteEntries(String CSVFilename) throws FileNotFoundException, IOException, CsvValidationException {
        String tempFilename = CSVFilename.replace(".csv", ".tmp");
        CSVReader reader = new CSVReader(new FileReader(CSVFilename));
        
        String[] line;
        try(CSVWriter writer = new CSVWriter(new FileWriter(tempFilename, true))) {
            while((line = reader.readNext()) != null) {
                if(!line[0].equals(String.valueOf(employeeID))) {
                    writer.writeNext(line);
                }
            }
            reader.close();
        } finally {
            new File(CSVFilename).delete();
            new File(tempFilename).renameTo(new File(CSVFilename));
        }
    }
}