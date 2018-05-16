package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static ArrayList<String> cheeses = new ArrayList<>();

    @RequestMapping(value = "")
    public String index(Model model)
    {
        String title ="My Cheeses";

        //cheeses.add("American");
        //cheeses.add("Cheddar");

        model.addAttribute("title", title);
        model.addAttribute("cheeses", cheeses);

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model)
    {
        String title ="Add a Cheese";
        model.addAttribute("title", title);

        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName)
    {
        cheeses.add(cheeseName);

        return "redirect:";
    }
}
