package com.jira.first.repos;


import com.jira.first.domain.Messege;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Messege, Integer> {

    List<Messege> findByTag(String tag);
}
