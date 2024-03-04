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
import static java.lang.String.format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rosh
 */
public class Payroll extends Employee {
    
    
    public float computeHoursWorked(String startDate, String endDate) throws FileNotFoundException, IOException, CsvValidationException, ParseException {
        
        SimpleDateFormat format1 = new SimpleDateFormat("M/d/yyyy HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("M/d/yyyy");
        
        Date dateIn = format2.parse(startDate);
        Date dateOut = format2.parse(endDate);
        
        try(CSVReader csvreader = new CSVReader(new FileReader("C:\\Users\\Anna\\Downloads\\attendanceRecord.csv"))) {
            String[] line;
            ArrayList<String> timein = new ArrayList<>();
            ArrayList<String> timeout = new ArrayList<>();
            
            while((line=csvreader.readNext())!=null) {
                if(line[0].equals(employeeID)) {
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
}
