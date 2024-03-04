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
import java.util.ArrayList;

/**
 *
 * @author Rosh
 */
public class Employee {
    int employeeID;
    private String lastName;
    private String firstName;
    private String birthday;
    private String address;
    private String phoneNumber;
    private String sssNumber;
    private String philhealthNumber;
    private String tinNumber;
    private String pagibigNumber;
    private String status;
    private String position;
    private String immediateSupervisor;
    private ArrayList<TimeAttendance> timeAndAttendanceRecords;
    private ArrayList<LeaveBalance> leaveBalances;
    private ArrayList<EmployeeRecord> employeeRecords;
    private ArrayList<Payslip> payslips;
    
    public Employee() {
        this.timeAndAttendanceRecords = new ArrayList<>();
        this.leaveBalances = new ArrayList<>();
        this.employeeRecords = new ArrayList<>();
        this.payslips = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getEmployeeID() {
        return employeeID;
    }
    public String getLastName() {
	return lastName;
    }
    public String getFirstName() {
	return firstName;
    }
    public String getBirthday() {
	return birthday;
    }
    public String getAddress() {
	return address;
    }
    public String getPhoneNumber() {
	return phoneNumber;
    }
    public String getSSS() {
	return sssNumber;
    }
    public String getPhilhealth() {
	return philhealthNumber;
    }
    public String getTIN() {
	return tinNumber;
    }
    public String getPagibig() {
	return pagibigNumber;
    }
    public String getStatus() {
	return status;
    }
    public String getPosition() {
	return position;
    }
    public String getImmediateSupervisor() {
	return immediateSupervisor;
    }
    
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setAddress(String address) {
	this.address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }
    public void setSSS(String sss) {
	this.sssNumber = sss;
    }   
    public void setPhilhealth(String philhealth) {
	this.philhealthNumber = philhealth;
    } 
    public void setTIN(String tin) {
	this.tinNumber = tin;
    }   
    public void setPagibig(String pagibig) {
	this.pagibigNumber = pagibig;
    }   
    public void setStatus(String status) {
	this.status = status;
    }  
    public void setPosition(String position) {
	this.position = position;
    }  
    public void setImmediateSupervisor(String immediateSupervisor) {
	this.immediateSupervisor = immediateSupervisor;
    }  
    
    public void addTimeAttendanceRecord(TimeAttendance record) {
        this.timeAndAttendanceRecords.add(record);
    }

    public void addLeaveBalance(LeaveBalance balance) {
        this.leaveBalances.add(balance);
    }

    public void addEmployeeRecord(EmployeeRecord record) {
        this.employeeRecords.add(record);
    }

    public void addPayslip(Payslip payslip) {
        this.payslips.add(payslip);
    }

    public Payslip viewPayslip() {
        // Logic to view payslip
        return null; // Placeholder, implement your logic
    }

    public LeaveBalance viewLeaveBalance() {
        // Logic to view leave balance
        return null; // Placeholder, implement your logic
    }

    public TimeAttendance viewTimeAndAttendance() {
        // Logic to view time and attendance
        return null; // Placeholder, implement your logic
    }

    public void updatePersonalDetails() {
        // Logic to update personal details
    }

    public void applyForLeave(String filename, String leaveType, String days) throws FileNotFoundException, IOException, CsvValidationException {
        String tempfilename = filename.replace(".csv", ".tmp");
        
        try(CSVWriter csvwriter = new CSVWriter(new FileWriter(tempfilename,true))){
            
            String[] line;
            
            CSVReader csvreader = new CSVReader(new FileReader(filename)); 
            
            String[] header = csvreader.readNext();
            csvwriter.writeNext(header);
            
            while((line=csvreader.readNext())!=null){
                if(line[0].equals(employeeID) & leaveType.equals("Sick Leave")){
                    line[3] = String.valueOf(Integer.parseInt(line[3])-Integer.parseInt(days));
                }
                else if(line[0].equals(employeeID) & leaveType.equals("Vacation Leave")){
                    line[4] = String.valueOf(Integer.parseInt(line[4])-Integer.parseInt(days));
                }
                else if(line[0].equals(employeeID) & leaveType.equals("Emergency Leave")){
                    line[5] = String.valueOf(Integer.parseInt(line[5])-Integer.parseInt(days));
                }
                
                csvwriter.writeNext(line); 
            }
            
            csvreader.close();
        }
        finally{
            new File(filename).delete();
            new File(tempfilename).renameTo(new File(filename));
        }
    }
    
     public boolean leaveIsAllowed(String filename,String leaveType, String days) throws FileNotFoundException, IOException, CsvValidationException{
        
        try(CSVReader csvreader = new CSVReader(new FileReader(filename))){
            String[] line;
            int leaveDays = Integer.parseInt(days);
            while((line=csvreader.readNext())!=null){
                if(line[0].equals(employeeID) & leaveType.equals("Sick Leave")){
                    int remainSL = Integer.parseInt(line[3]);
                    
                    if(leaveDays>remainSL){
                        return false;
                    }
                }
                else if(line[0].equals(employeeID) & leaveType.equals("Vacation Leave")){
                    int remainVL = Integer.parseInt(line[4]);
                    
                    if(leaveDays>remainVL){
                        return false;
                    }
                }
                else if(line[0].equals(employeeID) & leaveType.equals("Emergency Leave")){
                    int remainEL = Integer.parseInt(line[3]);
                    
                    if(leaveDays>remainEL){
                        return false;
                    }
                }
            }
            
            return true;
        }
    }
     
    public void createLeaveApplication(String dateFiled, String leaveType, String days,String start, String end) throws IOException, CsvValidationException {
        String file = "C:\\Users\\Anna\\Downloads\\leaveRecord.csv";
        
        CSVReader csvreader = new CSVReader(new FileReader(file));
        String [] line;
        String remainSL=null;
        String remainVL=null;
        String remainEL=null;
        
        while((line=csvreader.readNext())!=null){
            if(line[0].equals(employeeID)){
                remainSL = line[3];
                remainVL = line[4];
                remainEL = line[5];
                break;
            }
        }
        csvreader.close();
        
        try(CSVWriter csvwriter= new CSVWriter(new FileWriter("leaveApplication.csv"))){
            String[] header = { "Date Filed", "Employee Number", "Last Name", "First Name", "Type of Leave Applied", "Number of Days", "Start Date", "End Date", "Application Status", "Remarks", "Remaining Sick Leave", "Remaining Vacation Leave", "Remaining Emergency Leave" };
            csvwriter.writeNext(header);
            
            String[] data = { dateFiled, String.valueOf(employeeID), lastName, firstName, leaveType, days, start, end, "Approved", "", remainSL, remainVL, remainEL };
            csvwriter.writeNext(data);
        }
    }
    
    public static Employee loadDetailsFromCSV(String csvFilename, String username) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilename))) {
            
            String[] header = reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                int id = Integer.parseInt(line[0]);
                String employeeUsername = line[2];
                
                if (username.equals(employeeUsername)) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(id);
                    employee.setLastName(line[1]);
                    employee.setFirstName(line[2]);
                    employee.setBirthday(line[3]);
                    employee.setAddress(line[4]);
                    employee.setPhoneNumber(line[5]);
                    employee.setSSS(line[6]);
                    employee.setPhilhealth(line[7]);
                    employee.setTIN(line[8]);
                    employee.setPagibig(line[9]);
                    employee.setStatus(line[10]);
                    employee.setPosition(line[11]);
                    employee.setImmediateSupervisor(line[12]);
                    return employee;
                }
            }
        } catch (IOException | CsvValidationException e) {
        
        }
        return null;
    }
}
