package use_cases;

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
    HashMap<String, ArrayList<String>> friends;

    /**
     * Creates a <code>ContactManager</code> with a given <code>HashMap</code>
     * of <code>Account</code> to <code>ArrayList</code> of friends.
     *
     * @param friends given <code>HashMap</code> of Account to ArrayList of friends.
     */
    public ContactManager(HashMap<String, ArrayList<String>> friends) { this.friends = friends; }

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
    public ArrayList<String> getFriendList(String user) { return friends.get(user); }

    /**
     * Adds a new key a username of an associated <code>Account</code>.
     *
     * @param user given username of associated <code>Account</code>
     */
    public void addAccountKey(String user) { friends.put(user, new ArrayList<>()); }


    public void addFriend(String user, String friendToAdd) throws UserNotFoundException, FriendNotFoundException, AlreadyFriendException {
        if (!friends.containsKey(user))
            throw new UserNotFoundException();
        if (!friends.containsKey(friendToAdd))
            throw new FriendNotFoundException();
        if (friends.get(user).contains(friendToAdd))
            throw new AlreadyFriendException();
        friends.get(user).add(friendToAdd);
    }

    /**
     * Attempts to remove a friend
     * @param user given username of User
     * @param friendToRemove given username of friend
     * @throws ObjectNotFoundException upon friend not being in User's friends
     */
    public void removeFriend(String user, String friendToRemove) throws UserNotFoundException, FriendNotFoundException {
        if (!friends.containsKey(user))
            throw new UserNotFoundException();
        if (!friends.containsKey(friendToRemove))
            throw new FriendNotFoundException();
        friends.get(user).remove(friendToRemove);
    }
}
