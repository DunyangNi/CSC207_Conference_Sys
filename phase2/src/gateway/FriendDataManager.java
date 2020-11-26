package gateway;

import java.io.*;
import use_cases.*;

public class FriendDataManager implements DataReader, DataSaver{
    private final String friendPath;

    public FriendDataManager() {
        this("FriendManager");
    }

    public FriendDataManager(String friendPath) {
        this.friendPath = friendPath;
    }

    public FriendManager readManager() {
        try{
            return (FriendManager) readObject(friendPath);
        } catch (IOException e) {
            System.out.println("Could not read AccountManager, creating a new AccountManager.");
            return new FriendManager();
        } catch (ClassNotFoundException e) {
            System.out.println("AccountManager not found, creating a new AccountManager.");
            return new FriendManager();
        }
    }
}
