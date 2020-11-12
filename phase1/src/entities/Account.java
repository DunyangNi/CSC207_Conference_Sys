package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Account implements Serializable {
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private ArrayList<String> friendsList = new ArrayList<>(); // added by Lucas
    private ArrayList<Integer> conversations = new ArrayList<>(); // added by Lucas
    private ArrayList<Integer> attendeeTalks = new ArrayList<>(); // added by Lucas

    public Account(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Account) && ((Account) o).getUsername().equals(this.getUsername());
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

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public ArrayList<Integer> getConversations() {
        return conversations;
    }

    public ArrayList<Integer> getAttendeeTalks() {
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

    public void setFriendsList(ArrayList<String> friendsList) {
        this.friendsList = friendsList;
    }

    public void setConversations(ArrayList<Integer> conversations) {
        this.conversations = conversations;
    }

    public void setAttendeeTalks(ArrayList<Integer> attendeeTalks) {
        this.attendeeTalks = attendeeTalks;
    }
}
