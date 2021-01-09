package com.sterniczuk.web;

import com.sterniczuk.MF.GetDataFromMf;
import com.sterniczuk.StringGenerator;
import com.sterniczuk.eMail.SendVerificationEmail;
import com.sterniczuk.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/registration")
@SessionAttributes("user")
public class RegistrationController {


    @ModelAttribute("user")
    public User getUserObject() {
        return new User();

    }
    @Autowired
    SendVerificationEmail email;

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

               String token = StringGenerator.ranomGenerator(40);
               email.sendMail(user.getEMail(), "Potwierdzenie Logowania na Koncie Podatnika",email.registrationTemplate(token));

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
