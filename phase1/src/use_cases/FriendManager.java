package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
    public boolean AddFriend(String user, String friendToAdd){
        // Create the new ArrayList if does not exist
        friends.computeIfAbsent(user, k -> new ArrayList<>());
        // Checking if friend already exists
        if (friends.get(user).contains(friendToAdd)) { return false; }
        else { friends.get(user).add(friendToAdd); }
        return true;
    }

    /**
     * Remove friend IF APPLICABLE (THEY ARE ACTUALLY FRIENDS). OTHERWISE DO NOTHING
     */
    public boolean RemoveFriend(String user, String friendToRemove){
        // Checking if friend ArrayList exists or friendToRemove is not in it
        if (friends.get(user) == null || !friends.get(user).contains(friendToRemove)) { return false; }
        else { friends.get(user).remove(friendToRemove); }
        return true;
    }

    public ArrayList<String> getFriendList(String user) {
        ArrayList<String> selectedFriendList = friends.get(user);
        return selectedFriendList == null ? new ArrayList<>() : selectedFriendList;
    }

}
