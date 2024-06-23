package com.todoListProject.MVC.Services;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.todoListProject.MVC.Model.TodoListEntity;
import com.todoListProject.MVC.Repo.TodoListRepo;

@Service
public class TodoListServices {
	@Autowired
	TodoListRepo todoListRepo;
	
	public ResponseEntity<List<TodoListEntity>> getTodoList(){
		
		final List<TodoListEntity> todoListEntity=todoListRepo.findAll();
		return new ResponseEntity<List<TodoListEntity>>(todoListEntity,HttpStatus.OK);
	}
	
	public ResponseEntity<TodoListEntity> postTodoList(TodoListEntity listEntity){
		
		final TodoListEntity todoListEntity= todoListRepo.save(listEntity);
		return new ResponseEntity<TodoListEntity>(todoListEntity,HttpStatus.OK);
		
	}

	public ResponseEntity<TodoListEntity> updateTodoList(TodoListEntity listEntity) {
		final TodoListEntity todoListEntity= todoListRepo.save(listEntity);
		return new ResponseEntity<TodoListEntity>(todoListEntity,HttpStatus.OK);
	}

	public ResponseEntity<String> deleteTodoListService(int id) {
		todoListRepo.deleteById(id);
		return new ResponseEntity<String>("Record deleted successfully",HttpStatus.OK);
	}
	

}
