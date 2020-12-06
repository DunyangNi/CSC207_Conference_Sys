package presenters.account;

import presenters.Presenter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

public class AccountPresenter implements Presenter {
    @Override
    public void startPrompt() {
        System.out.println("Logged in}");
        System.out.println();
    }

    public void invalidInputPrompt() {
        System.out.println("{Invalid input}");
    }

    public void requestCommandPrompt() {
        System.out.println("Enter another command (1-16) or '*' to view the command menu again.");
    }

    public void accountList(Set<String> accounts) {
        System.out.println("[USER LIST]");
        System.out.println("===================================");
        for (String acct : accounts) {
            System.out.println(acct);
        }
        System.out.println("===================================");
    }

    // TODO: 12/06/20 Cut these Talk methods into EventPresenter, call from EventView
    public void displayTalkSchedule(HashMap<String[], Calendar> allTalks) {
        System.out.println("Schedule for all talks:\n");
        displayTalks(allTalks);
    }

    private void displayTalks(HashMap<String[], Calendar> allTalks) {
        if (allTalks.keySet().isEmpty()) {
            System.out.println("Nothing!");
        }
        Calendar timeNow = Calendar.getInstance();
        for(String[] eventInfo : allTalks.keySet()) {
            if(timeNow.compareTo(allTalks.get(eventInfo)) < 0) {
                displayTalkInfo(eventInfo);
            }
        }
    }

    private void displayTalkInfo(String[] eventInfo) {
        System.out.println("ID: " + eventInfo[4]);
        System.out.println("Topic: " + eventInfo[0]);
        System.out.println("Speaker: " + eventInfo[1]);
        System.out.println("Location: " + eventInfo[2]);
        System.out.println("Time: " + eventInfo[3]);
        System.out.println();
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Logging out...");
    }
}
