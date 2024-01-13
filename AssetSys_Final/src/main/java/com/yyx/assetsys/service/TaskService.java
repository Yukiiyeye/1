package com.yyx.assetsys.service;

import com.yyx.assetsys.entity.Task;

import java.util.List;

public interface TaskService {
    void addTask(Task task);
    List findTaskByTaskName(String taskName);
    List findTaskByTaskDesc(String taskDesc);
    List findTaskByTaskStatus(Integer taskStatus);
    void updateTaskStatus(Task task);

    List<Task> findAllTask();
    void updateTask(Task task);

}
