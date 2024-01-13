package com.yyx.assetsys.mapper;

import com.yyx.assetsys.entity.Move;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MoveMapper {
    @Insert("INSERT INTO tb_task_move(moveName,tester,mytask_id,attackTime,targetIP,exploitVuls,movePath,moveResult,moveTime) VALUES (#{moveName}, #{tester}, #{mytask_id}, #{attackTime}, #{targetIP}, #{exploitVuls}, #{movePath}, #{moveResult}, CURRENT_TIMESTAMP)")
    void addMove(Move Move);
    @Select("SELECT * FROM tb_task_move WHERE mytask_id = #{mytask_id}")
    List<Move> findMoveByMytask_id(Integer mytask_id);
    @Select("SELECT * FROM tb_task_move")
    List<Move> findAllMove();
    @Update("UPDATE tb_task_move SET moveName=#{moveName},tester=#{tester},attackTime=#{attackTime},targetIP=#{targetIP},exploitVuls=#{exploitVuls},movePath=#{movePath},moveResult=#{moveResult},moveTime=CURRENT_TIMESTAMP WHERE mytask_id=#{mytask_id}")
    void updateMove(Move move);

    @Select("SELECT * FROM tb_task_move WHERE tester = #{tester}")
    List<Move> findMoveByTester(String tester);
}
