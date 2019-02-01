package com.jira.first.controller;

import com.jira.first.domain.*;
import com.jira.first.repos.MessageRepo;
import com.jira.first.repos.TicketRepo;
import com.jira.first.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class TicketEditController {
    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "{userId}/add-ticket", method = RequestMethod.GET)
    public String main(@AuthenticationPrincipal User user,
                       @RequestParam(name="parentId", required=false, defaultValue= "65") String parentId,
                       @RequestParam(name="parentName", required=false, defaultValue="") String parentName,
                       @RequestParam(name="editTicket", required = false) Ticket editTicket,
                       Model model){
//        Iterable<Ticket> tickets = ticketRepo.findByAuthorOrAssigns(user, user);
//        model.addAttribute("assignTickets", user.getAssignTickets());
//        model.addAttribute("authorTickets", user.getAuthorTickets());
//        model.addAttribute("stats", Status.values());
        if(editTicket != null){
            model.addAttribute("editTicket", editTicket);
        }
        Iterable<User> users  = userRepo.findAll();
        model.addAttribute("users", users);
        model.addAttribute("parentId", parentId);
        model.addAttribute("parentName", parentName);

        model.addAttribute("tickets", ticketRepo.findAll());
        model.addAttribute("userId",  user.getId());

        return "newTicket";
    }

    @RequestMapping(value = "{userId}/add-ticket", method = RequestMethod.POST)
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String tname,
            @RequestParam String description,
            @RequestParam String priority,
            @RequestParam String dateEnding,
            @RequestParam User assign,
            @RequestParam Ticket parent,
            @RequestParam(name="editTicket", required = false) Ticket editTicket,
            @RequestParam(name="text", required = false, defaultValue = "Ticket edit at ") String text,
            @RequestParam(name="tag", required = false) String tag,
            Map<String, Object> model) {
        Priority prior = Priority.valueOf(priority);
        model.put("userId",  user.getId().toString());

        if(editTicket != null){
            editTicket.setName(tname);
            editTicket.setDescription(description);
            editTicket.setAssigns(assign);
            editTicket.setParent(parent);
            editTicket.setPriority(prior);
            Messege message = new Messege(text, tag, user, editTicket);
            editTicket.addMessage(message);
            ticketRepo.save(editTicket);
            messageRepo.save(message);
        } else {
            Ticket t = new Ticket(tname, description, user, prior, dateEnding, assign, parent);
            ticketRepo.save(t);
            user.addAuthorTickets(t);
        }
        model.put("assignTickets", user.getAssignTickets());
        model.put("authorTickets", user.getAuthorTickets());
        Iterable<User> users  = userRepo.findAll();
        model.put("users", users);
        model.put("tickets", ticketRepo.findAll());


        return "redirect:/user-page/{userId}/author";
    }

    @RequestMapping(value = "/ticket/{ticket}", method = RequestMethod.GET)
    public String ticketEdit(
            @AuthenticationPrincipal User user,
            @PathVariable Ticket ticket,
            Model model){
        if(ticket.getAuthor().equals(user)) {
            model.addAttribute("isAuthor", "true");
        } else {
            model.addAttribute("isAuthor", "false");
        }
        model.addAttribute("messages", ticket.getMesseges());
        //model.addAttribute("isAuthor", true);
        return "ticketPage";
    }
    @RequestMapping(value = "/ticket/{ticket}", method = RequestMethod.POST, params = "addComment")
    public String addComment(
            @AuthenticationPrincipal User user,
            @PathVariable Ticket ticket,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model){
        Messege messege = new Messege(text, tag, user, ticket);
        messageRepo.save(messege);
        ticket.addMessage(messege);
        ticketRepo.save(ticket);
        model.put("messages", ticket.getMesseges());
        return "ticketPage";
    }


    @RequestMapping(value = "/ticket/{ticket}", method = RequestMethod.POST, params = "changeStatus")
    public String changeTicket(
                                @AuthenticationPrincipal User user,
                                @PathVariable Ticket ticket,
                                @RequestParam String status,
                                @RequestParam String text,
                                Map<String, Object> model) {
        Status stat = Status.valueOf(status);
        Messege messege = new Messege(text + "to " + status, "stat change", user, ticket);
        ticket.setStatus(stat);
        ticket.addMessage(messege);
        messageRepo.save(messege);
        ticketRepo.save(ticket);
        model.put("messages", ticket.getMesseges());
        return "ticketPage";
    }

    @RequestMapping(value = "/ticket/{ticket}", method = RequestMethod.POST, params = "take")
    public String takeTicket( @AuthenticationPrincipal User user,
                              @PathVariable Ticket ticket,
                              Map<String, Object> model) {
        ticket.setAssigns(user);
        ticketRepo.save(ticket);
        model.put("messages", ticket.getMesseges());
        return "ticketPage";
    }
}
