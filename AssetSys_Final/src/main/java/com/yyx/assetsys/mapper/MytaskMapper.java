package com.yyx.assetsys.mapper;

import com.yyx.assetsys.entity.Mytask;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MytaskMapper {
    @Insert("INSERT INTO tb_task_mytask(user_id,task_id,finished_status,finished_time,submit_scan_num,submit_exploit_num,submit_move_num)VALUES (#{user_id},#{task_id},#{finished_status},#{finished_time},#{submit_scan_num},#{submit_exploit_num},#{submit_move_num})")
    void addMytask(Mytask mytask);
    @Select("SELECT * FROM tb_task_mytask WHERE user_id=#{user_id}")
    List<Mytask> findMytaskByUser_id(Integer user_id);
    @Update("UPDATE tb_task_mytask SET finished_status=#{finished_status} WHERE task_id=#{task_id}")
    void updateMytaskStatus(Mytask mytask);
}
