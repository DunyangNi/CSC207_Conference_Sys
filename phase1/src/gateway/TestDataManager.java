package gateway;

import java.io.File;
import java.util.Calendar;
import entities.Organizer;
import entities.Speaker;
import use_cases.AccountManager;
import use_cases.EventManager;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestDataManager {
    static String EMPTY_ACCOUNT_MANAGER_PATH; static String EMPTY_EVENT_MANAGER_PATH;
    static String TEST1_ACCOUNT_MANAGER_PATH; static String TEST1_EVENT_MANAGER_PATH;
    static String TEST2_ACCOUNT_MANAGER_PATH; static String TEST2_EVENT_MANAGER_PATH;
    static String TEST3_ACCOUNT_MANAGER_PATH; static String TEST3_EVENT_MANAGER_PATH;
    static File empty_am; static File empty_em;
    static File test1_am; static File test1_em;
    static File test2_am; static File test2_em;
    static File test3_am; static File test3_em;

    @BeforeClass
    public static void setup() throws Exception {
        // path setup
        EMPTY_ACCOUNT_MANAGER_PATH = "phase1/src/gateway/EmptyAccountDatabase.ser";
        EMPTY_EVENT_MANAGER_PATH = "phase1/src/gateway/EmptyEventDatabase.ser";
        TEST1_ACCOUNT_MANAGER_PATH = "phase1/src/gateway/Test1AccountDatabase.ser";
        TEST1_EVENT_MANAGER_PATH = "phase1/src/gateway/Test1EventDatabase.ser";
        TEST2_ACCOUNT_MANAGER_PATH = "phase1/src/gateway/Test2AccountDatabase.ser";
        TEST2_EVENT_MANAGER_PATH = "phase1/src/gateway/Test2EventDatabase.ser";
        TEST3_ACCOUNT_MANAGER_PATH = "phase1/src/gateway/Test3AccountDatabase.ser";
        TEST3_EVENT_MANAGER_PATH = "phase1/src/gateway/Test3EventDatabase.ser";
        // file setup
        empty_am = new File(EMPTY_ACCOUNT_MANAGER_PATH); empty_am.delete(); empty_am.createNewFile();
        empty_em = new File(EMPTY_EVENT_MANAGER_PATH); empty_em.delete(); empty_em.createNewFile();
        test1_am = new File(TEST1_ACCOUNT_MANAGER_PATH); test1_am.delete(); test1_am.createNewFile();
        test1_em = new File(TEST1_EVENT_MANAGER_PATH); test1_em.delete(); test1_em.createNewFile();
        test2_am = new File(TEST2_ACCOUNT_MANAGER_PATH); test2_am.delete(); test2_am.createNewFile();
        test2_em = new File(TEST2_EVENT_MANAGER_PATH); test2_em.delete(); test2_em.createNewFile();
        test3_am = new File(TEST3_ACCOUNT_MANAGER_PATH); test3_am.delete(); test3_am.createNewFile();
        test3_em = new File(TEST3_EVENT_MANAGER_PATH); test3_em.delete(); test3_em.createNewFile();
    }

    @Test(timeout = 1000)
    public void test_EmptyFile() {
        // construct empty objects from empty file, serialize it to file
        DataManager dm1 = new DataManager(EMPTY_ACCOUNT_MANAGER_PATH, EMPTY_EVENT_MANAGER_PATH);
        EventManager em1 = dm1.readEventManager();
        AccountManager am1 = dm1.readAccountManager();
        dm1.saveAccountManager(am1);
        dm1.saveEventManager(em1);
        // deserialize from file into new object
        DataManager dm2 = new DataManager(EMPTY_ACCOUNT_MANAGER_PATH, EMPTY_EVENT_MANAGER_PATH);
        EventManager em2 = dm2.readEventManager();
        AccountManager am2 = dm2.readAccountManager();
        // compare two objects
        boolean res1 = em2.equals(em1);
        boolean res2 = am2.equals(am1);
        assertTrue(res1);
        assertTrue(res2);
    }

    @Test(timeout = 1000)
    public void test_EventManager() {
        // construct empty objects from empty file
        DataManager dm1 = new DataManager(TEST1_ACCOUNT_MANAGER_PATH, TEST1_EVENT_MANAGER_PATH);
        EventManager em1 = dm1.readEventManager();
        AccountManager am1 = dm1.readAccountManager();
        // construct event parameters
        Organizer o1 = new Organizer("rizahawkeye", "guardian6", "Riza", "Hawkeye");
        Organizer o2 = new Organizer("abarairenji", "redpineapple8", "Renji", "Abarai");
        Speaker s1 = new Speaker("kirishima17", "rabbit997", "Touka", "Kirishima");
        Calendar c0 = Calendar.getInstance(); c0.set(2009, Calendar.APRIL, 7, 13, 35);
        Calendar c1 = Calendar.getInstance(); c1.set(2009, Calendar.APRIL, 7, 9, 15);
        em1.addLocation("auditorium");
        em1.addLocation("spiritroom7");
        // add events to EventManager
        em1.AddNewEvent("Sniping", c0, "auditorium", o1);
        em1.AddNewEvent("Soul Reaping", c1, "spiritroom7", o2, s1);
        // serialize changes into file
        dm1.saveAccountManager(am1);
        dm1.saveEventManager(em1);
        // deserialize from file into new object
        DataManager dm2 = new DataManager(TEST1_ACCOUNT_MANAGER_PATH, TEST1_EVENT_MANAGER_PATH);
        EventManager em2 = dm2.readEventManager();
        AccountManager am2 = dm2.readAccountManager();
        // compare two objects
        boolean res1 = em2.equals(em1);
        boolean res2 = am2.equals(am1);
        assertTrue(res1);
        assertTrue(res2);
    }

    @Test(timeout = 1000)
    public void test_AccountManager() {
        // construct empty objects from empty file
        DataManager dm1 = new DataManager(TEST2_ACCOUNT_MANAGER_PATH, TEST2_EVENT_MANAGER_PATH);
        EventManager em1 = dm1.readEventManager();
        AccountManager am1 = dm1.readAccountManager();
        // add accounts to AccountManager
        am1.AddNewOrganizer("thereaper3", "tententacles10", "Koro", "Sensei");
        am1.AddNewSpeaker("profmichaelis", "aGVsbG9mYWJ1dGxlcg==", "Sebastian", "Michaelis");
        am1.AddNewAttendee("byakuya31", "kuchiki286", "Byakuya", "Kuchiki");
        // serialize changes into file
        dm1.saveAccountManager(am1);
        dm1.saveEventManager(em1);
        // deserialize from file into new object
        DataManager dm2 = new DataManager(TEST2_ACCOUNT_MANAGER_PATH, TEST2_EVENT_MANAGER_PATH);
        EventManager em2 = dm2.readEventManager();
        AccountManager am2 = dm2.readAccountManager();
        // compare two objects
        boolean res1 = em2.equals(em1);
        boolean res2 = am2.equals(am1);
        assertTrue(res1);
        assertTrue(res2);
    }

    @Test//(timeout = 1000)
    public void test_Mixed() {
        // construct empty objects from empty file
        DataManager dm1 = new DataManager(TEST3_ACCOUNT_MANAGER_PATH, TEST3_EVENT_MANAGER_PATH);
        EventManager em1 = dm1.readEventManager();
        AccountManager am1 = dm1.readAccountManager();
        // add accounts to AccountManager
        am1.AddNewOrganizer("lizardon006", "immortalflame", "Gigantamax", "Charizard");
        am1.AddNewOrganizer("silverbullet", "caseclosed7", "Conan", "Edogawa");
        am1.AddNewSpeaker("edelric", "fullmetalalchemist", "Edward", "Elric");
        am1.AddNewAttendee("pirateking15", "strawhatluffy", "Monkey D.", "Luffy");
        am1.AddNewAttendee("kirito", "theblackswordsman77", "Kazuto", "Kirigaya");
        // construct event parameters
        Calendar c0 = Calendar.getInstance(); c0.set(2009, Calendar.APRIL, 7, 13, 35);
        Calendar c1 = Calendar.getInstance(); c1.set(2009, Calendar.APRIL, 7, 9, 15);
        Calendar c2 = Calendar.getInstance(); c2.set(2009, Calendar.APRIL, 7, 17, 15);
        em1.addLocation("auditorium");
        em1.addLocation("classroomA54");
        // add events to EventManager
        em1.AddNewEvent("Law Of Equivalent Exchange", c0, "auditorium",
                am1.fetchOrganizer("lizardon006"), am1.fetchSpeaker("edelric"));
        em1.AddNewEvent("Logical Deduction", c1, "classroomA54",
                am1.fetchOrganizer("silverbullet"));
        em1.AddNewEvent("Fire-breathing Exercises", c2, "auditorium",
                am1.fetchOrganizer("lizardon006"));
        // serialize changes into file
        dm1.saveAccountManager(am1);
        dm1.saveEventManager(em1);
        // deserialize from file into new object
        DataManager dm2 = new DataManager(TEST3_ACCOUNT_MANAGER_PATH, TEST3_EVENT_MANAGER_PATH);
        EventManager em2 = dm2.readEventManager();
        AccountManager am2 = dm2.readAccountManager();
        // compare two objects
        boolean res1 = em2.equals(em1);
        boolean res2 = am2.equals(am1);
        assertTrue(res1);
        assertTrue(res2);
    }
}
