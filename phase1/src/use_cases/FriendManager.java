package use_cases;

import java.util.ArrayList;
import java.util.HashMap;
import entities.Account;

public class FriendManager {

    /* AUTHOR: ANDREW
     * No constructor is needed
     */

    /**
     * Add friend IF APPLICABLE (THEY AREN'T ALREADY FRIENDS). OTHERWISE DO NOTHING
     */
    public static boolean AddFriend(Account adder, Account added){
        if (adder.getFriendsList().contains(added.getUsername())){
            return false;
        }
        adder.getFriendsList().add(added.getUsername());
        added.getFriendsList().add(adder.getUsername());
        return true;
        }

    /**
     * Remove friend IF APPLICABLE (THEY ARE ACTUALLY FRIENDS). OTHERWISE DO NOTHING
     */

//    // Old version
//    public static boolean removeFriend(Account Account1, Account Account2){
//        /*
//         * Assumption: If Account1 is in Account2's friend list, then
//         * Account2 is in Account1's friend list, due to the
//         * AddFriend method's implementation
//         */
//        if (Account1.getFriendsList().containsKey(Account2.getUsername())){
//            Account1.getFriendsList().remove(Account2.getUsername());
//            return true;
//        }
//        return false;
//    }

    public static boolean RemoveFriend(Account deleter, Account deleted){
        if (!deleter.getFriendsList().contains(deleted.getUsername())) {
            return false;
        }
        deleter.getFriendsList().remove(deleted.getUsername());
        deleted.getFriendsList().remove(deleter.getUsername());
        return true;
    }

    public static ArrayList<String> getFriendList(Account account) {
//        // Old version
//        ArrayList<String> friendarray = new ArrayList<>();
//        for(String friendUsername: account.getFriendsList().keySet()) {
//            Account friend = account.getFriendsList().get(friendUsername);
//            friendarray.add(friend.getUsername() + ": " + friend.getFirstName() + " " + friend.getLastName());
//        }
//        return friendarray;
        return account.getFriendsList();
    }

}
