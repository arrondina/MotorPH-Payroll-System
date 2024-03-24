/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.Files.lines;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rosh
 */
public class TimeAttendance {

    private int attendanceID;
    private LocalDate startDate;
    private LocalDate endDate;
    private Date time;
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
    
    public Date getTime() {
        return time;
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
    
    public void setTime(Date time) {
        this.time = time;
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
    
    private Date parseDateTime(String dateStr, String timeStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        return dateFormat.parse(dateStr + " " + timeStr);
    }

    public float calculateOvertime(String startDate, String endDate, Employee employee, String csvFilename) throws IOException, CsvValidationException, ParseException {
        
        float totalOvertime = 0;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateStart = dateFormat.parse(startDate);
        Date dateEnd = dateFormat.parse(endDate);
        
        try (CSVReader reader = new CSVReader(new FileReader(csvFilename))) {
            String[] line;
            
            while ((line = reader.readNext()) != null) {
                if (line.length >= 4 && line[0].equals(String.valueOf(employee.getEmployeeID()))) {
                    String dateStr = line[1];
                    String timeInStr = line[2];
                    String timeOutStr = line[3];
                    
                    Date eDate = dateFormat.parse(dateStr);
                    Date eTimeIn = parseDateTime(dateStr, timeInStr);
                    Date eTimeOut = parseDateTime(dateStr, timeOutStr);
                    
                    
                    if (isWithinRange(eDate, dateStart, dateEnd)) {
                        long durationMillis = eTimeOut.getTime() - eTimeIn.getTime();
                        float durationHours = TimeUnit.MILLISECONDS.toHours(durationMillis);
                        
                        if (durationHours > 9) {
                            float overtime = durationHours - 9;
                            totalOvertime += overtime;
                        }
                    }
                }
            }
        } catch (ParseException e) {
            
        }
        
        return totalOvertime;
    }

    public float calculateDays(String startDate, String endDate, int employeeID, String csvFilename) throws ParseException, FileNotFoundException, IOException, CsvValidationException {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateStart = dateFormat.parse(startDate);
        Date dateEnd = dateFormat.parse(endDate);
        
        int daysWorked = 0;
        boolean headerSkipped = false; 
        
        try (CSVReader reader = new CSVReader(new FileReader("attendanceRecord.csv"))) {
            String[] line;
            
            while((line = reader.readNext()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                
                int csvEmployeeID = Integer.parseInt(line[0]);
                Date csvDate = dateFormat.parse(line[1]);
                
                // Check if the employee ID matches the specified employee's ID
                if(csvEmployeeID == employeeID && isWithinRange(csvDate, dateStart, dateEnd)) {
                    daysWorked++;
                }
            }
        }
        
        System.out.println("Total days worked: " + daysWorked);
        return daysWorked;
    }   
    
    private boolean isWithinRange(Date date, Date startDate, Date endDate) {
        return !date.before(startDate) && !date.after(endDate);
    }
    
    public boolean isClockedIn(int employeeID) {
        try {
            List<String> records = Files.readAllLines(Paths.get("attendanceRecord.csv"));
            
            LocalDateTime currentTime = LocalDateTime.now();
            String dateString = currentTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            
            String employeeRecord = records.stream()
                .filter(record -> {
                    String[] fields = record.split(",");
                    return fields[0].equals(Integer.toString(employeeID)) && fields[1].equals(dateString);
                })
                .findFirst()
                .orElse(null);
            
            return employeeRecord != null;
            
        } catch (IOException e) {
            return false;
        }
    }
    
    public void clockIn(int employeeID) {
        LocalDateTime currentTime = LocalDateTime.now();
        String dateString = currentTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String dateTimeString = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        appendToAttendanceRecord(employeeID, dateString, dateTimeString, null);
    }
    
    public void clockOut(int employeeID) {
        LocalDateTime currentTime = LocalDateTime.now();
        String dateString = currentTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String dateTimeString = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        appendToAttendanceRecord(employeeID, dateString, null, dateTimeString);
    }
    
    private void appendToAttendanceRecord(int employeeID, String date, String timeIn, String timeOut) {
        try {
            File inputFile = new File("attendanceRecord.csv");
            File tempFile = new File("tempAttendanceRecord.csv");
        
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        
            String lineToUpdate = employeeID + "," + date + ",";
            String currentLine;
            boolean found = false;
            
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith(lineToUpdate)) {
                    if (timeOut != null) {
                        currentLine += "," + timeOut;
                        found = true;
                    }
                }
                
                writer.write(currentLine + System.lineSeparator());
            }
            
            if (!found && timeIn != null) {
                String lineToAdd = employeeID + "," + date + "," + timeIn;
                if (timeOut != null) {
                    lineToAdd += "," + timeOut;
                }
                
                writer.write(lineToAdd + System.lineSeparator());
            }
            
            writer.close();
            reader.close();
                
            // Replace the original file with the temporary file
            inputFile.delete();
            tempFile.renameTo(inputFile);
        
        } catch (IOException e) {
            
        }
    }
   
    public void generateAttendanceReport() {
        
    }
    
    public void calculateMonthlyAttendance() {
        
    }
}
