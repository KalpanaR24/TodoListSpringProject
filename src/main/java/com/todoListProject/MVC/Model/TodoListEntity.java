package com.todoListProject.MVC.Model;

import java.lang.reflect.Array;
import java.util.TreeSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="todo_list",schema = "public")
public class TodoListEntity{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private int Id;
 private String task;
 private String status;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getTask() {
	return task;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public void setTask(String task) {
	this.task = task;
}
@Override
public String toString() {
	return "TodoListEntity [Id=" + Id + ", task=" + task + "]";
}


 

}
