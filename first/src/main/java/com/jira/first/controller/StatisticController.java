package com.jira.first.controller;

import com.jira.first.domain.Ticket;
import com.jira.first.domain.User;
import com.jira.first.repos.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StatisticController {
    @Autowired
    private TicketRepo ticketRepo;

    @GetMapping("/statistic/{user}")
    public String stat(@AuthenticationPrincipal User curUser, @PathVariable User user, Model model){
        List<Ticket> ticketsStatus1 = ticketRepo.findByNameSort(0, user.getId());

        model.addAttribute("count0", ticketsStatus1.size());
        model.addAttribute("count1", ticketRepo.countByStatusAndUserId(1, user.getId()));
        model.addAttribute("count2", ticketRepo.countByStatusAndUserId(2, user.getId()));
        model.addAttribute("count3", ticketRepo.countByStatusAndUserId(3, user.getId()));
        model.addAttribute("count4", ticketRepo.countByStatusAndUserId(4, user.getId()));
        model.addAttribute("countAuthor", ticketRepo.countByAuthor(user.getId()));
        model.addAttribute("name", user.getUsername());
        return "statistic";

    }

}
