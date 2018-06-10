package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class UserData {

    static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getAll()
    {
        return users;
    }

    public static void add(User new_user)
    {
        users.add(new_user);
    }

    public static User getById(int id)
    {
        User currentUser;

        for (int i=0; i < users.size(); i++)
        {
            currentUser = users.get(i);

            if(currentUser.getUserId() == id)
            {
                return  currentUser;
            }
        }
        return null;
    }

    public static boolean remove(int id)
    {
        User userToRemove = getById(id);

        return users.remove(userToRemove);
    }
}
