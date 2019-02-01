package com.jira.first.sql;

import com.jira.first.domain.Ticket;
import com.jira.first.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcTicketDaoImp implements TicketDao
{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    @Override
    public Ticket getTicketById(long id) {
        String SQL = "SELECT * FROM TICKET WHERE id = ?";
        Ticket ticket = (Ticket) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new TicketMapper());
        return ticket;
    }
}
