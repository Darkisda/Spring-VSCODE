package eaj.tads.projeto2.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
    public String getHome(Model model, HttpServletResponse response) {
        List<Users> usersList = usersService.findAll();
        model.addAttribute("usersList", usersList);
        
        String count = usersService.count().toString();
        response.setHeader("X-Total-Count", count);

        return "index";
    }

    @RequestMapping("/cadastrar")
    public String getPageCadastro(Model model) {
        Users users = new Users();
        model.addAttribute("users", users);
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String addUser(@ModelAttribute @Valid Users users, Errors errors) {
        if(errors.hasErrors()){
            return "cadastrar";
        }
        else {
            usersService.save(users);
            return "redirect:/";
        }
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

    @RequestMapping("/logar")
    public String loggin(Model model, HttpServletResponse response, HttpServletRequest request) {
        Users users = new Users();
        model.addAttribute("users", users);
        return "logar";
    }

    @RequestMapping(value = "/perfil", method = RequestMethod.POST)
    public String autenticar(@ModelAttribute @Valid Users users, Errors errors, 
                                HttpServletResponse response, 
                                HttpServletRequest request,
                                Model model){

        var email = users.getEmail();
        var password = users.getPassword();

        // if(errors.hasErrors()){
            
        //     return "logar";
        // }

        HttpSession session = request.getSession();

        Users userLogado = usersService.login(email, password);
        System.out.println(userLogado.toString());
        
        Cookie cookie = new Cookie("autenticado", userLogado.getNome());

        session.setAttribute("autenticacao", true);
        model.addAttribute("userLogado", userLogado);
        response.addCookie(cookie);

        return "/perfil";

    }

}