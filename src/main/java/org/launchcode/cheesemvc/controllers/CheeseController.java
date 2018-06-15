package org.launchcode.cheesemvc.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        model.addAttribute("title", title);
        model.addAttribute("cheeses", CheeseData.getAll());

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model)
    {
        String title ="Add a Cheese";
        model.addAttribute("title", title);
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@Valid  Cheese newCheese, Errors errors, Model model)
    {
        if (errors.hasErrors())
        {
            String title ="Add a Cheese";
            model.addAttribute("title", title);

            return "cheese/add";
        }
            CheeseData.add(newCheese);
            return "redirect:";
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
    public String displayEditForm(HttpServletRequest request, Model model, @PathVariable int cheeseId)
    {
        String title ="Edit a Cheese";
        model.addAttribute("title", title);
        String error = request.getParameter("error");

        Cheese changedCheese = CheeseData.getById(cheeseId);
        model.addAttribute("cheese", changedCheese);
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("error", error);

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(HttpServletRequest request, @RequestParam int cheeseId, @RequestParam String name, @RequestParam CheeseType type, @RequestParam String description)
    {
        Cheese changedCheese = CheeseData.getById(cheeseId);
        boolean alpha = CheeseController.isAlpha(name);
        String error;

        if(name.equals(""))
        {
            error = "The Cheese name is required";
            return "redirect:edit/" + cheeseId + "?error=" + error;
        }
        else if(!alpha)
        {
            error = "The Cheese name must be Alphabetic";
            return "redirect:edit/" + cheeseId + "?error=" + error;
        }
        else
        {
            changedCheese.setCheeseName(name);
            changedCheese.setCheeseDescription(description);
            changedCheese.setType(type);
            return "redirect:";
        }
    }
}
