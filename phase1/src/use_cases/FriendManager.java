package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import Throwables.*;

public class FriendManager implements Serializable {
    HashMap<String, ArrayList<String>> friends;

    /* AUTHOR: ANDREW */

    // (NEW!)
    public FriendManager(HashMap<String, ArrayList<String>> friends) { this.friends = friends; }

    // (NEW!)
    public FriendManager() { this(new HashMap<>()); }

    /**
     * Add friend IF APPLICABLE (THEY AREN'T ALREADY FRIENDS). OTHERWISE DO NOTHING
     */
    public void AddFriend(String user, String friendToAdd) throws ObjectNotFoundException{
        if(!friends.containsKey(user)) {
            throw new ObjectNotFoundException();
        }
        if(!friends.containsKey(friendToAdd)) {
            throw new ObjectNotFoundException();
        }
        // Create the new ArrayList if does not exist
        friends.computeIfAbsent(user, k -> new ArrayList<>());
        // Checking if friend already exists
        if (!friends.get(user).contains(friendToAdd)) { friends.get(user).add(friendToAdd); }
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
        // Checking if friend ArrayList exists or friendToRemove is not in it
        if (friends.get(user) == null || !friends.get(user).contains(friendToRemove)) { return; }
        else { friends.get(user).remove(friendToRemove); }
    }

    public ArrayList<String> getFriendList(String user) {
        ArrayList<String> selectedFriendList = friends.get(user);
        return selectedFriendList == null ? new ArrayList<>() : selectedFriendList;
    }

}
