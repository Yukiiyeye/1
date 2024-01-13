package com.yyx.assetsys.service.impl;

import com.yyx.assetsys.mapper.TaskMapper;
import com.yyx.assetsys.entity.Task;
import com.yyx.assetsys.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
@Autowired
private TaskMapper taskMapper;
    @Override
    public void addTask(Task task) {
        taskMapper.addTask(task);
    }

    @Override
    public List<Task> findTaskByTaskName(String taskName) {
        return taskMapper.findTaskByTaskName("%" + taskName + "%");
    }

    @Override
    public List<Task> findTaskByTaskDesc(String taskDesc) {
        return taskMapper.findTaskByTaskDesc("%" + taskDesc + "%");
    }

    @Override
    public List<Task> findTaskByTaskStatus(Integer taskStatus) {
        return taskMapper.findTaskByTaskStatus(taskStatus);
    }

    @Override
    public void updateTaskStatus(Task task) {
        taskMapper.updateTaskStatus(task);
    }

    @Override
    public List<Task> findAllTask() {
        return taskMapper.findAllTask();
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }
}
