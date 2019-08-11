package com.trilogyed.tasker.controller;

import com.trilogyed.tasker.exception.NotFoundException;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class TaskerController {

    TaskerServiceLayer service;

    public TaskerController(TaskerServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value="/tasks",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskViewModel createTask(@RequestBody @Valid TaskViewModel taskViewModel){
        return service.newTask(taskViewModel);
    }

    @RequestMapping(value="/tasks",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel> getAllTasks(){
        List<TaskViewModel> taskViewModels = service.fetchAllTasks();
        if(taskViewModels.size() == 0){
            throw new NotFoundException("There aren't any task(s) to be found.");
        }
        return service.fetchAllTasks();
    }

    @RequestMapping(value="/tasks/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TaskViewModel getTask(@PathVariable("id") int id){
        TaskViewModel taskViewModel = service.fetchTask(id);
        if(taskViewModel == null){
            throw new NotFoundException("Task cannot be retrieved for the id " + id);
        }
        return taskViewModel;
    }

    @RequestMapping(value="/tasks",method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@RequestBody @Valid TaskViewModel taskViewModel){
        int id = taskViewModel.getId();
        TaskViewModel fromService = service.fetchTask(id);
        if(fromService == null){
            throw new IllegalArgumentException("Cannot Update. Task ID does not exist.");
        }
        service.updateTask(taskViewModel);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") int id) {
        TaskViewModel fromService = service.fetchTask(id);
        if(fromService == null){
            throw new IllegalArgumentException("Cannot Delete. Task ID does not exist.");
        }
        service.deleteTask(id);
    }

    @RequestMapping(value="/tasks/category/{category}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel> getTaskByCategory (@PathVariable("category") String category){
        List<TaskViewModel> tasks = service.fetchTasksByCategory(category);
        //exception handling if there are no items by that category
        if(tasks != null && tasks.size() == 0){
            throw new NotFoundException("Task(s) cannot be retrieved by the category " + category);
        }
        return tasks;
    }
}