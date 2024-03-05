/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.time.LocalDate;

/**
 *
 * @author Rosh
 */
public class Payslip {
    private int payrollID;
    private LocalDate startDate;
    private LocalDate endDate;
    private double monthlyRate;
    private double dailyRate;
    private double daysWorked;
    private double overtime;
    private double earnings;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double benefits;
    private double sss;
    private double philhealth;
    private double pagibig;
    private double tax;
    private double deductions;
    private double netPay;
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

    public double getEarnings() {
        return earnings;
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

    public double getBenefits() {
        return benefits;
    }

    public double getSss() {
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

    public double getDeductions() {
        return deductions;
    }
    
    public double getNetPay() {
        return netPay;
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

    public void setEarnings(double earnings) {
        this.earnings = earnings;
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

    public void setBenefits(double benefits) {
        this.benefits = benefits;
    }

    public void setSss(double sss) {
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

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }
    
    public void setNetPay(double netPay) {
        this.netPay = netPay;
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

    public void calculateSalary() {
        // Logic to calculate salary
    }

    public void generatePayrollSummary() {
        // Logic to generate payroll summary
    }
    
    public String computeSSS() {
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
    
    public String computePhilhealth() {
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
    
    public String computePagibig() {
        double pagibigValue;
        
        // Variable to store the computed Pagibig value
        if(monthlyRate > 1000 && monthlyRate <= 1500) {
            pagibigValue = monthlyRate * 0.01;
        } else {
            pagibigValue = Math.min(monthlyRate * 0.02, 100);
        }
        
        return String.format("%.2f",pagibigValue);  
    }
    
    public String computeTax() {
        double taxValue;
        double sal = monthlyRate;
        double sssValue = Double.parseDouble(computeSSS());
        double philhealthValue = Double.parseDouble(computePhilhealth());
        double pagibigValue = Double.parseDouble(computePagibig());
        
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
    
}
