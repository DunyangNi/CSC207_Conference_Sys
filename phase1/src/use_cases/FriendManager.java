package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import Throwables.*;

/**
 * Represents the entire system of Friend relationships between <code>Accounts</code>.
 */
public class FriendManager implements Serializable {
    HashMap<String, ArrayList<String>> friends;

    /**
     * Creates a <code>FriendManager</code> with a given <code>HashMap</code>
     * of <code>Account</code> to <code>ArrayList</code> of friends.
     *
     * @param friends given <code>HashMap</code> of Account to ArrayList of friends.
     */
    public FriendManager(HashMap<String, ArrayList<String>> friends) { this.friends = friends; }

    /**
     * Creates a <code>FriendManager</code> with an empty <code>HashMap</code>.
     */
    public FriendManager() { this(new HashMap<>()); }

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

    /**
     * Attempts to add a friend into the user associated <code>ArrayList</code> of friends.
     *
     * @param user given username of User
     * @param friendToAdd given username of friend
     * @throws ObjectNotFoundException upon User or friend not being found
     * @throws ConflictException upon friend already in User's friends
     */
    public void addFriend(String user, String friendToAdd) throws ObjectNotFoundException, ConflictException {
        if (!friends.containsKey(user))
            throw new ObjectNotFoundException("User");
        if (!friends.containsKey(friendToAdd))
            throw new ObjectNotFoundException("Friend");
        if (friends.get(user).contains(friendToAdd))
            throw new ConflictException("This friend is already in your contacts.");
        friends.get(user).add(friendToAdd);
    }

    /**
     * Attempts to remove a friend
     * @param user given username of User
     * @param friendToRemove given username of friend
     * @throws ObjectNotFoundException upon friend not being in User's friends
     */
    public void removeFriend(String user, String friendToRemove) throws ObjectNotFoundException{
        if (!friends.containsKey(user))
            throw new ObjectNotFoundException("User");
        if (!friends.containsKey(friendToRemove))
            throw new ObjectNotFoundException("Friend");
        friends.get(user).remove(friendToRemove);
    }
}
