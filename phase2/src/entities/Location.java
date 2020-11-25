package entities;

public class Location {
    private final String name;
    private final int id;
    private int maxOccupancy;
    private int numTables;
    private int numChairs;
    private boolean hasInternet;
    private boolean hasSoundSystem;
    private boolean hasPresentationScreen; //for projectors/movie screens etc.
    private String furtherNotes; //further notes the Organizer would like to add

    public Location(String name, int id, int maxOccupancy, int numTables, int numChairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, String furtherNotes) {
        this.name = name;
        this.id = id;
        this.maxOccupancy = maxOccupancy;
        this.numTables = numTables;
        this.numChairs = numChairs;
        this.hasInternet = hasInternet;
        this.hasSoundSystem = hasSoundSystem;
        this.hasPresentationScreen = hasPresentationScreen;
        this.furtherNotes = furtherNotes;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public int getMaxOccupancy(){
        return this.maxOccupancy;
    }
    public int numTables(){
        return this.numTables;
    }
    public int numChairs(){
        return this.numChairs;
    }
    public boolean hasInternet(){
        return this.hasInternet;
    }
    public boolean hasSoundSystem(){
        return this.hasSoundSystem;
    }
    public boolean hasPresentationScreen(){
        return this.hasPresentationScreen;
    }
    public String furtherNotes() {
        return this.furtherNotes;
    }
    public void setMaxOccupancy(int max) {
        this.maxOccupancy = max;
    }
    public void setNumTables(int num) {
        this.numTables = num;
    }
    public void setNumChairs(int num) {
        this.numChairs = num;
    }
    public void setHasInternet(boolean status) {
        this.hasInternet = status;
    }
    public void setHasSoundSystem(boolean status) {
        this.hasPresentationScreen = status;
    }
    public void setHasPresentationScreen(boolean status) {
        this.hasPresentationScreen = status;
    }
    public void setFurtherNotes(String notes) {
        this.furtherNotes = notes;
    }
}
