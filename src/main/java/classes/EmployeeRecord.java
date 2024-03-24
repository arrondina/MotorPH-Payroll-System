/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rosh
 */
public class EmployeeRecord extends Employee {
    
    public void createEmployeeFile(String CSVFileName) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter("employeeDetails.csv", true))) {
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
    
    public void createLeaveFile(String dateRequested, String employeeID, String lastName, String firstName, String leaveType, String days, String start, String end, String remainSL, String remainVL, String remainEL, String reason) throws IOException, CsvValidationException {
        String file = "leaveApplication.csv";
        
        // Check if the file exists
        File csvFile = new File(file);
        boolean fileExists = csvFile.exists();
        
        // Append new leave application details to the existing CSV file
        try(CSVWriter csvwriter= new CSVWriter(new FileWriter(file, true))){
            // If the file does not exist, write headers first
            if (!fileExists) {
                String[] header = { "Date Requested", "Employee ID", "Last Name", "First Name", "Leave Type", "Days", "Start Date", "End Date", "Status", "Reason", "Remaining Sick Leave", "Remaining Vacation Leave", "Remaining Emergency Leave" };
                csvwriter.writeNext(header);
            }
            
            // Prepare data for the new leave application entry
            String[] data = { dateRequested, String.valueOf(employeeID), lastName, firstName, leaveType, days, start, end, "Pending", reason, remainSL, remainVL, remainEL };
            csvwriter.writeNext(data);
        }
    }
    
    public void createPayrollFile(String filename, String startDate, String endDate, String employeeID, double monthlyRate, double dailyRate, float daysWorked, double overTime, double grossIncome, double riceSubsidy, double phoneAllowance, double totalBenefits, double sss, double philhealth, double pagibig, double tax, double totalDeductions, double takeHomePay) throws IOException, CsvValidationException {
        // Check if the payroll record already exists
        if (isRecordExists(filename, startDate, endDate, employeeID)) {
            JOptionPane.showMessageDialog(null, "Payroll record already exists for the specified date range.", "Payroll Record Exists", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Check if the file exists
        File csvFile = new File(filename);
        boolean fileExists = csvFile.exists();
        
        // Append new payroll details to the existing CSV file
        try (CSVWriter csvwriter = new CSVWriter(new FileWriter(filename, true))) {
            // If the file does not exist, write headers first
            if (!fileExists) {
                String[] header = {"Start Date", "End Date", "Employee ID", "Monthly Rate", "Daily Rate", "Days Worked", "Overtime", "Gross Income", "Rice Subsidy", "Phone Allowance", "Total Benefits", "SSS", "PhilHealth", "Pagibig", "Tax", "Total Deductions", "Take Home Pay"};
                csvwriter.writeNext(header);
            }
            
            // Prepare data for the new payroll entry
            String[] data = {startDate, endDate, employeeID, formatNumber(monthlyRate), formatNumber(dailyRate), String.valueOf(daysWorked), formatNumber(overTime), formatNumber(grossIncome), formatNumber(riceSubsidy), formatNumber(phoneAllowance), formatNumber(totalBenefits), formatNumber(sss), formatNumber(philhealth), formatNumber(pagibig), formatNumber(tax), formatNumber(totalDeductions), formatNumber(takeHomePay)};
            csvwriter.writeNext(data);
            
        }
    }
    
    private boolean fileIsEmpty(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine() == null;
        } catch (IOException e) {
            return true; 
        }
    }
    
    public boolean isRecordExists(String file, String startDate, String endDate, String employeeID) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Check if the payroll record matches the specified date range and employee ID
                if (line[0].equals(startDate) && line[1].equals(endDate) && line[2].equals(employeeID)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private String formatNumber(double number) {
        // Format the number with two decimal places
        DecimalFormat df = new DecimalFormat("###0.00");
        return df.format(number);
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
        try (CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[4];
            final_header[0] = header[0]; // Employee ID
            final_header[1] = header[1]; // Date
            final_header[2] = header[2]; // Time In
            final_header[3] = header[3]; // Time Out
            attendance = new DefaultTableModel(final_header, 0);
            
            String[] line;
            while ((line = reader.readNext()) != null) {
                String recordDate = line[1].toString();
                LocalDate currentDate = LocalDate.now();
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String currentDateString = currentDate.format(formatter);
                
                if (recordDate.equals(currentDateString)) {
                    String[] record = new String[4];
                    record[0] = line[0]; // Employee ID
                    record[1] = line[1]; // Date
                    record[2] = line[2]; // Time In
                    record[3] = line[3]; // Time Out
                    attendance.addRow(record);
                }
            }
        }
        
        return attendance;
    }
    
    public DefaultTableModel fetchEmployeeAttendance(String CSVFilename, String employeeID) throws IOException, CsvValidationException {
        DefaultTableModel attendance;
        try (CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[4];
            final_header[0] = header[0]; // Employee ID
            final_header[1] = header[1]; // Date
            final_header[2] = header[2]; // Time In
            final_header[3] = header[3]; // Time Out
            attendance = new DefaultTableModel(final_header, 0);
            
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(employeeID)) {
                    String[] record = new String[4];
                    record[0] = line[0]; // Employee ID
                    record[1] = line[1]; // Date
                    record[2] = line[2]; // Time In
                    record[3] = line[3]; // Time Out
                    attendance.addRow(record);
                }
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
    
    public DefaultTableModel fetchPayslipReport(String CSVFilename) throws IOException, CsvValidationException {
        DefaultTableModel payroll;
        try(CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[13];
            final_header[0] = header[0]; // Start Date
            final_header[1] = header[1]; // End Date
            final_header[2] = header[2]; // Employee ID
            final_header[3] = header[3]; // Monthly Rate
            final_header[4] = header[4]; // Daily Rate
            final_header[5] = header[7]; // Gross Income
            final_header[6] = header[10]; // Total Benefits
            final_header[7] = header[11]; // SSS
            final_header[8] = header[12]; // Philhealth
            final_header[9] = header[13]; // Pagibig
            final_header[10] = header[14]; // Tax
            final_header[11] = header[15]; // Total Deductions
            final_header[12] = header[16]; // Take Home Pay
            payroll = new DefaultTableModel(final_header, 0);
            String[] line; 
            while((line = reader.readNext()) != null) {
                String record[] = new String[13];
                        record[0] = line[0];
                        record[1] = line[1];
                        record[2] = line[2];
                        record[3] = line[3];
                        record[4] = line[4];
                        record[5] = line[7];
                        record[6] = line[10];
                        record[7] = line[11];
                        record[8] = line[12];
                        record[9] = line[13];
                        record[10] = line[14];
                        record[11] = line[15];
                        record[12] = line[16];
                payroll.addRow(record);
            }
        }
        return payroll;
    }
    
    public DefaultTableModel fetchLeave (String CSVFilename) throws IOException, CsvValidationException {
        DefaultTableModel leave;
        try(CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[9];
            final_header[0] = header[0]; // Date Requested
            final_header[1] = header[1]; // Employee ID
            final_header[2] = header[2]; // Last Name 
            final_header[3] = header[3]; // First Name
            final_header[4] = header[4]; // Leave Type
            final_header[5] = header[5]; // Days
            final_header[6] = header[6]; // Start Date
            final_header[7] = header[7]; // Last Date
            final_header[8] = header[8]; // Status
            leave = new DefaultTableModel(final_header, 0);
            String[] line; 
            while((line = reader.readNext()) != null) {
                String record[] = new String[9];
                       record[0] = line[0];
                       record[1] = line[1];
                       record[2] = line[2];
                       record[3] = line[3];
                       record[4] = line[4];
                       record[5] = line[5];
                       record[6] = line[6];
                       record[7] = line[7];
                       record[8] = line[8];
                leave.addRow(record);       
            }
        }
        return leave;
    }
    
    public DefaultTableModel fetchRequest (String CSVFilename) throws IOException, CsvValidationException {
        DefaultTableModel leave;
        try(CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[6];
            final_header[0] = header[0]; // Date Requested
            final_header[1] = header[4]; // Leave Type
            final_header[2] = header[5]; // Days
            final_header[3] = header[6]; // Start Date
            final_header[4] = header[7]; // Last Date
            final_header[5] = header[8]; // Status
            leave = new DefaultTableModel(final_header, 0);
            String[] line; 
            while((line = reader.readNext()) != null) {
                String record[] = new String[6];
                       record[0] = line[0];
                       record[1] = line[4];
                       record[2] = line[5];
                       record[3] = line[6];
                       record[4] = line[7];
                       record[5] = line[8];
                leave.addRow(record);       
            }
        }
        return leave;
    }
    
    public DefaultTableModel fetchCredit (String CSVFilename) throws IOException, CsvValidationException {
        DefaultTableModel credit;
        try(CSVReader reader = new CSVReader(new FileReader(CSVFilename))) {
            String[] header = reader.readNext();
            
            String[] final_header = new String[6];
            final_header[0] = header[0];
            final_header[1] = header[1];
            final_header[2] = header[2];
            final_header[3] = header[3];
            final_header[4] = header[4];
            final_header[5] = header[5];
            credit = new DefaultTableModel(final_header, 0);
            String[] line; 
            while((line = reader.readNext()) != null) {
                String record[] = new String[6];
                       record[0] = line[0];
                       record[1] = line[1];
                       record[2] = line[2];
                       record[3] = line[3];
                       record[4] = line[4];
                       record[5] = line[5];
                credit.addRow(record);       
            }
        }
        return credit;
    }
    
    public String[] fetchDetail(String CSVFilename) throws FileNotFoundException, IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(CSVFilename));
        String[] record;
        String[] employeeDetail = new String[18];
            
        while ((record = reader.readNext()) != null) {
            if (record.length >= 18 && record[0].equals(employeeID)) {
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
            employeeDetail[13] = record[13]; //Basic Salary
            employeeDetail[14] = record[14]; //Rice Subsidy
            employeeDetail[15] = record[15]; //Phone Allowance
            employeeDetail[16] = record[16]; //Gross Semi-Monthly Rate
            employeeDetail[17] = record[17]; //Hourly Rate
            
                break;
            }
        }
        
        reader.close();
        return employeeDetail;
    }
    
    public String[] fetchPayslip(String CSVFilename, String selectedStartDate, String selectedEndDate, int employeeID) throws FileNotFoundException, IOException, CsvValidationException, ParseException {
        CSVReader reader = new CSVReader(new FileReader(CSVFilename));
        String[] record;
        String[] payslipDetail = null;
        
        // Skip the first row (headers)
        reader.readNext();
        
        System.out.println("Searching for payslip with Start Date: " + selectedStartDate + ", End Date: " + selectedEndDate + ", Employee ID: " + employeeID);
    
        while ((record = reader.readNext()) != null) {
            System.out.println("Checking record: " + Arrays.toString(record));
            
            if (record.length >= 17) {
                System.out.println("Record has at least 17 elements");
                
                if (record[0].trim().equals(selectedStartDate) && record[1].trim().equals(selectedEndDate) && record[2].trim().equals(String.valueOf(employeeID))) {
                payslipDetail = new String[17];
                payslipDetail[0] = record[0]; // Start Date
                payslipDetail[1] = record[1]; // End Date
                payslipDetail[2] = record[2]; // Employee ID
                payslipDetail[3] = record[3]; // Monthly Rate
                payslipDetail[4] = record[4]; // Daily Rate
                payslipDetail[5] = record[5]; // Days Worked
                payslipDetail[6] = record[6]; // Overtime
                payslipDetail[7] = record[7]; // GrossIncome
                payslipDetail[8] = record[8]; // Rice Subsidy
                payslipDetail[9] = record[9]; // Phone Allowance
                payslipDetail[10] = record[10]; // Total Benefits
                payslipDetail[11] = record[11]; // SSS
                payslipDetail[12] = record[12]; // Philhealth
                payslipDetail[13] = record[13]; // Pagibig
                payslipDetail[14] = record[14]; // Withholding Tax
                payslipDetail[15] = record[15]; // Total Deductions
                payslipDetail[16] = record[16]; // Take Home Pay
                
                System.out.println("Payslip detail found: " + Arrays.toString(payslipDetail));
                break;
            } else {
                    System.out.println("Record does not match criteria");
                }
            } else {
                System.out.println("Record does not have at least 17 elements");
            }
        }
        
        reader.close();
        return payslipDetail;
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
