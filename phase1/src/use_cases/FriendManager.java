package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import Throwables.*;

public class FriendManager implements Serializable {
    HashMap<String, ArrayList<String>> friends;

    /* AUTHOR: ANDREW */

    public FriendManager(HashMap<String, ArrayList<String>> friends) { this.friends = friends; }

    public FriendManager() { this(new HashMap<>()); }

    // (NEW!)
    public void addAccountKey(String user) { friends.put(user, new ArrayList<>()); }

    /**
     * Add friend IF APPLICABLE (THEY AREN'T ALREADY FRIENDS). OTHERWISE DO NOTHING
     */
    public void AddFriend(String user, String friendToAdd) throws ObjectNotFoundException, ConflictException {
        if(!friends.containsKey(user)) {
            throw new ObjectNotFoundException();
        }
        if(!friends.containsKey(friendToAdd)) {
            throw new ObjectNotFoundException();
        }
        if (friends.get(user).contains(friendToAdd)) {
            throw new ConflictException("This user is already in your contacts. Please try again");
        }
        friends.get(user).add(friendToAdd);
    }

    /**
     * Remove friend IF APPLICABLE (THEY ARE ACTUALLY FRIENDS). OTHERWISE DO NOTHING
     */
    public void RemoveFriend(String user, String friendToRemove) throws ObjectNotFoundException{
        if(!friends.containsKey(user)) {
            throw new ObjectNotFoundException();
        }
        if(!friends.containsKey(friendToRemove)) {
            throw new ObjectNotFoundException();
        }
        else { friends.get(user).remove(friendToRemove); }
    }

    public ArrayList<String> getFriendList(String user) { return friends.get(user); }
}
