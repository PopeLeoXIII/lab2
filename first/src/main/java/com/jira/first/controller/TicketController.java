package com.jira.first.controller;

import com.jira.first.domain.Messege;
import com.jira.first.domain.Ticket;
import com.jira.first.domain.User;
import com.jira.first.repos.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@Controller
public class TicketController {
    @Autowired
    public TicketRepo ticketRepo;

    @GetMapping("/all-ticket")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Ticket> tickets = ticketRepo.findAll();

        if(filter != null && !filter.isEmpty()) {
            tickets = ticketRepo.findByName(filter);
        } else {
            tickets = ticketRepo.findAll();
        }

        model.addAttribute("tickets", tickets);
        model.addAttribute("filter", filter);
        //model.addAttribute("usr", user.getUsername());
        return "allTicket";
    }

    @PostMapping("/all-ticket")
    public String add(
            @AuthenticationPrincipal User user,
            @PathVariable Ticket ticket,
            Map<String, Object> model) {
        ticket.setAssigns(user);

        Iterable<Ticket> tickets = ticketRepo.findAll();
        model.put("tickets", tickets);
        return "allTicket";
    }
}
