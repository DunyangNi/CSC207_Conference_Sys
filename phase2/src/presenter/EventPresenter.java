package presenter;

public class EventPresenter implements ConsolePresenter {
    @Override
    public void startPrompt() {
    }


    public void eventCreationPrompt(String field) {
        switch (field) {
            case "topic":
                System.out.println("Enter the event topic:");
                break;
            case "speaker":
                System.out.println("Enter the speaker's username:");
                break;
            case "room":
                System.out.println("Enter the event room:");
                break;
            case "capacity":
                System.out.println("Hour of the day (9-16)");
                break;
            case "vip":
                System.out.println("0 = Standard");
                System.out.println("0 = VIP");
                break;
        }
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

    public void eventIdPrompt() {
        System.out.println("Please enter the ID of a talk you wish to modify: ");
    }

    @Override
    public void exitPrompt() {
    }
}