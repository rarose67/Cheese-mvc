package org.launchcode.cheesemvc.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.cheesemvc.models.MyDate;
import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request)
    {
        String name = request.getParameter("name");
        String match = request.getParameter("match");
        String title = "Index";

        model.addAttribute("title", title);

        if(UserData.getAll().size() == 0)
        {
            return "redirect:add";
        }
        else if (name != null)
        {

            model.addAttribute("name", name);
            model.addAttribute("match", match);
            model.addAttribute("users", UserData.getAll());

            return "user/index";
        }
        else
        {
            model.addAttribute("users", UserData.getAll());
            return "user/index";
        }

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAdduserForm(Model model)
    {
        String title ="Add a user";
        model.addAttribute("title", title);
        model.addAttribute(new User());

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(Model model, @ModelAttribute @Valid User newUser, Errors errors, String verify)
    {
        if (errors.hasErrors())
        {
            String title ="Add a user";
            model.addAttribute("title", title);

            return "user/add";
        }

        if (newUser.getPassword().equals(verify))
        {
            UserData.add(newUser);
            return "redirect:/user?name=" + newUser.getUsername() + "&match=true";
        }
        else
        {
            return "redirect:/user?name=" + newUser.getUsername() + "&match=false";
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String displayDetail(Model model, @PathVariable int userId)
    {
        String title ="User Detail";
        Date currDate = new Date();
        int diffHours, diffMins;
        String timeDiff;

        model.addAttribute("title", title);

        User user = UserData.getById(userId);
        timeDiff = MyDate.diffDates(user.getDate(), currDate);

        model.addAttribute("user", user);
        model.addAttribute("time", timeDiff);
        System.out.println("\n\ntime:" + timeDiff + "\n");


        return "user/detail";
    }

}
