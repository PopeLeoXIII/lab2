package com.jira.first.controller;

import com.jira.first.domain.Status;
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
public class UserPageController {
    @Autowired
    private TicketRepo ticketRepo;

    @GetMapping("/user-page/{user}/{mode}")
    public String userInfo (@AuthenticationPrincipal User curUser, @PathVariable User user, @PathVariable String mode, Model model){
        if(mode.equals("author")) {
            model.addAttribute("tickets", user.getAuthorTickets());
        } else {
            model.addAttribute("tickets", user.getAssignTickets());
        }
        model.addAttribute("name", user.getUsername());
        model.addAttribute("userId",  curUser.getId().toString());

        return "userPage";

    }


}
