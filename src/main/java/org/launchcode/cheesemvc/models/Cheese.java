package org.launchcode.cheesemvc.models;

public class Cheese {

    private String cheeseName;
    private String cheeseDescription;
    private int cheeseId;
    private static int nextId = 1;

    public Cheese() {
        this.cheeseId = nextId;
        nextId++;
    }

    public Cheese(String cheeseName, String cheeseDescription)
    {
        this();
        this.cheeseName = cheeseName;
        this.cheeseDescription = cheeseDescription;
    }

    public String getCheeseDescription() {
        return cheeseDescription;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public String getCheeseName() {
        return cheeseName;
    }

    public void setCheeseName(String aCheeseName) {
        this.cheeseName = aCheeseName;
    }

    public void setCheeseDescription(String aCheeseDescription) {
        this.cheeseDescription = aCheeseDescription;
    }
}
