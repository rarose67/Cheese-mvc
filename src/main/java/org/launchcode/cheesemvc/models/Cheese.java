package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {

    @NotNull
    @Size(min = 3, max = 15)  //validation annotations
    private String cheeseName;

    private String cheeseDescription;
    private int cheeseId;
    private static int nextId = 1;

    private CheeseType type;

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

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType aType) {
        this.type = aType;
    }

    public void setCheeseDescription(String aCheeseDescription) {
        this.cheeseDescription = aCheeseDescription;
    }
}
