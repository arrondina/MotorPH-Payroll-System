/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 *
 * @author Rosh
 */
public class Payslip {
    private int payrollID;
    private LocalDate startDate;
    private LocalDate endDate;
    
    // Earnigns
    private double monthlyRate;
    private double dailyRate;
    private double daysWorked;
    private double overtime;
    private double grossIncome;
    
    // Benefits
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double totalBenefits;
    
    // Deductions
    private double sss;
    private double philhealth;
    private double pagibig;
    private double tax;
    private double totalDeductions;
    
    // Summary
    private double takeHomePay;
    
    // Getters and Setters
    public int getPayrollID() {
        return payrollID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getDaysWorked() {
        return daysWorked;
    }

    public double getOvertime() {
        return overtime;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    public double getTotalBenefits() {
        return totalBenefits;
    }

    public double getSSS() {
        return sss;
    }

    public double getPhilhealth() {
        return philhealth;
    }

    public double getPagibig() {
        return pagibig;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public double getTakeHomePay() {
        return takeHomePay;
    }
    
    public void setPayrollID(int payrollID) {
        this.payrollID = payrollID;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public void setDaysWorked(double daysWorked) {
        this.daysWorked = daysWorked;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public void setRiceSubsidy(double riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public void setPhoneAllowance(double phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public void setClothingAllowance(double clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }

    public void setTotalBenefits(double totalBenefits) {
        this.totalBenefits = totalBenefits;
    }

    public void setSSS(double sss) {
        this.sss = sss;
    }

    public void setPhilhealth(double philhealth) {
        this.philhealth = philhealth;
    }

    public void setPagibig(double pagibig) {
        this.pagibig = pagibig;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setTotalDeductions(double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public void setTakeHomePay(double takeHomePay) {
        this.takeHomePay = takeHomePay;
    }
    
    // Methods
    
    public Payslip viewPayslip() {
        // Logic to view payslip
        return null;
    }

    public void downloadPayslip() {
        // Logic to download payslip
    }
    
    public void generatePayslip() {
        // Logic to generate payslip
    }

    public void generatePayrollReport() {
        // Logic to generate payroll report
    }

    public double calculateGrossIncome(double monthlyRate, double overtime, double totalBenefits) {
        grossIncome = monthlyRate + overtime + totalBenefits;
        return grossIncome;
    }
    
    public double calculateDailyRate(double monthlyRate, float daysWorked) {
        if (daysWorked == 0) {
            // Return 0 to avoid division by zero
            return 0;
        }
        
        double dRate = monthlyRate / daysWorked;
        return dRate;
    }
    
    public double calculateTotalBenefits(double riceSubsidy, double phoneAllowance) {
        double tBenefits = riceSubsidy + phoneAllowance;
        return tBenefits;
    }
    
    public double calculateTotalDeductions(double sss, double philhealth, double pagibig, double tax) {
        totalDeductions = sss + philhealth + pagibig + tax;
        return totalDeductions;
    }
    
    public double calculateTakeHomePay(double grossIncome, double totalDeductions) {
        return grossIncome - totalDeductions;
    }
    
    public String calculateSSS(double monthlyRate) {
        double sssValue;
        
        // Calculate SSS based on monthly rate
        if(monthlyRate <= 3250) {
            sssValue = 135;
        } else if (monthlyRate <= 24750) {
            if(monthlyRate %1000 == 250 || monthlyRate % 1000 == 750 ) {
                float mod = (float) ((monthlyRate - 3250) % 500);
                float multiplier = (float) ((monthlyRate - 3250 - mod) / 500); 
                sssValue = 22.5 * (multiplier) + 135;
            } else {
                float mod = (float) ((monthlyRate - 3250) % 500); 
                float multiplier = (float) ((monthlyRate - 3250 - mod) / 500);              
                sssValue = 22.5 *(multiplier + 1) + 135;     
            }
        } else {
            sssValue = 1125;
        }
        
        return String.format("%.2f",sssValue);  
    }
    
    public String calculatePhilhealth(double monthlyRate) {
        double philhealthValue;
        
        // Calculate Philhealth based on monthly rate
        if(monthlyRate <= 10000) {
            philhealthValue = 300/2;
        } else if (monthlyRate < 60000) {
            philhealthValue = monthlyRate * 0.03 / 2;
        } else {
            philhealthValue = 1800/2;
        }
        
        return String.format("%.2f",philhealthValue);
    }
    
    public String calculatePagibig(double monthlyRate) {
        double pagibigValue;
        
        // Variable to store the computed Pagibig value
        if(monthlyRate > 1000 && monthlyRate <= 1500) {
            pagibigValue = monthlyRate * 0.01;
        } else {
            pagibigValue = Math.min(monthlyRate * 0.02, 100);
        }
        
        return String.format("%.2f",pagibigValue);  
    }
    
    public String calculateTax(double monthlyRate) {
        double taxValue;
        double sal = monthlyRate;
        double sssValue = Double.parseDouble(calculateSSS(monthlyRate));
        double philhealthValue = Double.parseDouble(calculatePhilhealth(monthlyRate));
        double pagibigValue = Double.parseDouble(calculatePagibig(monthlyRate));
        
        // Compute taxable income
        double taxable = sal - sssValue - pagibigValue - philhealthValue;
        
        // Compute tax based on taxable income
        if (sal <= 20832) {
            taxValue = 0;
        } else if (sal < 33333) {
            taxValue = (taxable - 20833) * 0.2f;
        } else if (sal < 66667) {
            taxValue = (taxable - 33333) * 0.25f + 2500;
        } else if (sal < 166667) {
            taxValue = (taxable - 66667) * 0.3f + 10833;
        } else if (sal < 666667) {
            taxValue = (taxable - 166667) * 0.32f + 40833.33f;
        } else {
            taxValue = (taxable - 666667) * 0.35f + 200833.33f;
        }
        
        return String.format("%.2f", taxValue);
    }
    
    public void generatePayrollSummary() {
        // Logic to generate payroll summary
    }
    
    public static Payslip loadPayslip(String csvFilename, String username) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilename))) {
            String[] header = reader.readNext();
            if (header == null) {
                System.out.println("CSV file is empty.");
                return null;
            }
            
            String[] line;
            while ((line = reader.readNext()) != null) {
                int id = Integer.parseInt(line[0]);
                String employeeUsername = line[2];
                
                if (username.equals(employeeUsername)) {
                    double monthlyRate = Double.parseDouble(line[13]);
                    double riceSubsidy = Double.parseDouble(line[14]);
                    double phoneAllowance = Double.parseDouble(line[15]);
                
                    Payslip payslip = new Payslip();
                    payslip.setMonthlyRate(monthlyRate);
                    payslip.setRiceSubsidy(riceSubsidy);
                    payslip.setPhoneAllowance(phoneAllowance);
                    
                    
                    return payslip;
                }
            }
            System.out.println("No matching username found: " + username);
            return null;
        } catch (IOException | CsvValidationException | NumberFormatException e) {
            return null;
        }
    } 
    
}
