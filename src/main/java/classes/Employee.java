/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
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
    public String getName() {
        return lastName + "," + " " + firstName;
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
        return null; 
    }

    public LeaveBalance viewLeaveBalance() {
        return null; 
    }

    public TimeAttendance viewTimeAndAttendance() {
        return null; 
    }

    public void updatePersonalDetails() {
   
    }
    
    public static Employee loadDetails(String csvFilename, String username) {
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
        System.out.println("Employee details not found for username: " + username);
        return null;
    }
}
