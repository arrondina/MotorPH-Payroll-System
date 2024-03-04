/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rosh
 */
public class TimeAttendance {

    private int attendanceID;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime timeIn;
    private LocalTime timeOut;
    
    // Getters and Setters
    public int getAttendanceID() {
        return attendanceID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }
    
    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }
    
    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }
    
    // Methods
    public DefaultTableModel fetchEmployees(String CSVFilename) throws IOException, CsvValidationException {
        DefaultTableModel employees = new DefaultTableModel(new String[]{"Employee ID", "Date", "Time-in", "Time-out"}, 0);
        try(CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[4];
            final_header[0] = header[0];
            final_header[1] = header[1];
            final_header[2] = header[2];
            final_header[3] = header[3];
            employees = new DefaultTableModel(final_header, 0);
            String[] line; 
            while((line = reader.readNext()) != null) {
                String record[] = new String[4];
                        record[0] = line[0];
                        record[1] = line[1];
                        record[2] = line[2];
                        record[3] = line[3];
                employees.addRow(record);
            }
        }
        return employees;
    }
    
    public void calculateMonthlyAttendance() {
        // Logic to calculate monthly attendance
    }

    public void calculateOT() {
        // Logic to calculate overtime
    }

    public void calculateWorkhours() {
        // Logic to calculate work hours
    }

    public void generateAttendanceReport() {
        // Logic to generate attendance report
    }
}
