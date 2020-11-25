package Enums;

public enum AttendeeCommand {
    EXIT("00"),
    LOGOUT("0"),
    ADDCONTACT("1"),
    REMCONTACT("2"),
    VIEWCONTACTS("3"),
    MSGATT("4"),
    MSGSPEAK("5"),
    VIEWCONVO("6"),
    VIEWTALKSCHED("7"),
    TALKSIGNUP("8"),
    LEAVEEVENT("9"),
    MYTALKS("10"),
    VIEWMENU("*");

    public final String command;

    private AttendeeCommand(String command) {
        this.command = command;
    }
}
