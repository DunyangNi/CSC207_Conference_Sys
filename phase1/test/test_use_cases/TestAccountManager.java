package test_use_cases;

import entities.Account;
import entities.Attendee;
import entities.Organizer;
import entities.Speaker;
import org.junit.Test;
import use_cases.AccountManager;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TestAccountManager {
    AccountManager accountmanager;

    @Test(timeout = 50)
    public void test_initial_list_empty() {
        accountmanager = new AccountManager(
                new HashMap<String, Attendee>(), new HashMap<String, Organizer>(), new HashMap<String, Speaker>());

        assert(accountmanager.getAttendeelist().isEmpty());
        assert(accountmanager.getOrganizerList().isEmpty());
        assert(accountmanager.getSpeakerlist().isEmpty());

        assertFalse(accountmanager.containsAttendee("lbj23"));
        assertFalse(accountmanager.containsOrganizer("kb24"));
        assertFalse(accountmanager.containsSpeaker("mj23"));

        assertNull(accountmanager.fetchAttendee("lbj123"));
        assertNull(accountmanager.fetchOrganizer("kb24"));
        assertNull(accountmanager.fetchSpeaker("mj23"));
    }

    @Test(timeout = 50)
    public void add_list_successful(){
        accountmanager = new AccountManager(
                new HashMap<String, Attendee>(), new HashMap<String, Organizer>(), new HashMap<String, Speaker>());

        accountmanager.AddNewAttendee("lbj123", "king", "Lebron","James");
        assert(accountmanager.containsAttendee("lbj123"));
        assertNotNull(accountmanager.fetchAttendee("lbj123"));
        assert(accountmanager.fetchAttendee("lbj123").equals(new Account
                ("lbj123", "king", "Lebron","James")));

        accountmanager.AddNewOrganizer("kb24", "mamba", "Kobe","Bryant");
        assert(accountmanager.containsOrganizer("kb24"));
        assert(accountmanager.fetchOrganizer("kb24").equals(new Account
                ("kb24", "mamba", "Kobe","Bryant")));

        accountmanager.AddNewSpeaker("mj23", "goat", "Michael","Jordan");
        assert(accountmanager.containsSpeaker("mj23"));
        assert(accountmanager.fetchSpeaker("mj23").equals(new Account
                ("mj23", "goat", "Michael","Jordan")));

    }

    @Test(timeout = 50)
    public void add_list_violation(){
        accountmanager = new AccountManager(
                new HashMap<String, Attendee>(), new HashMap<String, Organizer>(), new HashMap<String, Speaker>());
        accountmanager.AddNewAttendee("lbj123", "king", "Lebron","James");
        accountmanager.AddNewOrganizer("kb24", "mamba", "Kobe","Bryant");
        accountmanager.AddNewSpeaker("mj23", "goat", "Michael","Jordan");
        //We get an accountmanager containing all the usernames//

        accountmanager.AddNewAttendee("lbj123", "bruh", "Bruh","Bruh");
        assert(accountmanager.fetchAttendee("lbj123").equals(new Account
                ("lbj123", "king", "Lebron","James")));

        //No changes should be made//
        //All accounts in all list must have distinct usernames//

        accountmanager.AddNewSpeaker("lbj123", "bruh", "Bruh","Bruh");
        assertFalse(accountmanager.getSpeakerlist().containsKey("lbj123"));
        assertNull(accountmanager.fetchSpeaker("lbj123"));
        assert(accountmanager.fetchSpeaker("mj23").equals(new Account
                ("mj23", "goat", "Michael","Jordan")));

        //No changes should be made//
        //All accounts in all list must have distinct usernames//

        accountmanager.AddNewOrganizer("lbj123", "bruh", "Bruh","Bruh");
        assertFalse(accountmanager.getOrganizerList().containsKey("lbj123"));
        assertNull(accountmanager.fetchOrganizer("lbj123"));
        assert(accountmanager.fetchOrganizer("kb24").equals(new Account
                ("kb24", "mamba", "Kobe","Bryant")));

        //Currently, this test will fail until a tweak is made to AccountManager: all accounts
        // in all lists must have distinct usernames.
    }

    @Test(timeout = 50)
    public void change_account_info(){
        accountmanager = new AccountManager(
                new HashMap<String, Attendee>(), new HashMap<String, Organizer>(), new HashMap<String, Speaker>());

        accountmanager.AddNewAttendee("lbj123", "king", "Lebron","James");
        Attendee lbj = accountmanager.fetchAttendee("lbj123");

        accountmanager.ChangeFirstName(lbj,"LeBron");
        accountmanager.ChangeLastName(lbj,"LeBeast");
        accountmanager.ChangePassword(lbj,"cleveland");

        assert(lbj.getFirstName().equals("LeBron"));
        assert(lbj.getLastName().equals("LeBeast"));
        assert(lbj.getPassword().equals("cleveland"));

    }
}
