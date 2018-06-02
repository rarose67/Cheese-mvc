package org.launchcode.cheesemvc.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    public static boolean isAlpha(String text)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] charactersInText;
        String character;
        String error;
        boolean is_lower = true;
        boolean is_upper = true;
        boolean result = true;

        charactersInText = text.toCharArray();

        for (int i=0; i < charactersInText.length; i++)
        {
            character = Character.toString(charactersInText[i]);
            is_lower = (alphabet.indexOf(character) >= 0);
            is_upper = (alphabet.toUpperCase().indexOf(character) >= 0);

            if (!is_upper && !is_lower && !(character.equals(" ")))
            {
                result = false;
                break;
            }
        }

        return result;
    }

    @RequestMapping(value = "")
    public String index(Model model)
    {
        String title ="My Cheeses";

        //cheeses.add("American");
        //cheeses.add("Cheddar");

        model.addAttribute("title", title);
        model.addAttribute("cheeses", CheeseData.getAll());

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model, HttpServletRequest request)
    {
        String title ="Add a Cheese";
        String error = request.getParameter("error");
        model.addAttribute("title", title);

        if (error != null)
        {
            model.addAttribute("error", error);
            //System.out.println("\n" + error + "\n" + error.length() + "\n");
        }
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese)
    //public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription)
    {
        boolean alpha = CheeseController.isAlpha(newCheese.getCheeseName());
        String error;

        if(newCheese.getCheeseName().equals(""))
        {
            error = "The Cheese name is required";
            return "redirect:add?error=" + error;
        }
        else if(!alpha)
        {
            error = "The Cheese name must be Alphabetic";
            return "redirect:add?error=" + error;
        }
        else {
            //Cheese new_cheese = new Cheese(cheeseName, cheeseDescription);
            CheeseData.add(newCheese);
            return "redirect:";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model)
    {
        String title ="Remove a Cheese";
        model.addAttribute("title", title);
        model.addAttribute("cheeses", CheeseData.getAll());

        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int cheeseId)
    {
        CheeseData.remove(cheeseId);

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId)
    {
        String title ="Edit a Cheese";
        model.addAttribute("title", title);

        Cheese changedCheese = CheeseData.getById(cheeseId);
        model.addAttribute("cheese", changedCheese);
        changedCheese.getCheeseDescription();

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int cheeseId, @RequestParam String name, @RequestParam String description)
    {
        Cheese changedCheese = CheeseData.getById(cheeseId);
        boolean alpha = CheeseController.isAlpha(changedCheese.getCheeseName());
        String error;

        if(changedCheese.getCheeseName().equals(""))
        {
            error = "The Cheese name is required";
            return "redirect:edit?error=" + error;
        }
        else if(!alpha)
        {
            error = "The Cheese name must be Alphabetic";
            return "redirect:edit?error=" + error;
        }
        else
        {
            changedCheese.setCheeseName(name);
            changedCheese.setCheeseDescription(description);
            return "redirect:";
        }
    }
}
