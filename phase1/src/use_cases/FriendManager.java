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

    public static boolean AddFriend(Account Account1, Account Account2){
        String username_2 = Account2.getUsername();
        HashMap<String, Account> list_1 = Account1.getFriendsList();

        if (list_1.containsKey(username_2)){
            return false;
        }
        list_1.put(username_2, Account2);
        return true;
    }

    /**
     * Remove friend IF APPLICABLE (THEY ARE ACTUALLY FRIENDS). OTHERWISE DO NOTHING
     */

    public static boolean RemoveFriend(Account Account1, Account Account2){
        /*
         * Assumption: If Account1 is in Account2's friend list, then
         * Account2 is in Account1's friend list, due to the
         * AddFriend method's implementation
         */
        if (Account1.getFriendsList().containsKey(Account2.getUsername())){
            Account1.getFriendsList().remove(Account2.getUsername());
            return true;
        }
        return false;
    }

    public static ArrayList<String> getFriendList(Account account) {
        ArrayList<String> friendarray = new ArrayList<>();
        for(String friendusername: account.getFriendsList().keySet()) {
            Account friend = account.getFriendsList().get(friendusername);
            friendarray.add(friend.getUsername() + ": " + friend.getFirstName() + " " + friend.getLastName());
        }
        return friendarray;
    }

}
