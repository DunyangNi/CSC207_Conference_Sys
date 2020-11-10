package gateway;

import java.io.File;
import use_cases.AccountManager;
import use_cases.EventManager;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestDataManager {
    static String EMPTY_ACCOUNT_MANAGER_PATH;
    static String EMPTY_EVENT_MANAGER_PATH;
    static String TEST1_ACCOUNT_MANAGER_PATH;
    static String TEST1_EVENT_MANAGER_PATH;
    static String TEST2_ACCOUNT_MANAGER_PATH;
    static String TEST2_EVENT_MANAGER_PATH;
    static String TEST3_ACCOUNT_MANAGER_PATH;
    static String TEST3_EVENT_MANAGER_PATH;

    static File empty_am;
    static File empty_em;

    @BeforeClass
    public static void setup() throws Exception {
        // path setup
        EMPTY_ACCOUNT_MANAGER_PATH = "phase1/src/gateway/EmptyAccountDatabase.ser";
        EMPTY_EVENT_MANAGER_PATH = "phase1/src/gateway/EmptyEventDatabase.ser";
        TEST1_ACCOUNT_MANAGER_PATH = "";
        TEST1_EVENT_MANAGER_PATH = "";
        TEST2_ACCOUNT_MANAGER_PATH = "";
        TEST2_EVENT_MANAGER_PATH = "";
        TEST3_ACCOUNT_MANAGER_PATH = "";
        TEST3_EVENT_MANAGER_PATH = "";
        // file setup
        empty_am = new File(EMPTY_ACCOUNT_MANAGER_PATH); empty_am.delete(); empty_am.createNewFile();
        empty_em = new File(EMPTY_EVENT_MANAGER_PATH); empty_em.delete(); empty_em.createNewFile();
    }

    @Test(timeout = 1000)
    public void test_EmptyFile() {
        // construct empty objects from empty file, serialize it to file
        DataManager dm1 = new DataManager(EMPTY_ACCOUNT_MANAGER_PATH, EMPTY_EVENT_MANAGER_PATH);
        EventManager em1 = dm1.readEventManager();
        AccountManager am1 = dm1.readAccountManager();
        dm1.saveAccountManager(am1);
        dm1.saveEventManager(em1);
        // deserialize into new object
        DataManager dm2 = new DataManager(EMPTY_ACCOUNT_MANAGER_PATH, EMPTY_EVENT_MANAGER_PATH);
        EventManager em2 = dm2.readEventManager();
        AccountManager am2 = dm2.readAccountManager();
        // compare two objects
        boolean res1 = em2.equals(em1);
        boolean res2 = am2.equals(am1);
        assertTrue(res1);
        assertTrue(res2);
    }

    @Test(timeout = 50)
    public void test_EventManager() {
        // tba
        // solo EventManager related changes
    }

    @Test(timeout = 50)
    public void test_AccountManager() {
        // tba
        // solo AccountManager related changes
    }

    @Test(timeout = 50)
    public void test_Mixed() {
        // tba
        // scenario involving both AccountManager and EventManager related changes
    }

}
