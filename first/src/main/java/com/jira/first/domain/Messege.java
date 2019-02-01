package com.jira.first.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Messege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;
    private String creatingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Ticket task;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Messege(){}

    public Messege(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.author = user;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yy");
        this.creatingDate = dateFormat.format( new Date());
    }

    public Messege(String text, String tag, User user, Ticket ticket) {
        this.text = text;
        this.tag = tag;
        this.author = user;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yy");
        this.creatingDate = dateFormat.format( new Date());
        this.task = ticket;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public String getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(String creatingDate) {
        this.creatingDate = creatingDate;
    }

    public Ticket getTask() {
        return task;
    }

    public void setTask(Ticket task) {
        this.task = task;
    }
}
