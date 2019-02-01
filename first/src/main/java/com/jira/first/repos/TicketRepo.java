package com.jira.first.repos;

import com.jira.first.domain.Ticket;
import com.jira.first.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepo extends CrudRepository<Ticket, Long> {


    List<Ticket> findByAuthorOrAssigns(User author, User assigns);
    List<Ticket> findByName(String name);

    @Query(value = "select * from ticket where status = ?1 and assigns_id = ?2", nativeQuery = true)
    List<Ticket> findByNameSort(int status, long id);

    @Query(value = "select count(*) from ticket where status = ?1 and assigns_id = ?2", nativeQuery = true)
    Integer countByStatusAndUserId(int status, long userId);


    @Query(value = "select count(*) from ticket where author_id = ?1", nativeQuery = true)
    Integer countByAuthor(long userId);


}
