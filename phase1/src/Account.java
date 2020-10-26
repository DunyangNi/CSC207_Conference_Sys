import java.util.ArrayList;
import java.util.HashMap;

public class Account{

    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private HashMap<String, Account> friendsList;
    private HashMap<String, Conversation> conversations;
    private ArrayList<EventTalk> attendeeTalks;

    public Account(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public HashMap<String, Account> getFriendsList() {
        return friendsList;
    }

    public HashMap<String, Conversation> getConversations() {
        return conversations;
    }

    public ArrayList<EventTalk> getAttendeeTalks() {
        return attendeeTalks;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFriendsList(HashMap<String, Account> friendsList) {
        this.friendsList = friendsList;
    }

    public void setConversations(HashMap<String, Conversation> conversations) {
        this.conversations = conversations;
    }

    public void setAttendeeTalks(ArrayList<EventTalk> attendeeTalks) {
        this.attendeeTalks = attendeeTalks;
    }
}
