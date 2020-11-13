package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private ArrayList<String> friendsList = new ArrayList<>(); // added by Lucas

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

