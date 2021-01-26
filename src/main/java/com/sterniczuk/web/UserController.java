package com.sterniczuk.web;

import com.sterniczuk.Receipt;
import com.sterniczuk.User;
import com.sterniczuk.data.JdbcReceiptRepository;
import com.sterniczuk.data.JdbcUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user/ksiega")
public class UserController {

    @Autowired
    JdbcUserRepository userRepository;

    @Autowired
    JdbcReceiptRepository receiptRepository;

    @GetMapping()
    public String getRevenueAndExpenseLedger(@RequestParam(name="month", required=false) String month, Model model, Principal principal) {

        String fullMonth;
        try {
            if(month==null){
                LocalDate date = LocalDate.now();
                month = String.valueOf(date.getMonthValue());
            }
            User user = userRepository.getUser(principal.getName());

            List<Receipt> receipts = receiptRepository.getALLRecepit(user.getId().toString(), month);

            log.info("drukuje wielkosc listy: " + String.valueOf(receipts.size()));

            model.addAttribute("user", user);
            model.addAttribute("receipts", receipts);
            model.addAttribute("month", month);
        } catch (Exception e) {
            return "redirect:/error";
        }

        return "ledger";
    }
}
