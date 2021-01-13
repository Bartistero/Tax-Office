package com.sterniczuk.web;

import com.sterniczuk.data.JdbcUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    JdbcUserRepository userRepository;

    @GetMapping("/activate")
    public String activateAccountByeMail(@RequestParam(name="token") String token, Model model){
        Integer id = userRepository.checkOneDataInDataBase("activateToken","token",token);
        if(id >= 0){
            userRepository.activateNewUser(id);
            model.addAttribute("Error", "false");
            return "eMailAccept";
        }else{
            model.addAttribute("Error", "true");
            return "eMailAccept";
        }
    }
}
