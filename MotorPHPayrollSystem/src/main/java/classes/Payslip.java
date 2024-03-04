/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import static java.lang.String.format;
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
    
    public String computeSSS(double monthlyRate) {
        double sss;
        
        if(monthlyRate <= 3250) {
            sss = 135;
        } else if (monthlyRate <= 24750) {
            if(monthlyRate %1000 == 250 || monthlyRate % 1000 == 750 ) {
                float mod = (float) ((monthlyRate - 3250) % 500);
                float multiplier = (float) ((monthlyRate - 3250 - mod) / 500); 
                sss = 22.5 * (multiplier) + 135;
            } else {
                float mod = (float) ((monthlyRate - 3250) % 500); 
                float multiplier = (float) ((monthlyRate - 3250 - mod) / 500);              
                sss = 22.5 *(multiplier + 1) + 135;     
            }
        } else {
            sss = 1125;
        }
        
        return String.format("%.2f",sss);  
    }
    
    public String computePhilhealth(double monthlyRate) {
        double ph;
        
        if(monthlyRate <= 10000) {
            ph = 300/2;
        } else if (monthlyRate < 60000) {
            ph = monthlyRate * 0.03 / 2;
        } else {
            ph = 1800/2;
        }
        
        return String.format("%.2f",ph);
    }
    
    public String computePagibig(double monthlyRate) {
        double pagibig;
        
        if(monthlyRate > 1000 && monthlyRate <= 1500) { //if-else statement checks the range of salary and applies formula in each rang
            pagibig = monthlyRate * 0.01;
        } else {
            pagibig = Math.min(monthlyRate * 0.02, 100);
        }
        
        return format("%.2f",pagibig);  
    }
    
    public String computeTax() {
        float tax;
        float sal = (float) monthlyRate;
        float sss = Float.parseFloat(computeSSS(monthlyRate));
        float ph = Float.parseFloat(computePhilhealth(monthlyRate));
        float pagibig = Float.parseFloat(computePagibig(monthlyRate));
        
        float taxable = sal - sss - pagibig - ph;
        
        if (sal <= 20832) {
            tax = 0;
        } else if (sal < 33333) {
            tax = (taxable - 20833) * 0.2f;
        } else if (sal < 66667) {
            tax = (taxable - 33333) * 0.25f + 2500;
        } else if (sal < 166667) {
            tax = (taxable - 66667) * 0.3f + 10833;
        } else if (sal < 666667) {
            tax = (taxable - 166667) * 0.32f + 40833.33f;
        } else {
            tax = (taxable - 666667) * 0.35f + 200833.33f;
        }
        
        return String.format("%.2f", tax);
    }
}
