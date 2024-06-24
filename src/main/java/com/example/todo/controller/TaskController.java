package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TaskController {

    Logger logger= LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskRepository taskRepository;
    @GetMapping("/hello")
    public String hello(){
        return "Hello for testing";
    }
@GetMapping("/api/tasks/all")
    public List Alltask(){
logger.info("list all tasks");
    return taskRepository.findAll();
}
    @PostMapping("/api/tasks")
    public Task createTask(@RequestBody Task task){
        logger.info("new task added");
       taskRepository.save(task);
       return task;
    }
    @GetMapping("/api/tasks/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id);
    }

    @DeleteMapping("/api/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
    @DeleteMapping("/api/tasks/all")
    public String deleteAllTask() {

        taskRepository.deleteAll();
        logger.info("All task deleted");
        return "deleted Successfully";
    }

    @PutMapping("/api/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
     taskDetails.setId(id);
        return taskRepository.save(taskDetails);
    }


}
