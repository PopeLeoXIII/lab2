package com.jira.first.sql;

import com.jira.first.domain.Ticket;
import com.jira.first.domain.User;

public interface TicketDao {
    public Ticket getTicketById(long id);


}
