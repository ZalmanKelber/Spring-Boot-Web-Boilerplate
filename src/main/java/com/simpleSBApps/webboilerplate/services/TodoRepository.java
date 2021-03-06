package com.simpleSBApps.webboilerplate.services;

import com.simpleSBApps.webboilerplate.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {

    List<Todo> findAllByUser(String user);
}
