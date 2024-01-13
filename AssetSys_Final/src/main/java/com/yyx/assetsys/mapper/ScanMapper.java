package com.yyx.assetsys.mapper;

import com.yyx.assetsys.entity.Scan;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ScanMapper {
    @Insert("INSERT INTO tb_task_scan(scanName,tester,mytask_id,scanTarget,scanPolicy,scanTime,foundVul,vulNo,scanTools,riskLevel) VALUES (#{scanName}, #{tester}, #{mytask_id}, #{scanTarget}, #{scanPolicy}, CURRENT_TIMESTAMP, #{foundVul}, #{vulNo}, #{scanTools}, #{riskLevel})")
    void addScan(Scan Scan);
    @Select("SELECT * FROM tb_task_scan WHERE mytask_id = #{mytask_id}")
    List<Scan> findScanByMytask_id(Integer mytask_id);
    @Select("SELECT * FROM tb_task_scan")
    List<Scan> findAllScan();
    @Select("SELECT * FROM tb_task_scan WHERE tester = #{tester}")
    List<Scan> findScanByTester(String tester);
    @Update("UPDATE tb_task_scan SET scanName=#{scanName},tester=#{tester},mytask_id=#{mytask_id},scanTarget=#{scanTarget},scanPolicy=#{scanPolicy},scanTime=CURRENT_TIMESTAMP,foundVul=#{foundVul},vulNo=#{vulNo},scanTools=#{scanTools},riskLevel=#{riskLevel} WHERE mytask_id=#{mytask_id}")
    void updateScan(Scan Scan);
}
