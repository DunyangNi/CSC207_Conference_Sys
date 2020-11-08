package test.usecases;
import entities.*;
import org.junit.*;

import use_cases.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

public class TestFriendManager {
    FriendManager friendmanager = new FriendManager();

    @Test(timeout = 50)
    public void test_add_friend_success() {
        Attendee lbj = new Attendee("lbj23","king","Lebron","James");
        Organizer kb = new Organizer("kb24","mamba","Kobe","Bryant");

        assert(lbj.getFriendsList().isEmpty());
        assert(kb.getFriendsList().isEmpty());

        friendmanager.AddFriend(lbj,kb);

        assert(lbj.getFriendsList().size() == 1);
        assert(kb.getFriendsList().size() == 1);

        assert(lbj.getFriendsList().get("kb24") == kb);
        assert(kb.getFriendsList().get("lbj23") == lbj);
    }

    @Test(timeout = 50)
    public void test_remove_friend_success() {
        Attendee lbj = new Attendee("lbj23","king","Lebron","James");
        Organizer kb = new Organizer("kb24","mamba","Kobe","Bryant");

        friendmanager.AddFriend(lbj,kb);
        friendmanager.RemoveFriend(kb,lbj);

        assert(lbj.getFriendsList().isEmpty());
        assert(kb.getFriendsList().isEmpty());
    }

    @Test(timeout = 50)
    public void test_remove_friend_failure() {
        Attendee lbj = new Attendee("lbj23","king","Lebron","James");
        Organizer kb = new Organizer("kb24","mamba","Kobe","Bryant");
        Speaker mj = new Speaker("mj23","goat","Michael","Jordan");

        friendmanager.AddFriend(lbj,kb);

        friendmanager.RemoveFriend(kb,mj);
        // Failed removal that doesn't change anything //

        assert(lbj.getFriendsList().size() == 1);
        assert(kb.getFriendsList().size() == 1);

        assert(lbj.getFriendsList().get("kb24") == kb);
        assert(kb.getFriendsList().get("lbj23") == lbj);
    }

}
