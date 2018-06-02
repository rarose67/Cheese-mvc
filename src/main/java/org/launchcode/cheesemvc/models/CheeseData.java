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
        cheeses.add(new_cheese);
    }

    public static Cheese getById(int id)
    {
        Cheese currentChesse;

        for (int i=0; i < cheeses.size(); i++)
        {
            currentChesse = cheeses.get(i);

            if(currentChesse.getCheeseId() == id)
            {
                return  currentChesse;
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
