package com.todoListProject.MVC.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoListProject.MVC.Model.TodoListEntity;
import com.todoListProject.MVC.Services.TodoListServices;

@RestController
@RequestMapping("list")
@CrossOrigin(origins = "http://localhost:3000/")
public class TodolistController {
@Autowired
TodoListServices todoListServices;


@GetMapping("getTodoList")
public ResponseEntity<List<TodoListEntity>> getTodoList(){
	return todoListServices.getTodoList();
}

@PostMapping("todoList")
public ResponseEntity<TodoListEntity> postTodolist(@RequestBody TodoListEntity todoListEntity){
	return todoListServices.postTodoList(todoListEntity);
}


@PutMapping("/{id}")
public ResponseEntity<TodoListEntity> updateTodoList(@PathVariable int id, @RequestBody TodoListEntity listEntity ){
	TodoListEntity todoListEntity = new TodoListEntity();
	todoListEntity.setId(id);
	todoListEntity.setStatus(listEntity.getStatus());
	todoListEntity.setTask(listEntity.getTask());
	System.out.println(listEntity);
	return todoListServices.updateTodoList(todoListEntity);
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteTodoList(@PathVariable int id){
	return todoListServices.deleteTodoListService(id);
}
}

