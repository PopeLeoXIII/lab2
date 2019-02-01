package com.jira.first.repos;

import com.jira.first.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
