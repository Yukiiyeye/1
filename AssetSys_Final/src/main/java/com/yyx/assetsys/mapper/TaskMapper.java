package com.yyx.assetsys.mapper;

import com.yyx.assetsys.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Insert("INSERT INTO tb_task_attack(taskName,taskDesc,relatedOrg,joinedTesters,taskStatus,startTime,endTime,bugNum) VALUES (#{taskName}, #{taskDesc}, #{relatedOrg}, #{joinedTesters},0, #{startTime}, #{endTime},#{bugNum})")
    void addTask(Task task);
    @Update("UPDATE tb_task_attack SET taskStatus=#{taskStatus} WHERE id=#{id}")
    void updateTaskStatus(Task task);
    @Select("SELECT * FROM tb_task_attack WHERE taskName LIKE #{taskName}")
    List<Task> findTaskByTaskName(String taskName);
    @Select("SELECT * FROM tb_task_attack WHERE taskDesc LIKE #{taskDesc}")
    List<Task>findTaskByTaskDesc(String taskDesc);
    @Select("SELECT * FROM tb_task_attack WHERE taskStatus = #{taskStatus}")
    List<Task> findTaskByTaskStatus(Integer taskStatus);
    @Select("SELECT * FROM tb_task_attack")
    List<Task> findAllTask();
    @Update("UPDATE tb_task_attack SET taskName=#{taskName},taskDesc=#{taskDesc},relatedOrg=#{relatedOrg},joinedTesters=#{joinedTesters},taskStatus=#{taskStatus},startTime=#{startTime},endTime=#{endTime},bugNum=#{bugNum} WHERE id=#{id}")
    void updateTask(Task task);


}
