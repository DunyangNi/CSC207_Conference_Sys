package use_cases.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.conflict.AlreadyFriendException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.UserNotFoundException;

/**
 * Represents the entire system of Friend relationships between <code>Accounts</code>.
 */
public class ContactManager implements Serializable {
    HashMap<String, ArrayList<String>> contacts;

    /**
     * Creates a <code>ContactManager</code> with a given <code>HashMap</code>
     * of <code>Account</code> to <code>ArrayList</code> of friends.
     *
     * @param contacts given <code>HashMap</code> of Account to ArrayList of friends.
     */
    public ContactManager(HashMap<String, ArrayList<String>> contacts) { this.contacts = contacts; }

    /**
     * Creates a <code>ContactManager</code> with an empty <code>HashMap</code>.
     */
    public ContactManager() { this(new HashMap<>()); }

    /**
     * Returns an <code>ArrayList</code> of usernames of Accounts that are friends with user.
     *
     * @param user given username
     * @return <code>ArrayList</code> of usernames of Accounts that are friends with user.
     */
    public ArrayList<String> getFriendList(String user) { return contacts.get(user); }

    /**
     * Adds a new key a username of an associated <code>Account</code>.
     *
     * @param user given username of associated <code>Account</code>
     */
    public void addAccountKey(String user) { contacts.put(user, new ArrayList<>()); }


    public void addFriend(String currentUser, String friendToAdd) throws UserNotFoundException, FriendNotFoundException, AlreadyFriendException {
        if (!contacts.containsKey(currentUser))
            throw new UserNotFoundException();
        if (!contacts.containsKey(friendToAdd))
            throw new FriendNotFoundException();
        if (contacts.get(currentUser).contains(friendToAdd))
            throw new AlreadyFriendException();
        contacts.get(currentUser).add(friendToAdd);
    }

    /**
     * Attempts to remove a friend
     * @param user given username of User
     * @param friendToRemove given username of friend
     * @throws ObjectNotFoundException upon friend not being in User's friends
     */
    public void removeFriend(String user, String friendToRemove) throws UserNotFoundException, FriendNotFoundException {
        if (!contacts.containsKey(user))
            throw new UserNotFoundException();
        if (!contacts.containsKey(friendToRemove))
            throw new FriendNotFoundException();
        contacts.get(user).remove(friendToRemove);
    }
}
