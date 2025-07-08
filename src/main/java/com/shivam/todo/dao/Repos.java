package com.shivam.todo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.todo.model.TodoEntity;

public interface Repos extends JpaRepository<TodoEntity, Long>{

}
