package Enums;

public enum SpeakerCommand {
    EXIT("00"),
    LOGOUT("0"),
    VIEWALLACC("1"),
    ADDCONTACT("2"),
    REMCONTACT("3"),
    VIEWCONTACTS("4"),
    MSGATT("5"),
    MSGTALKS("6"),
    VIEWCONVO("7"),
    MYTALKSCHED("8"),
    VIEWTALKSCHED("9"),
    VIEWMENU("*");

    public final String command;

    private SpeakerCommand(String command) {
        this.command = command;
    }
}
