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
    private final HashMap<String, ArrayList<String>> contactLists;

    /**
     * Creates a <code>ContactManager</code> with a given <code>HashMap</code>
     * of <code>Account</code> to <code>ArrayList</code> of friends.
     *
     * @param contactLists given <code>HashMap</code> of Account to ArrayList of friends.
     */
    public ContactManager(HashMap<String, ArrayList<String>> contactLists) {
        this.contactLists = contactLists;
    }

    /**
     * Creates a <code>ContactManager</code> with an empty <code>HashMap</code>.
     */
    public ContactManager() {
        this(new HashMap<>());
    }

    /**
     * Returns an <code>ArrayList</code> of usernames of Accounts that are friends with user.
     *
     * @param user given username
     * @return <code>ArrayList</code> of usernames of Accounts that are friends with user.
     */
    public ArrayList<String> getFriendList(String user) {
        return contactLists.get(user);
    }

    /**
     * Adds a new key a user of an associated <code>Account</code>.
     *
     * @param user given user of associated <code>Account</code>
     */
    public void addAccountKey(String user) {
        contactLists.put(user, new ArrayList<>());
    }

    public void addFriend(String currentUser, String friendToAdd) throws UserNotFoundException, FriendNotFoundException, AlreadyFriendException {
        if (!contactLists.containsKey(currentUser))
            throw new UserNotFoundException();
        if (!contactLists.containsKey(friendToAdd))
            throw new FriendNotFoundException();
        if (contactLists.get(currentUser).contains(friendToAdd))
            throw new AlreadyFriendException();
        contactLists.get(currentUser).add(friendToAdd);
    }

    /**
     * Attempts to remove a friend
     * @param user given username of User
     * @param friendToRemove given username of friend
     * @throws ObjectNotFoundException upon friend not being in User's friends
     */
    public void removeFriend(String user, String friendToRemove) throws UserNotFoundException, FriendNotFoundException {
        if (!contactLists.containsKey(user))
            throw new UserNotFoundException();
        if (!contactLists.containsKey(friendToRemove))
            throw new FriendNotFoundException();
        contactLists.get(user).remove(friendToRemove);
    }
}
