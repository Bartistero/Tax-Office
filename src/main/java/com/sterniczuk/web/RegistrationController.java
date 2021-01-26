package com.sterniczuk.web;

import com.sterniczuk.MF.GetDataFromMf;
import com.sterniczuk.StringGenerator;
import com.sterniczuk.data.JdbcUserRepository;
import com.sterniczuk.data.UserRepository;
import com.sterniczuk.eMail.SendVerificationEmail;
import com.sterniczuk.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
//@SessionAttributes("user")
public class RegistrationController {


    @ModelAttribute("user")
    public User getUserObject() {
        return new User();

    }

    @Autowired
    SendVerificationEmail email;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GetDataFromMf getDataFromMf;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    //User user, Errors errors, Model model
    @PostMapping
    public String registrationProcess(@Valid @ModelAttribute User user, Errors errors, Model model) {

                model.addAttribute("user",user);
        try {
            Map<String, String> map = getDataFromMf.checkNIP(user.getNIP());
            if (!map.isEmpty()) {

                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setCompanyName(map.get("name"));
                user.setREGON(map.get("REGON"));
                user.setAddress(map.get("address"));
                int number = userRepository.checkOneDataInDataBase("Users", "NIP", user.getNIP());
                log.info(String.valueOf(number));
                if ( number < 0 ) {
                    String token = StringGenerator.ranomGenerator(40);
                    email.sendMail(user.getEMail(), "Potwierdzenie Rejestracji na Koncie Podatnika", email.registrationTemplate(token));

                    if (userRepository.insertNewUser(user, token) == true) {
                        log.info("chyba zadziałało");

                        return "accept";
                    } else {

                        throw new Exception("Problem with save to DB");
                    }

                } else {

                    throw new Exception("user with this tax ID already exists");
                }
            }
        } catch (Exception e) {

            log.info(e.getMessage());
            if (e.getMessage().equals("user with this tax ID already exists")) {

                model.addAttribute("Error", "Użytkownik o podanym numerze NIP, już istnieje");

            } else if (e.getMessage().equals("Problem with save to DB")) {

                model.addAttribute("Wystąpił nieoczekiwany problem, skontaktuj się z infolinią podatkową");

            } else {

                model.addAttribute("Error", "Użyktownik o podanych numerze NIP nie został odnaleziony w centralnej bazie podatników");
            }
        }
        return "registration";
    }


    @GetMapping("/accept")
    public String registrationProcessAccepted(Model model, @ModelAttribute User user) {

        model.addAttribute(user);

        return "accept";
    }
}
