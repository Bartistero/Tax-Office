package com.sterniczuk.web;

import com.sterniczuk.User;
import com.sterniczuk.data.JdbcUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
@Slf4j
@Controller
@RequestMapping("/user/ksiega")
public class UserController {

    @Autowired
    JdbcUserRepository userRepository;

    @GetMapping()
    public String getRevenueAndExpenseLedger(Model model, Principal principal){

        try{
            User user = userRepository.getUser(principal.getName());
            log.info("coa "+user.getCompanyName());
            model.addAttribute("user", user);
        }
        catch(Exception e){
            return "redirect:/error";
        }

        return "ledger";
    }
}
