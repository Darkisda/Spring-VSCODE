package eaj.tads.projeto2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eaj.tads.projeto2.models.Users;
import eaj.tads.projeto2.services.UsersService;

@Controller
public class UsersController {
    
    @Autowired
    UsersService usersService;

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/")
    public String getHome(Model model) {
        List<Users> usersList = usersService.findAll();
        model.addAttribute("usersList", usersList);
        return "index";
    }

    @RequestMapping("/cadastrar")
    public String getPageCadastro(Model model) {
        Users users = new Users();
        model.addAttribute("users", users);
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String addUser(@ModelAttribute Users users ) {
        usersService.add(users);
        return "redirect:/";
    }

    @RequestMapping("/editar/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editar");
        Users users = usersService.get(id);

        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping("/deletar/{id}")
    public String deleteAnime(@PathVariable(name = "id") Long id) {
        usersService.delete(id);
        return "redirect:/";
    }

}