package com.todoListProject.MVC.Repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.todoListProject.MVC.Model.TodoListEntity;

@Repository
public interface TodoListRepo extends JpaRepository<TodoListEntity,Integer>{

}
