package com.jira.first.controller;

import com.jira.first.domain.Messege;
import com.jira.first.domain.User;
import com.jira.first.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(
            Map<String, Object> model) {
        return "greeting";
    }
    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Messege> messeges = messageRepo.findAll();

        if(filter != null && !filter.isEmpty()) {
            messeges = messageRepo.findByTag(filter);
        } else {
            messeges = messageRepo.findAll();
        }

        model.addAttribute("messages", messeges);
        model.addAttribute("filter", filter);
        //model.addAttribute("usr", user.getUsername());
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model) {
        Messege m = new Messege(text, tag, user);
        messageRepo.save(m);

        Iterable<Messege> messeges = messageRepo.findAll();
        model.put("messages", messeges);
        return "main";
    }

}
