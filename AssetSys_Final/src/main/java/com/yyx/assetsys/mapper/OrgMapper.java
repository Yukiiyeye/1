package com.yyx.assetsys.mapper;

import com.yyx.assetsys.entity.Org;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrgMapper {
    @Select("select * from tb_assert_org where orgName LIKE #{orgName} ")
    List<Org> findOrgByOrgName(String orgName);


    @Update("update tb_assert_org set orgName=#{orgName}, fatherOrg=#{fatherOrg} where id=#{id}")
    void updateOrg(Org org);

    @Select("SELECT * FROM tb_assert_org WHERE fatherOrg LIKE #{fatherOrg}")
    List<Org> findOrgByFatherOrg(String fatherOrg);

    @Insert("INSERT INTO tb_assert_org (orgName, fatherOrg) VALUES (#{orgName}, #{fatherOrg})")
    void addOrg(Org org);

    @Select("select * from tb_assert_org")
    List<Org> findAllOrg();

    @Select("select * from tb_assert_org where id=#{id}")
    Org findOrgById(Integer id);

}
