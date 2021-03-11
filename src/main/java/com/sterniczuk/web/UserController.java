package com.sterniczuk.web;

import com.sterniczuk.Receipt;
import com.sterniczuk.User;
import com.sterniczuk.data.JdbcReceiptRepository;
import com.sterniczuk.data.JdbcUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
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
            model.addAttribute("newReceipt", new Receipt());
        } catch (Exception e) {
            return "redirect:/error";
        }

        return "ledger";
    }

    @PostMapping("/newInvoice")
    public String addNewInvoice(@ModelAttribute @Valid Receipt receipt, Principal principal) {

        Date date = new Date();
        receipt.setDate(date);

        try{
            User user = userRepository.getUser(principal.getName());
            receipt.setIdUser(user.getId().toString());
            receipt.setType("1");
            receiptRepository.addNewReceipt(receipt);
        }catch (Exception e){
            return "redirect:/error";
        }

        return "redirect:/user/ksiega";
    }

    @PostMapping("/newCost")
    public String addNewCost(@ModelAttribute @Valid Receipt receipt, Principal principal) {

        Date date = new Date();
        receipt.setDate(date);

        try{
            User user = userRepository.getUser(principal.getName());
            receipt.setIdUser(user.getId().toString());
            receipt.setType("0");
            receiptRepository.addNewReceipt(receipt);
        }catch (Exception e){
            return "redirect:/error";
        }

        return "redirect:/user/ksiega";
    }

    @PostMapping("/editForm")
    public String editInvoice(@ModelAttribute @Valid Receipt receipt, Principal principal) {

        receiptRepository.editReceipt(receipt);
        return "redirect:/user/ksiega";
    }
}
