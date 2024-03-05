/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rosh
 */
public class EmployeeRecord extends Employee {
    
    public void createEmployeeFile(String CSVFileName) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\Anna\\Downloads\\employeeDetails.csv", true))) {
            String[] headers = new String[18];
            headers[0] = "Employee ID";
            headers[1] = "Last Name";
            headers[2] = "First Name";
            headers[3] = "Birthday";
            headers[4] = "Address";
            headers[5] = "Phone Number";
            headers[6] = "SSS";
            headers[7] = "Philhealth";
            headers[8] = "TIN";
            headers[9] = "Pag-ibig";
            headers[10] = "Status";
            headers[11] = "Position";
            headers[12] = "Immediate Supervisor";
            headers[13] = "Basic Salary";
            headers[14] = "Rice Subsidy";
            headers[15] = "Phone Allowance";
            headers[16] = "Gross Semi-Monthly Rate";
            headers[17] = "Hourly Rate";
            
            writer.writeNext(headers);
        }
    }
    
    public void createLeaveFile(String dateRequested, String leaveType, String days,String start, String end) throws IOException, CsvValidationException {
        String file = "C:\\Users\\Anna\\Downloads\\leaveRecord.csv";
        
        // Read leave record to get remaining leave balances
        CSVReader csvreader = new CSVReader(new FileReader(file));
        String [] line;
        String remainSL = null;
        String remainVL = null;
        String remainEL = null;
        
        // Find the employee's record and get remaining leave balances
        while((line = csvreader.readNext())!=null){
            if(line[0].equals(employeeID)){
                remainSL = line[3];
                remainVL = line[4];
                remainEL = line[5];
                break;
            }
        }
        csvreader.close();
        
        // Write leave application details to a new CSV file
        try(CSVWriter csvwriter= new CSVWriter(new FileWriter("leaveApplication.csv"))){
            String[] header = { "Date Requested", "Employee Number", "Last Name", "First Name", "Type of Leave Applied", "Number of Days", "Start Date", "End Date", "Application Status", "Remarks", "Remaining Sick Leave", "Remaining Vacation Leave", "Remaining Emergency Leave" };
            csvwriter.writeNext(header);
            
            // Prepare data for the new leave application entry
            String[] data = { dateRequested, String.valueOf(employeeID), getLastName(), getFirstName(), leaveType, days, start, end, "", "", remainSL, remainVL, remainEL };
            csvwriter.writeNext(data);
        }
    }
    
    public DefaultTableModel fetchEmployees(String CSVFilename) throws IOException, CsvValidationException {
        DefaultTableModel employees;
        try(CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[5];
            final_header[0] = header[0];
            final_header[1] = header[1];
            final_header[2] = header[2];
            final_header[3] = header[3];
            final_header[4] = header[4];
            employees = new DefaultTableModel(final_header, 0);
            String[] line; 
            while((line = reader.readNext()) != null) {
                String record[] = new String[5];
                        record[0] = line[0];
                        record[1] = line[1];
                        record[2] = line[2];
                        record[3] = line[3];
                        record[4] = line[4];
                employees.addRow(record);
            }
        }
        return employees;
    }
    
    public DefaultTableModel fetchAttendance(String CSVFilename) throws IOException, CsvValidationException {
        DefaultTableModel attendance;
        try(CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[6];
            final_header[0] = header[0];
            final_header[1] = header[1];
            final_header[2] = header[2];
            final_header[3] = header[3];
            final_header[4] = header[4];
            final_header[5] = header[5];
            attendance = new DefaultTableModel(final_header, 0);
            String[] line; 
            while((line = reader.readNext()) != null) {
                String record[] = new String[6];
                        record[0] = line[0];
                        record[1] = line[1];
                        record[2] = line[2];
                        record[3] = line[3];
                        record[4] = line[4];
                        record[5] = line[5];
                attendance.addRow(record);
            }
        }
        return attendance;
    }
    
    public DefaultTableModel fetchPayroll(String CSVFilename) throws IOException, CsvValidationException {
        DefaultTableModel payroll;
        try(CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[8];
            final_header[0] = header[0];
            final_header[1] = header[1];
            final_header[2] = header[2];
            final_header[3] = header[13];
            final_header[4] = header[14];
            final_header[5] = header[15];
            final_header[6] = header[16];
            final_header[7] = header[17];
            payroll = new DefaultTableModel(final_header, 0);
            String[] line; 
            while((line = reader.readNext()) != null) {
                String record[] = new String[8];
                        record[0] = line[0];
                        record[1] = line[1];
                        record[2] = line[2];
                        record[3] = line[13];
                        record[4] = line[14];
                        record[5] = line[15];
                        record[6] = line[16];
                        record[7] = line[17];
                payroll.addRow(record);
            }
        }
        return payroll;
    }
    
    public String[] fetchDetail(String CSVFilename) throws FileNotFoundException, IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(CSVFilename));
        String[] record;
        String[] employeeDetail = new String[13];
            
        while ((record = reader.readNext()) != null) {
            if (record.length >= 13 && record[0].equals(employeeID)) {
            employeeDetail[0] = record[0]; //Employee ID
            employeeDetail[1] = record[1]; //Last Name
            employeeDetail[2] = record[2]; //First Name
            employeeDetail[3] = record[3]; //Birthday
            employeeDetail[4] = record[4]; //Address
            employeeDetail[5] = record[5]; //Phone Number
            employeeDetail[6] = record[6]; //SSS
            employeeDetail[7] = record[7]; //Philhealth
            employeeDetail[8] = record[8]; //TIN
            employeeDetail[9] = record[9]; //Pag-ibig
            employeeDetail[10] = record[10]; //Status
            employeeDetail[11] = record[11]; //Position
            employeeDetail[12] = record[12]; //Immediate Supervisor
                break;
            }
        }
        
        reader.close();
        return employeeDetail;
    }
    
    public String[] loadLeaveBalances(String csvFilename, int employeeID) throws IOException, CsvValidationException {
        String[] leaveBalances = new String[3]; 
        
        try (CSVReader reader = new CSVReader(new FileReader(csvFilename))) {
            String[] header = reader.readNext(); // Read the header row
            if (header == null || header.length < 6) {
                throw new CsvValidationException("Invalid CSV file format: Missing or incomplete header.");
            }
            
            String[] line;
            while ((line = reader.readNext()) != null) {
                int id = Integer.parseInt(line[0]);
                if (id == employeeID) {
                    leaveBalances[0] = line[3]; //Sick Leave
                    leaveBalances[1] = line[4]; //Vacation Leave
                    leaveBalances[2] = line[5]; //Emergency Leave
                    return leaveBalances;
                }
            }
        }
        
        return new String[3];
    }
    
}
