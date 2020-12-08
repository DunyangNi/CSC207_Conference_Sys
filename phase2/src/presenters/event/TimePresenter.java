package presenters.event;

import presenters.Presenter;

public class TimePresenter implements Presenter {
    @Override
    public void startPrompt() {
        System.out.println("Please enter the time.");
    }
    public void timePrompt(String field) {
        switch (field) {
            case "day":
                System.out.println("Day of the month (1-31)");
                break;
            case "month":
                System.out.println("Month (1-12)");
                break;
            case "year":
                System.out.println("Year (YYYY)");
                break;
            case "hour":
                System.out.println("Hour of the day (9-16)");
                break;
        }
    }
    public void errorPrompt(){System.out.println("This is not a valid time.");}

    @Override
    public void exitPrompt() {System.out.println("Successfully get the time.");}
}
