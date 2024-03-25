
# MotorPH Payroll System Documentation

<br/>

This guide is your go-to resource for understanding and navigating our innovative payroll solution.


  
<br/> <br/>

<hr> <!-- Horizontal rule for separation -->



<br/>


  
### *H2101* GROUP 6


 
<br/>
  
***Aldrich Rosh Rondina***

***Arabella Pabular***

***Patricia Marie Panganiban***



<br/> <br/> <br/> <br/>

<hr> <!-- Horizontal rule for separation -->


## 1. Introduction

This user guide serves as a comprehensive resource for the MotorPH Payroll System. It's designed to be informative and user-friendly for all employees within the organization who have been granted access to manage payroll-related tasks. It automates calculations, manages deductions, and generates payslips, ensuring efficient and accurate compensation for all employees.


## 1.1 User Roles
  
### 🧑‍💼 Employee 
- Employees with access to the system can typically view their information, payslips, leave records, submit leave requests, time-in, and time-out for the attendance

### 📋 HR Department/HR Staff  
The HR department may have access to specific functionalities within the system, such as:
- Adding new employee profiles and maintaining existing ones.
- Approving leave requests submitted by employees
- Manage Employees’ Attendance Record

### 💰 Finance Department/Finance Staff
The Finance Department holds the most extensive permissions within the payroll system. Their responsibilities include:
- Processing payroll, including managing employee profiles, setting pay rates and allowances, and overseeing automated calculations.
- Generating payslips and ensuring their timely distribution to employees.
-  Overseeing tax calculations and deductions.
- Generating reports for accounting and payroll analysis.

### Navigating The User Guide
This user guide is tailored to cater to all MotorPH Payroll System employees. Here's a quick overview of what you'll find:

## 2. Getting Started
Before diving in, ensure you meet the following prerequisites:

### 2.1 Hardware Prerequisites 🛠️
- Strong Wi-Fi connection
- Minimum 4GB RAM
- AMD Ryzen 3 or equivalent

### 2.2 Software Prerequisites 💻
- GitHub & GitHub Pages
- NetBeans IDE
- Operating System: Windows 10 or 11 / MacOS 14

### 2.3 Accessing The System 🔒
- Employee User Account: To be able to access the system
- User Credentials: This is used to login into the system 
- Authorization and Access Controls

## 3. Using The Payroll System 
Discover how to make the most out of our system:

### 3.1 Secure Login Procedures 🔐
Ensure data security with our robust login procedures, including username and password authentication. Here’s what you need to do:
- **Login Credentials:** Upon accessing the system, the user will be prompted to enter their unique username and password. The system administrator assigns these credentials and should be kept confidential.
- **Role-Based Access Control (RBAC):** Once the user’s credentials are verified, the system automatically grants access based on the user designated role (Employee, Finance, HR). This ensures users can only access functionalities relevant to their responsibilities.

### 3.2 Salary and Deduction Calculations 💰
The system automates salary calculations based on pre-defined parameters within the employee profile. The Finance Staff utilizes the payroll system to automate salary calculations and deductions for all employees.
- **Employee Profile:** The system relies on pre-defined data within each employee's profile. This includes factors such as:
  - Basic Salary: Fixed monthly or bi-weekly amount.
  - Pay Rate: Hourly rate (if applicable).
  - Overtime Hours: Additional hours worked beyond the standard schedule (if approved by the HR Department).
  - Allowances: Fixed or variable amounts for phone and rice subsidy.
- **Automated Calculations:** The system automatically calculates gross pay based on the factors mentioned above
- **Deductions:** Pre-defined deduction rules are applied to the gross pay to determine the net pay. Common deductions include:
  - Tax
  - Benefit Contributions (PhilHealth, SSS, Pag-IBIG)

  Once calculations are complete, the system generates payslips for each employee. These payslips detail the breakdown of earnings and deductions, providing transparency into their net pay. Employees typically receive their payslips electronically distributed by the Finance Department.

### 3.3 Leave Applications 🏖️
The payroll system streamlines leave management by allowing employees to submit leave requests electronically. Here's a general overview of the process:
- **Employee Leave Request:** Employees can access the leave section within the system. This section will typically display the employee’s leave request records, leave form, and leave balance. In applying for a request, Employees will be required to specify the details such as leave type, leave duration, and reason for leave that briefly explain the reason for the leave request.  Once all details are entered, submit the leave request electronically. The system may automatically route the request to the designated approver (typically the line manager or HR department).
  - Employees able to track the status of their leave requests within the system. Leave balances is displayed within the system, allowing employees to monitor their remaining leave entitlement.

- **HR Leave Approval:** The HR department (or designated approver) will receive a notification regarding the submitted leave request.
    - Review and Approval: The approver can access the leave request details, including the employee's profile, leave type, duration, and reason (if provided). Based on departmental needs and staffing requirements, the approver can then:
      - Approve: Grant permission for the leave request.
      - Reject: Deny the request.
    - Employee Notification: The system will automatically notify the employee regarding the approval or rejection status of their leave request. This ensures clear communication and avoids any confusion.


### 3.4 Employee Data Management 📋
Employee management in a payroll system involves the comprehensive organization and oversight of employee-related data and processes within an organization. The HR department is responsible for managing employee-related tasks. 
- **Adding a New Employee:** Within the Employee Management module, find the option for adding a new employee. This labeled as "Add Employee,"  
- **Updating Employee Information:** Navigate to the employee's existing record within the Employee Management module. This might involve searching by name or employee ID. Once clicked, the system will display the employee's current information. HR personnel can locate and edit any fields requiring an update. After making changes, save the updates using the designated button “Update”. This update will reflect on the Employee’s Profile Module.  
- **Deleting Employee Information (Important Note: Deleting employee data should be done with caution):** Locate the employee's record using search functions or by clicking it on the Employee table. The system has a dedicated button or option for “Delete Employee”. The system will prompt for confirmation before proceeding with deletion.


### 3.5 Time Entries ⏰
Time Entries in a payroll system refer to the records of when an employee starts and ends their work shift, commonly known as clocking in and out. 
- **Clocking In:** Locate the "Time In" button or function on the top right of the dashboard. Once successful, the system should display a confirmation message indicating you are clocked in.
- **Clocking Out:** Click on the option to clock out, typically labeled "Time out". This records the end time and it will reflect on the attendance record.

## 4. Technical Information

### 4.1 Use Case Diagram
![Payroll System Use Case  (2)](https://github.com/arrondina/MotorPH-Payroll-System/assets/124907667/7391ca9e-0d20-41d5-9fa2-b236fcfbab34)

### 4.2 Class Diagram
![UML class (10)](https://github.com/arrondina/MotorPH-Payroll-System/assets/124907667/12c0e2cf-c8c8-4fa3-b821-dd8211992abd)

### 4.3 Testing
