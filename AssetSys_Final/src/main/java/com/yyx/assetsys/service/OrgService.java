package com.yyx.assetsys.service;

import com.yyx.assetsys.entity.Org;

import java.util.List;

public interface OrgService {
    List findOrgByOrgName(String orgName);
    List findOrgByFatherOrg(String fatherOrg);
    void addOrg(Org org);
    void updateOrg(Org org);

    List<Org> findAllOrg();
    Org findOrgById(Integer id);


}
