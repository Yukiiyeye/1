package com.yyx.assetsys.controller;


import com.yyx.assetsys.entity.Result;
import com.yyx.assetsys.entity.Task;
import com.yyx.assetsys.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/addTask")
    public String addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return Result.success().getMessage();

    }
    @PostMapping("/updateTaskStatus")
    public String updateTaskStatus(@RequestBody Task task){
        taskService.updateTaskStatus(task);
        return Result.success().getMessage();

    }
    @GetMapping("/findTaskByTaskName")
    public List<Task> findTaskByTaskName(String taskName){
        return taskService.findTaskByTaskName(taskName);
    }
    @GetMapping("/findTaskByTaskDesc")
    public List<Task> findTaskByTaskDesc(String taskDesc){
        return taskService.findTaskByTaskDesc(taskDesc);
    }
    @GetMapping("/findTaskByTaskStatus")
    public List<Task> findTaskByTaskStatus(Integer taskStatus){
        return taskService.findTaskByTaskStatus(taskStatus);
    }
    @GetMapping("/findAllTask")
    public List<Task> findAllTask(){
        return taskService.findAllTask();
    }
    @PostMapping("/updateTask")
    public String updateTask(@RequestBody Task task){
        taskService.updateTask(task);
        return Result.success().getMessage();

    }



}
