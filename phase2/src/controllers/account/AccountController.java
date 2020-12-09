package controllers.account;

import enums.AccountTypeEnum;
import enums.ViewEnum;
import gateways.*;
import use_cases.ConversationManager;
import use_cases.RequestManager;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;
import use_cases.event.LocationManager;
import views.View;
import views.ViewFactory;

import java.util.ArrayList;
import java.util.Set;

public class AccountController {
    private final DataManager dm;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final LocationManager lm;
    private final RequestManager rm;
    private final String username;

    public AccountController(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.lm = dm.getLocationManager();
        this.rm = dm.getRequestManager();
        this.username = dm.getUsername();
    }

    public AccountTypeEnum getAccountType() {
        if (am.containsAttendee(username)) {
            return AccountTypeEnum.ATTENDEE;
        } else if (am.containsSpeaker(username)) {
            return AccountTypeEnum.SPEAKER;
        } else {
            return AccountTypeEnum.ORGANIZER;
        }
    }

    public boolean usernameExists(String username) {
        return am.containsAccount(username);
    }

    public Set<String> getAccountList() {
        return dm.getAccountManager().getAccountHashMap().keySet();
    }

    public ArrayList<String> getContactList() {
        return fm.getFriendList(username);
    }

    public View getView(ViewEnum viewEnum) {
        ViewFactory viewFactory = new ViewFactory(dm);
        return viewFactory.getView(viewEnum);
    }

    public void saveData() {
        AccountDataManager accountDataManager = new AccountDataManager();
        ContactDataManager contactDataManager = new ContactDataManager();
        ConversationDataManager conversationDataManager = new ConversationDataManager();
        EventDataManager eventDataManager = new EventDataManager();
        LocationDataManager locationDataManager = new LocationDataManager();
        RequestDataManager requestDataManager = new RequestDataManager();

        accountDataManager.saveManager("AccountManager", am);
        contactDataManager.saveManager("ContactManager", fm);
        conversationDataManager.saveManager("ConversationManager", cm);
        eventDataManager.saveManager("EventManager", em);
        locationDataManager.saveManager("LocationManager", lm);
        requestDataManager.saveManager("RequestManager", rm);
    }
}
