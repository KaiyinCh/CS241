 //****************************************************************
 //Payroll.java

 //Represents a list of employees.
 //****************************************************************
import java.util.Scanner;
import java.util.*;
import java.io.*;
public class Payroll
{
    //here the GenericLinkedList is your generalized LinkedIntList
    //DO NOT USE java ready-made lists.
    PACKAGE_NAME.GenericLinkedList<Employee> payroll = new PACKAGE_NAME.GenericLinkedList<Employee>();
    int numEmployees = 0;
    // ---------------------------------------------------------
    // Reads the list of employee wage data from the given
    // file.
    // ---------------------------------------------------------
    public void readPayrollInfo(String file)
    {
        String line; // a line in the file
        String name; // name of an employee
        int hours; // hours worked
        double rate; // hourly pay rate
        Scanner fileScan, lineScan;
        try
        {
            fileScan = new Scanner (new File(file));
            while (fileScan.hasNext())
            {
                line = fileScan.nextLine();
                lineScan = new Scanner(line);
                name = lineScan.next ();
                try
                {
                    hours = lineScan.nextInt();
                    rate = lineScan.nextDouble();
                    payroll.add(numEmployees,new Employee (name, hours, rate));
                    numEmployees++;
                }
                catch (InputMismatchException exception)
                {
                    System.out.println ("Error in input. Line ignored.");
                    System.out.println (line);
                }
                catch (ArrayIndexOutOfBoundsException exception)
                {
                    System.out.println ("Too many employees!");
                }
            }
                fileScan.close();

            }
            catch (FileNotFoundException exception)
            {
                System.out.println ("The file " + file + " was not found.");
            }
        }
        // ------------------------------------------
        // Returns the number of employees who
        // worked over 40 hours; the helper method
        // overtime is called to do all the work.
        // ------------------------------------------
        public int numOvertime ()
        {
            return overtime (0);
        }
        // ------------------------------------------------
        // Returns the number of employees in the part
        // of the list from index start to the end who
        // worked more than 40 hours.
        // ------------------------------------------------

        private int overtime (int start)
        {
            int count = 0;
            if (start >= 0 && start < numEmployees && payroll.getByIndex(start) != null){
                Employee L = payroll.getByIndex(start);
                if (payroll.getByIndex(start).getHours() > 40){
                    count++;
                }
                count += overtime(start+1);
            }
            return count;
        }

    }
