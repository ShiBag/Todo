package com.shivam.todo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shivam.todo.dao.Repos;
import com.shivam.todo.model.TodoEntity;

@Controller
public class TodoController {
	
	@Autowired
	Repos repo;

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		System.out.println("Test Passed");
		List<TodoEntity> todos = repo.findAll();
		mv.addObject("todos",todos);
		mv.setViewName("home");
		return mv;
	}
	
	@PostMapping("/add")
	public String testMethodAdd(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam(value="completed", required=false) String completed){
		TodoEntity todo = new TodoEntity();
		todo.setTitle(title);
		todo.setDescription(description);
		if(("on").equals(completed)) todo.setCompleted(true);
		else todo.setCompleted(false);
		
		repo.save(todo);
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String deleteTodo(@RequestParam("id") long id) {
		repo.deleteById(id);
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String updateTodo(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam(value="completed", required=false) String completed, @RequestParam("id") long id) {
		TodoEntity todo = repo.findById(id).orElse(new TodoEntity());
		todo.setTitle(title);
		todo.setDescription(description);
		if(("on").equals(completed)) todo.setCompleted(true);
		else todo.setCompleted(false);
		repo.save(todo);
		return "redirect:/";
	}
	
	@GetMapping("/updateHtmlController")
	public ModelAndView updateHtml(@RequestParam("id") long id) {
		ModelAndView mv = new ModelAndView();
		TodoEntity todo = repo.findById(id).orElse(new TodoEntity());
		mv.addObject("todo", todo);
		mv.setViewName("update");
		return mv;
	}
	
}
