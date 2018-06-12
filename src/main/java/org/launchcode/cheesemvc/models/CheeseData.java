package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class CheeseData {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    public static ArrayList<Cheese> getAll()
    {
        return cheeses;
    }

    public static void add(Cheese new_cheese)

    {
        int id = Cheese.getNextId();
        cheeses.add(new_cheese);
        Cheese.setNextId(id + 1);

    }

    public static Cheese getById(int id)
    {
        Cheese currentCheese;

        for (int i=0; i < cheeses.size(); i++)
        {
            currentCheese = cheeses.get(i);

            if(currentCheese.getCheeseId() == id)
            {
                return  currentCheese;
            }
        }
        return null;
    }

    public static boolean remove(int id)
    {
        Cheese cheeseToRemove = getById(id);

        return cheeses.remove(cheeseToRemove);
    }
}
