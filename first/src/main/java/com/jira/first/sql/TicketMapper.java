package com.jira.first.sql;


import com.jira.first.domain.Ticket;
import com.jira.first.domain.User;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper {


    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Ticket t = new Ticket();
        t.setName(resultSet.getString("name"));
        t.setId(resultSet.getLong("id"));
        return t;
    }
}
