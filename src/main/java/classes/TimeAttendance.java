/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
    //  Fetches employee attendance records from a CSV file and returns them as a DefaultTableModel.
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
        
    }

    public void calculateOT() {
        
    }

    public float calculateWorkhours(String startDate, String endDate, Employee employee) throws FileNotFoundException, IOException, CsvValidationException, ParseException {
        
        SimpleDateFormat format1 = new SimpleDateFormat("M/d/yyyy HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("M/d/yyyy");
        
        Date dateIn = format2.parse(startDate);
        Date dateOut = format2.parse(endDate);
        
        try(CSVReader csvreader = new CSVReader(new FileReader("C:\\Users\\Anna\\Downloads\\attendanceRecord.csv"))) {
            String[] line;
            ArrayList<String> timein = new ArrayList<>();
            ArrayList<String> timeout = new ArrayList<>();
            
            while((line=csvreader.readNext())!=null) {
                if(line[0].equals(employee.getEmployeeID())) {
                    timein.add(line[1]);
                    timeout.add(line[2]);
                }
            }
            
            int indexStart = 0;
            for (int i =0; i< timein.size();i++) {
                Date dStart = format1.parse(timein.get(i));
                if (dStart.compareTo(dateIn)==0) {
                    indexStart = i;
                }  
            }
            
            int indexEnd = 0;
            for (int i =0; i< timeout.size();i++) { 
                Date dEnd = format2.parse(timeout.get(i)); 
                if (dEnd.compareTo(dateOut)==0) {  
                    indexEnd=i;
                }
            }
            
            float sum= 0; 
            for (int k =indexStart; k<=indexEnd;k++) { 
                Date t1 = format1.parse(timein.get(k));
                Date t2 = format1.parse(timeout.get(k)); 
                
                float t = t2.getTime() - t1.getTime()-(60 * 60 * 1000); 
                float test =  t/(60 * 60 * 1000);
                
                if (test >= 47/6) {
                    test = 8;
                }
                
                sum = sum + test; 
            }
            
            return sum;
        }
    }   

    public void generateAttendanceReport() {
        
    }
}
