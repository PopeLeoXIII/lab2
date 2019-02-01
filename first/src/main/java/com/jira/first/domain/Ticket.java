package com.jira.first.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String dateBegining;
    private String dateEnding;

    private Status status;
    private Priority priority;

    @OneToMany(mappedBy = "task",fetch = FetchType.EAGER)
    private Set<Messege> messeges;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToOne(fetch = FetchType.EAGER)
    private Ticket parent;


    public Ticket(String name, String description, User user, Priority priority, String dateEnding, User assign, Ticket parent) {
        this.name = name;
        this.description = description;
        this.author = user;

        this.assigns = assign;
        this.priority = priority;
        this.status = Status.OPEN;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yy");
        this.dateBegining = dateFormat.format( new Date());
        this.dateEnding = dateEnding;
        if(54 == parent.getId()){
            this.parent = null;
        } else {
        this.parent = parent;
        }
    }
    public Ticket(){}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigns_id")
    private User assigns;

    public void addMessage(Messege messege){ messeges.add(messege);}

    public String getParentName() {
        return parent != null ? parent.getName() : "<none>";
    }

    public List<String> getSetParentName() {
        List<String> a = new ArrayList<String>();
        Ticket t = this;
        while(t != null){
            a.add(t.getName());
            t = t.parent;
        }
        return a;
    }

    public Long getAuthorId() {
        return author != null ? author.getId() : -1;
    }

    public Long getAssignsId() {
        return assigns != null ? assigns.getId() : -1;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public String getAssignsName() {
        return assigns != null ? assigns.getUsername() : "<none>";
    }

    public User getAssigns() {
        return assigns;
    }

    public void setAssigns(User assigns) {
        this.assigns = assigns;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateBegining() {
        return dateBegining;
    }

    public void setDateBegining(String dateBegining) {
        this.dateBegining = dateBegining;
    }

    public String getDateEnding() {
        return dateEnding;
    }

    public void setDateEnding(String dateEnding) {
        this.dateEnding = dateEnding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Messege> getMesseges() {
        return messeges;
    }

    public void setMesseges(Set<Messege> messeges) {
        this.messeges = messeges;
    }

    public long getParentId() {
        return parent != null ? parent.id : -1;
    }

    public Ticket getParent() {
        return parent;
    }

    public void setParent(Ticket parent) {
        this.parent = parent;
    }
}
