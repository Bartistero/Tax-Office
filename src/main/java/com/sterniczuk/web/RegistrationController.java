package com.sterniczuk.web;

import com.sterniczuk.MF.GetDataFromMf;
import com.sterniczuk.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Controller
@RequestMapping("/registration")
@SessionAttributes("user")
public class RegistrationController {


    @ModelAttribute("user")
    public User getUserObject() {
        return new User();

    }

    GetDataFromMf getDataFromMf = new GetDataFromMf();

    @GetMapping
    public String showRegistrationForm(Model model) {

        return "registration";
    }

    //User user, Errors errors, Model model
    @PostMapping
    public String registrationProcess(@Valid  @ModelAttribute  User user, Errors errors, Model model) {


       try{
           Map<String, String> map = getDataFromMf.checkNIP(user.getNIP());
           if(!map.isEmpty()){

               user.setCompanyName(map.get("name"));
               user.setREGON(map.get("REGON"));
               user.setAddress(map.get("address"));
               return "redirect:/registration/accept";
           }
       }catch(Exception e){
           //e.printStackTrace();
           log.info("Wystąpił błąd xD");
       }
        return "registration";
    }


    @GetMapping("/accept")
    public String registrationProcessAccepted(Model model, @ModelAttribute  User user, HttpSession session) {

        model.addAttribute(user);

        if (session.getId() != null) {
            session.invalidate();
        }
        return "accept";
    }
}
