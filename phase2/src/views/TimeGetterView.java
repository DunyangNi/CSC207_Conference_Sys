package views;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TimeGetterView {
    /**
     * A helper method to collect standart time information
     * @return returns the specified time
     * @throws InstantiationException error instantiating the time
     */
    protected Calendar collectTimeInfo() throws InstantiationException, InputMismatchException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Day of the month (1-31)");
        int dayOfMonth = sc.nextInt();
        sc.nextLine();
        System.out.println("Month (1-12)");
        int month = sc.nextInt()-1;
        sc.nextLine();
        System.out.println("Year (YYYY)");
        int year = sc.nextInt();
        sc.nextLine();
        System.out.println("Hour of the day (9-16)");
        int hourOfDay = sc.nextInt();
        sc.nextLine();
        Calendar time = Calendar.getInstance();
        try{
            time.set(year, month, dayOfMonth, hourOfDay, 0, 0);
            time.clear(Calendar.MILLISECOND);}
        catch (Exception e) {
            throw new InstantiationException();
        }
        return time;


    }
}
