import java.util.HashMap;

public class FriendManager {

    /* AUTHOR: ANDREW
     * No constructor is needed
     */

    /**
    * Add friend IF APPLICABLE (THEY AREN'T ALREADY FRIENDS). OTHERWISE DO NOTHING
    */

    public void AddFriend(Account Account1, Account Account2){
        /*
         * Assumption: If Account1 is in Account2's friend list, then
         * Account2 is in Account1's friend list. Reason: friend lists
         * are initially empty, and AddFriend adds accounts simultaneously
         */
        String username_1 = Account1.getUsername();
        String username_2 = Account2.getUsername();
        HashMap<String, Account> list_1 = Account1.getFriendsList();
        HashMap<String, Account> list_2 = Account2.getFriendsList();

        if (! (list_2.containsKey(username_1))){
            list_1.put(username_2, Account2);
            list_2.put(username_1, Account1);
        }
    }

    /**
     * Remove friend IF APPLICABLE (THEY ARE ACTUALLY FRIENDS). OTHERWISE DO NOTHING
     */

    public void RemoveFriend(Account Account1, Account Account2){
        /*
         * Assumption: If Account1 is in Account2's friend list, then
         * Account2 is in Account1's friend list, due to the
         * AddFriend method's implementation
         */
        if (Account2.getFriendsList().containsKey(Account1.getUsername())){
            Account1.getFriendsList().remove(Account2.getUsername());
            Account2.getFriendsList().remove(Account1.getUsername());
        }
    }

}
